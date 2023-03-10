package tech.hdurmaz.credit;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.hdurmaz.clients.credit.CreditCheckHistoryListResponse;
import tech.hdurmaz.clients.credit.CreditCheckResponse;

@RestController
@RequestMapping("api/v1/credit-check")
@AllArgsConstructor
@Slf4j
public class CreditCheckController {

  private final CreditCheckService creditCheckService;

  @GetMapping(path = "/{identityNumber}/{salary}")
  public CreditCheckResponse checkCredit(@PathVariable("identityNumber") String identityNumber,
      @PathVariable("salary") Integer salary) {
    log.info("Customer credit score checked. - " + identityNumber);
    return creditCheckService.checkCreditAmount(identityNumber, salary);
  }

  @GetMapping(path = "historyList/{identityNumber}")
  public List<CreditCheckHistoryListResponse> getCreditHistoryListByIdentityNumber(
      @PathVariable("identityNumber") String identityNumber) {
    return creditCheckService.checkCreditAmountHistoryByIdentityNumber(identityNumber);
  }

}
