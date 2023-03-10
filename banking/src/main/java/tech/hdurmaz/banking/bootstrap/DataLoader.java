package tech.hdurmaz.banking.bootstrap;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import tech.hdurmaz.banking.repository.CustomerRepository;
import tech.hdurmaz.banking.service.CustomerService;
import tech.hdurmaz.clients.customer.CustomerRegistrationRequest;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

  private final CustomerRepository customerRepository;
  private final CustomerService customerService;
  ObjectMapper mapper = new ObjectMapper();

  @Value("classpath:mockdata/MOCK_DATA.json")
  Resource resourceFile;

  @Override
  public void run(String... args) throws Exception {

    if (customerRepository.findAll().isEmpty()) {
      var customerList = Arrays.asList(
          mapper.readValue(resourceFile.getFile(), CustomerRegistrationRequest[].class));
      customerList.forEach(
          customerService::registerCustomer);
    }
  }
}











