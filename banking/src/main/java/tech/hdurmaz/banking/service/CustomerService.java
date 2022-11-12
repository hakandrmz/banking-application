package tech.hdurmaz.banking.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.hdurmaz.amqp.RabbitMQMessageProducer;
import tech.hdurmaz.banking.dtos.CustomerCreditResponse;
import tech.hdurmaz.banking.dtos.CustomerRegistrationRequest;
import tech.hdurmaz.banking.dtos.UpdateCustomerRequest;
import tech.hdurmaz.banking.dtos.UpdateCustomerResponse;
import tech.hdurmaz.banking.exceptions.CustomerAlreadyExistException;
import tech.hdurmaz.banking.models.Customer;
import tech.hdurmaz.banking.repository.CustomerRepository;
import tech.hdurmaz.clients.credit.CreditCheckHistoryListResponse;
import tech.hdurmaz.clients.credit.CreditCheckResponse;
import tech.hdurmaz.clients.credit.CreditClient;
import tech.hdurmaz.clients.notification.NotificationRequest;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CreditClient creditClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {

        checkCustomerIsAlreadyExist(customerRequest.identityNumber());

        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .income(customerRequest.income())
                .phoneNumber(customerRequest.phoneNumber())
                .identityNumber(customerRequest.identityNumber())
                .build();

        customerRepository.save(customer);
    }

    private void checkCustomerIsAlreadyExist(String identityNumber) {
        boolean exists = customerRepository.existsByIdentityNumber(identityNumber);
        if (exists) throw new CustomerAlreadyExistException("Customer " + identityNumber + " is already exist.");
    }

    public CustomerCreditResponse checkCustomerCreditScore(String identityNumber) {

        Customer customer = customerRepository.findByIdentityNumber(identityNumber);

        CreditCheckResponse creditCheckResponse =
                creditClient.checkCredit(identityNumber, customer.getIncome());

        NotificationRequest notificationRequest =
                new NotificationRequest("",
                        "Customer identity number: " + creditCheckResponse.identityNumber(),
                        "Kredi sonucunuz: " + creditCheckResponse.amount());

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");

        return CustomerCreditResponse.builder()
                .customerId(creditCheckResponse.identityNumber())
                .message(creditCheckResponse.message())
                .amount(creditCheckResponse.amount())
                .build();
    }

    public UpdateCustomerResponse updateCustomer(UpdateCustomerRequest updateCustomerRequest) {

        checkCustomerIsAlreadyExist(updateCustomerRequest.getIdentityNumber());

        Customer customer = customerRepository.getById(updateCustomerRequest.getId());
        customer.setEmail(updateCustomerRequest.getEmail());
        customer.setIncome(updateCustomerRequest.getIncome());
        customer.setFirstName(updateCustomerRequest.getFirstName());
        customer.setPhoneNumber(updateCustomerRequest.getPhoneNumber());

        customerRepository.save(customer);

        return new UpdateCustomerResponse();
    }

    public List<CreditCheckHistoryListResponse> getCreditScoresByCustomerId(String identityNumber) {
        return creditClient.getCreditHistoryListByIdentityNumber(identityNumber);
    }
}
