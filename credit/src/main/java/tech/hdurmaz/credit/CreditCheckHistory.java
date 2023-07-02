package tech.hdurmaz.credit;

import jakarta.persistence.Entity;
import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  private String customerId;
  private Integer amount;
  private Date checkDate;
}
