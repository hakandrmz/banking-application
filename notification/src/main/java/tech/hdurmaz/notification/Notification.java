package tech.hdurmaz.notification;

import java.time.LocalDateTime;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Notification {

  @Id
  @SequenceGenerator(
      name = "notification_id_sequence",
      sequenceName = "notification_id_sequence"
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "notification_id_sequence"
  )
  private Integer notificationId;
  private String toCustomerId;
  private String toCustomerEmail;
  private String sender;
  private String message;
  private LocalDateTime sentAt;
  @CreatedDate
  @Column(name = "created_date")
  private Date createdTime;

  @LastModifiedDate
  @Column(name = "last_modified_date")
  private Date lastModifiedTime;

}
