package tech.hdurmaz.banking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.hdurmaz.clients.credit.CreditCheckResponse;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRequest) {
        log.info("new customer registration {}",customerRequest);
        customerService.registerCustomer(customerRequest);
    }

    @GetMapping(path = "/{customerId}")
    public CreditCheckResponse checkCreditResponseByCustomerId(@PathVariable("customerId") Integer customerId) {
        log.info("Customer credit score checked. - "+customerId);
        return customerService.checkCustomerCreditScore(customerId);
    }
}
