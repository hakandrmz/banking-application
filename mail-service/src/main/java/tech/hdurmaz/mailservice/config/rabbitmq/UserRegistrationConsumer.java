package tech.hdurmaz.mailservice.config.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tech.hdurmaz.clients.mail.CustomerRegistrationMail;
import tech.hdurmaz.mailservice.service.EmailService;

@Component
@AllArgsConstructor
@Slf4j
public class UserRegistrationConsumer {

  private final EmailService emailService;

  @RabbitListener(queues = "${rabbitmq.queues.mail}")
  public void consumer(CustomerRegistrationMail request) {
    log.info("Consumed {} from queue.", request);
    emailService.sendRegistrationEmail(request);
  }
}
