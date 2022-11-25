package tech.hdurmaz.clients.credit;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("credit")
public interface CreditClient {

  @GetMapping(path = "api/v1/credit-check/{identityNumber}/{salary}")
  CreditCheckResponse checkCredit(@PathVariable("identityNumber") String identityNumber,
      @PathVariable("salary") Integer salary);

  @GetMapping(path = "api/v1/credit-check/historyList/{identityNumber}")
  List<CreditCheckHistoryListResponse> getCreditHistoryListByIdentityNumber(
      @PathVariable("identityNumber") String identityNumber);
  //8081/api/v1/credit-check/36367114630
}
