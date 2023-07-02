package tech.hdurmaz.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {
    "tech.hdurmaz.banking",
    "tech.hdurmaz.amqp"
}
)
@EnableFeignClients(
    basePackages = "tech.hdurmaz.clients"
)
@EnableJpaAuditing
public class BankingApplication {

  public static void main(String[] args) {
    SpringApplication.run(BankingApplication.class, args);
  }
}
