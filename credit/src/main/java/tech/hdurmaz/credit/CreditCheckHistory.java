package tech.hdurmaz.credit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditCheckHistory {

    @Id
    @SequenceGenerator(
            name = "credit_id_sequence",
            sequenceName = "credit_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "credit_id_sequence"
    )
    private Integer id;
    private Integer customerId;
    private Integer amount;
    private Date checkDate;
}
