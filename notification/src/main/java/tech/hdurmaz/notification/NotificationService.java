package tech.hdurmaz.notification;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.hdurmaz.clients.notification.NotificationRequest;

@Service
@AllArgsConstructor
public class NotificationService {

  private final NotificationRepository notificationRepository;

  public void send(NotificationRequest notificationRequest) {
    notificationRepository.save(
        Notification.builder()
            .toCustomerId(notificationRequest.identityNumber())
            .toCustomerEmail(notificationRequest.toCustomerName())
            .sender("hdurmaz")
            .message(notificationRequest.message())
            .sentAt(LocalDateTime.now())
            .build()
    );
  }
}
