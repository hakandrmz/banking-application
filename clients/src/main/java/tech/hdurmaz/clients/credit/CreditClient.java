package tech.hdurmaz.clients.credit;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("credit")
public interface CreditClient {

    @GetMapping(path = "api/v1/credit-check/{customerId}")
    CreditCheckResponse checkCredit(@PathVariable("customerId") Integer customerId);

}
