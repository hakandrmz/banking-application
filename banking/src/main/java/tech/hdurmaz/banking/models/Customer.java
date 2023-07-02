package tech.hdurmaz.banking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Customer {

  @Id
  @SequenceGenerator(
      name = "customer_id_generator",
      sequenceName = "customer_id_sequence"
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "customer_id_sequence"
  )
  private Integer id;
  private String firstName;
  private String lastName;
  @Column(unique = true)
  private String email;
  private Integer income;
  @Column(unique = true)
  private String phoneNumber;
  @Column(unique = true)
  private String identityNumber;
  @CreatedDate
  @Column(name = "created_date")
  private Date createdTime;

  @LastModifiedDate
  @Column(name = "last_modified_date")
  private Date lastModifiedTime;
}
