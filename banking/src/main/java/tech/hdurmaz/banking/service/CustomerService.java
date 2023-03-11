package tech.hdurmaz.banking.service;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.hdurmaz.amqp.RabbitMQMessageProducer;
import tech.hdurmaz.banking.config.Constants;
import tech.hdurmaz.banking.config.PropertiesConfigurer;
import tech.hdurmaz.banking.exceptions.CustomerAlreadyExistException;
import tech.hdurmaz.banking.models.Customer;
import tech.hdurmaz.banking.repository.CustomerRepository;
import tech.hdurmaz.clients.credit.CreditCheckHistoryListResponse;
import tech.hdurmaz.clients.credit.CreditCheckResponse;
import tech.hdurmaz.clients.credit.CreditClient;
import tech.hdurmaz.clients.customer.CustomerCreditResponse;
import tech.hdurmaz.clients.customer.CustomerRegistrationRequest;
import tech.hdurmaz.clients.customer.UpdateCustomerRequest;
import tech.hdurmaz.clients.customer.UpdateCustomerResponse;
import tech.hdurmaz.clients.mail.CustomerRegistrationMail;
import tech.hdurmaz.clients.notification.NotificationRequest;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final CreditClient creditClient;
  private final RabbitMQMessageProducer rabbitMQMessageProducer;
  private final PropertiesConfigurer propertiesConfigurer;

  public void registerCustomer(CustomerRegistrationRequest customerRequest) {

    checkCustomerIsAlreadyExist(customerRequest.identityNumber());

    sendRegistrationMailNotificationToCustomer(customerRequest);

    Customer customer = Customer.builder().firstName(customerRequest.firstName())
        .lastName(customerRequest.lastName()).email(customerRequest.email())
        .income(customerRequest.income()).phoneNumber(customerRequest.phoneNumber())
        .identityNumber(customerRequest.identityNumber()).build();

    customerRepository.save(customer);
  }

  private void sendRegistrationMailNotificationToCustomer(
      CustomerRegistrationRequest customerRequest) {
    var customerRegistrationMail = new CustomerRegistrationMail(
        customerRequest.email());
    rabbitMQMessageProducer
        .publish(
            customerRegistrationMail,
            propertiesConfigurer.getValue(Constants.RABBITMQ_MAIL_EXCHANGE),
            propertiesConfigurer.getValue(Constants.RABBITMQ_MAIL_ROUTING_KEY));

    var notification = new NotificationRequest(
        customerRequest.identityNumber(),
        "Customer identity number: " + customerRequest.identityNumber(),
        "Kredi sonucunuz hesaplanıyor."
    );
    rabbitMQMessageProducer
        .publish(notification,
            propertiesConfigurer.getValue(Constants.RABBITMQ_NOTIFICATION_EXCHANGE),
            propertiesConfigurer.getValue(Constants.RABBITMQ_NOTIFICATION_ROUTING_KEY)
        );
  }

  private void checkCustomerIsAlreadyExist(String identityNumber) {
    boolean exists = customerRepository.existsByIdentityNumber(identityNumber);
    if (exists) {
      log.error("Customer " + identityNumber + " is already exist.");
      throw new CustomerAlreadyExistException(
          "Customer " + identityNumber + " is already exist.");
    }
  }

  public CustomerCreditResponse checkCustomerCreditScore(String identityNumber) {

    Customer customer = customerRepository.findByIdentityNumber(identityNumber);

    CreditCheckResponse creditCheckResponse = creditClient.checkCredit(
        identityNumber,
        customer.getIncome()
    );

    var notification = new NotificationRequest(
        identityNumber,
        "Customer identity number: " + creditCheckResponse.identityNumber(),
        "Kredi sonucunuz: " + creditCheckResponse.amount()
    );

    rabbitMQMessageProducer
        .publish(notification,
            propertiesConfigurer.getValue(Constants.RABBITMQ_NOTIFICATION_EXCHANGE),
            propertiesConfigurer.getValue(Constants.RABBITMQ_NOTIFICATION_ROUTING_KEY)
        );

    return CustomerCreditResponse.builder().customerId(creditCheckResponse.identityNumber())
        .message(creditCheckResponse.message()).amount(creditCheckResponse.amount()).build();
  }

  public UpdateCustomerResponse updateCustomer(UpdateCustomerRequest updateCustomerRequest) {

    Customer customer = customerRepository.getCustomerByIdentityNumber(
        updateCustomerRequest.getIdentityNumber());
    customer.setEmail(updateCustomerRequest.getEmail());
    customer.setIncome(updateCustomerRequest.getIncome());
    customer.setFirstName(updateCustomerRequest.getFirstName());
    customer.setPhoneNumber(updateCustomerRequest.getPhoneNumber());

    customerRepository.save(customer);

    return UpdateCustomerResponse.builder()
        .identityNumber(updateCustomerRequest.getIdentityNumber()).build();
  }

  public List<CreditCheckHistoryListResponse> getCreditScoresByCustomerId(String identityNumber) {
    return creditClient.getCreditHistoryListByIdentityNumber(identityNumber);
  }
}
