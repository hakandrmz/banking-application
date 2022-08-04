package tech.hdurmaz.banking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.hdurmaz.clients.credit.CreditCheckResponse;
import tech.hdurmaz.clients.credit.CreditClient;
import tech.hdurmaz.clients.notification.NotificationClient;
import tech.hdurmaz.clients.notification.NotificationRequest;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final NotificationClient notificationClient;
    private final CreditClient creditClient;

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder().firstName(customerRequest.firstName()).lastName(customerRequest.lastName()).email(customerRequest.email()).build();
        customerRepository.save(customer);
    }

    public CreditCheckResponse checkCustomerCreditScore(Integer customerId) {

        CreditCheckResponse response = creditClient.checkCredit(customerId);

        notificationClient
                .sendNotification(new NotificationRequest(
                1,
                "Hakan Durmaz",
                "Kredi sonucunuz: 120")

        );

        return response;
    }
}
