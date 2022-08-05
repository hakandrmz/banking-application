package tech.hdurmaz.credit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.hdurmaz.clients.credit.CreditCheckHistoryListResponse;
import tech.hdurmaz.clients.credit.CreditCheckResponse;

import java.util.List;

@RestController
@RequestMapping("api/v1/credit-check")
@AllArgsConstructor
@Slf4j
public class CreditCheckController {

    private final CreditCheckService creditCheckService;

    @GetMapping(path = "/{identityNumber}/{salary}")
    CreditCheckResponse checkCredit(@PathVariable("identityNumber") String identityNumber,
                                    @PathVariable("salary") Integer salary){
        log.info("Customer credit score checked. - " + identityNumber);
        return creditCheckService.checkCreditAmount(identityNumber,salary);
    }

    @GetMapping(path = "api/v1/credit-check-list/{identityNumber}")
    List<CreditCheckHistoryListResponse> getCreditHistoryListByIdentityNumber(@PathVariable("identityNumber") String identityNumber){
        return creditCheckService.checkCreditAmountHistoryByIdentityNumber(identityNumber);
    }
}
