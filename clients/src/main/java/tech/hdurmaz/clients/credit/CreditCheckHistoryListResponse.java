package tech.hdurmaz.clients.credit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCheckHistoryListResponse {

    private Integer id;
    private String customerId;
    private Integer amount;
    private Date checkDate;
}
