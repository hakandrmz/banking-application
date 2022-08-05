package tech.hdurmaz.credit;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.hdurmaz.clients.credit.CreditCheckHistoryListResponse;
import tech.hdurmaz.clients.credit.CreditCheckResponse;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CreditCheckService {

    private final CreditCheckRepository creditCheckRepository;

    public CreditCheckResponse checkCreditAmount(String identityNumber,Integer salary) {

        Integer creditAmount = calculateCreditAmount(salary);

        creditCheckRepository.save(CreditCheckHistory.builder()
                .customerId(identityNumber)
                .amount(creditAmount)
                .checkDate(Date.from(Instant.now()))
                .build());

        CreditCheckResponse response =
                CreditCheckResponse.builder()
                        .identityNumber(identityNumber)
                        .amount(creditAmount)
                        .build();

        return response;
    }

    private Integer calculateCreditAmount(Integer salary) {

        Integer creditScore = calculateCreditScore(salary);
        Integer amountResult = 0;
        if(creditScore < 500) {
            amountResult = 0;
        }else if(creditScore>500 && creditScore<1000) {
            amountResult = 10000;
        }else if (creditScore>=1000) {
            amountResult = salary*5;
        }
        else if (creditScore>10000) {
            amountResult = salary*10;
        }
        return amountResult;
    }

    private Integer calculateCreditScore(Integer salary) {
        return salary*3;
    }

    public List<CreditCheckHistoryListResponse> checkCreditAmountHistoryByIdentityNumber(String identityNumber) {
        var result = creditCheckRepository.findAllByCustomerId(identityNumber);
        return result;
    }
}
