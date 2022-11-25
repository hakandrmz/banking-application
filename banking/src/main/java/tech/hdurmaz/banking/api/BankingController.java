package tech.hdurmaz.banking.api;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.hdurmaz.banking.dtos.CustomerCreditResponse;
import tech.hdurmaz.banking.dtos.CustomerRegistrationRequest;
import tech.hdurmaz.banking.dtos.UpdateCustomerRequest;
import tech.hdurmaz.banking.dtos.UpdateCustomerResponse;
import tech.hdurmaz.banking.service.CustomerService;
import tech.hdurmaz.clients.credit.CreditCheckHistoryListResponse;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class BankingController {

  private final CustomerService customerService;

  @PostMapping
  public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRequest) {
    log.info("New request to register customer. Request: " + customerRequest);
    customerService.registerCustomer(customerRequest);
  }

  @GetMapping(path = "/{identityNumber}")
  public CustomerCreditResponse checkCreditResponseByCustomerId(
      @PathVariable("identityNumber") String identityNumber) {
    log.info("Customer credit score requested. - " + identityNumber);
    return customerService.checkCustomerCreditScore(identityNumber);
  }

  @PutMapping
  public ResponseEntity<UpdateCustomerResponse> updateCustomer(@RequestBody UpdateCustomerRequest updateCustomerRequest) {
    log.info("Customer updated request: " + updateCustomerRequest);
    return new ResponseEntity<>(customerService.updateCustomer(updateCustomerRequest),
        HttpStatus.CREATED);
  }

  @GetMapping(path = "/creditHistory/{identityNumber}")
  public List<CreditCheckHistoryListResponse> getCreditScoresByCustomerIdResponse(
      @PathVariable("identityNumber") String identityNumber) {
    return customerService.getCreditScoresByCustomerId(identityNumber);
  }
}
