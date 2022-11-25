package tech.hdurmaz.notification.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tech.hdurmaz.clients.notification.NotificationRequest;
import tech.hdurmaz.notification.NotificationService;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

  private final NotificationService notificationService;

  @RabbitListener(queues = "${rabbitmq.queues.notification}")
  public void consumer(NotificationRequest notificationRequest) {
    log.info("Consumed {} from queue.", notificationRequest);
    notificationService.send(notificationRequest);
  }
}
