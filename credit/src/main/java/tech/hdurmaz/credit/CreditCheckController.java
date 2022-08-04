package tech.hdurmaz.credit;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.hdurmaz.clients.credit.CreditCheckResponse;

@RestController
@RequestMapping("api/v1/credit-check")
@AllArgsConstructor
public class CreditCheckController {

    private final CreditCheckService creditCheckService;

    @GetMapping(path = "{customerId}")
    public CreditCheckResponse checkCredit(@PathVariable("customerId") Integer customerId){
        return creditCheckService.checkCreditAmount(customerId);
    }
}
