package tech.hdurmaz.clients.credit;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("credit")
public interface CreditClient {

    @GetMapping(path = "api/v1/credit-check/{identityNumber}/{salary}")
    CreditCheckResponse checkCredit(@PathVariable("identityNumber") String identityNumber,
                                           @PathVariable("salary") Integer salary);

    @GetMapping(path = "api/v1/credit-check-list/{identityNumber}")
    List<CreditCheckHistoryListResponse> getCreditHistoryListByIdentityNumber(@PathVariable("identityNumber") String identityNumber);
}
