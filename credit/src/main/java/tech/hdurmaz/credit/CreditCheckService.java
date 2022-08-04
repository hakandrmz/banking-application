package tech.hdurmaz.credit;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.hdurmaz.clients.credit.CreditCheckResponse;

import java.time.Instant;
import java.util.Date;

@Service
@AllArgsConstructor
public class CreditCheckService {

    private final CreditCheckRepository creditCheckRepository;

    public CreditCheckResponse checkCreditAmount(Integer customerId) {
        creditCheckRepository.save(CreditCheckHistory.builder()
                .customerId(customerId).amount(calculateCreditAmount(customerId))
                        .checkDate(Date.from(Instant.now()))
                .build());
        CreditCheckResponse response = CreditCheckResponse.builder()
                .customerId(customerId).amount(calculateCreditAmount(customerId)).build();
        return response;
    }

    private Integer calculateCreditAmount(Integer customerId) {
        return 0;
    }
}
