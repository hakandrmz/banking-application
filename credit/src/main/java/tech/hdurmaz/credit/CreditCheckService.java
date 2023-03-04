package tech.hdurmaz.credit;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.hdurmaz.clients.credit.CreditCheckHistoryListResponse;
import tech.hdurmaz.clients.credit.CreditCheckResponse;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class CreditCheckService {

    private final CreditCheckRepository creditCheckRepository;

    public CreditCheckResponse checkCreditAmount(String identityNumber, Integer salary) {

        Integer creditAmount = calculateCreditAmount(salary);

        creditCheckRepository.save(CreditCheckHistory.builder()
                .customerId(identityNumber)
                .amount(creditAmount)
                .checkDate(Date.from(Instant.now()))
                .build());

        return CreditCheckResponse.builder()
                .identityNumber(identityNumber)
                .amount(creditAmount)
                .build();
    }

    private Integer calculateCreditAmount(Integer salary) {

        int creditScore = calculateCreditScore(salary);
        int amountResult = 0;
        if (creditScore > 500 && creditScore < 1000) {
            amountResult = 10000;
        } else if (creditScore >= 1000) {
            amountResult = salary * 5;
        }
        return amountResult;
    }

    private Integer calculateCreditScore(Integer salary) {
        return salary * 3;
    }

    public List<CreditCheckHistoryListResponse> checkCreditAmountHistoryByIdentityNumber(
            String identityNumber) {
        var foundCreditHistory = creditCheckRepository.findAllByCustomerId(identityNumber);
        List<CreditCheckHistoryListResponse> result = new LinkedList<>();

        for (var a : foundCreditHistory) {
            result.add(CreditCheckHistoryListResponse.builder()
                    .customerId(a.getCustomerId())
                    .amount(a.getAmount())
                    .checkDate(a.getCheckDate())
                    .id(a.getId())
                    .build());
        }

        return result;
    }
}
