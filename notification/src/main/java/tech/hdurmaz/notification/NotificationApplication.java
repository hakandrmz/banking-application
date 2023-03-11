package tech.hdurmaz.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(
    scanBasePackages = {
        "tech.hdurmaz.notification",
        "tech.hdurmaz.amqp",
    }
)
@EnableJpaAuditing
public class NotificationApplication {

  public static void main(String[] args) {
    SpringApplication.run(NotificationApplication.class, args);
  }

}
