package tech.hdurmaz.banking.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.hdurmaz.banking.dtos.*;
import tech.hdurmaz.banking.service.CustomerService;
import tech.hdurmaz.clients.credit.CreditCheckHistoryListResponse;

import java.util.List;

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
    public CustomerCreditResponse checkCreditResponseByCustomerId(@PathVariable("identityNumber") String identityNumber) {
        log.info("Customer credit score requested. - " + identityNumber);
        return customerService.checkCustomerCreditScore(identityNumber);
    }

    @PutMapping
    public UpdateCustomerResponse updateCustomer(@RequestBody UpdateCustomerRequest updateCustomerRequest) {
        log.info("Customer updated request: " + updateCustomerRequest);
        return customerService.updateCustomer(updateCustomerRequest);
    }

    @GetMapping(path = "/creditHistory/{identityNumber}")
    public List<CreditCheckHistoryListResponse> getCreditScoresByCustomerIdResponse(@PathVariable("identityNumber") String identityNumber) {
        return customerService.getCreditScoresByCustomerId(identityNumber);
    }
}
