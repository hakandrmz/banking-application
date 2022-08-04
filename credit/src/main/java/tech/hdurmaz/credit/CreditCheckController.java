package tech.hdurmaz.credit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.hdurmaz.clients.credit.CreditCheckResponse;

@RestController
@RequestMapping("api/v1/credit-check")
@AllArgsConstructor
@Slf4j
public class CreditCheckController {

    private final CreditCheckService creditCheckService;

    @GetMapping(path = "{customerId}")
    public CreditCheckResponse checkCredit(@PathVariable("customerId") Integer customerId){
        log.info("Customer credit score checked. - "+customerId);
        return creditCheckService.checkCreditAmount(customerId);
    }
}
