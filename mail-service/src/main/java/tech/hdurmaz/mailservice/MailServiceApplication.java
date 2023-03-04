package tech.hdurmaz.mailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(
    scanBasePackages = {
        "tech.hdurmaz.mailservice",
        "tech.hdurmaz.amqp",
    }
)
@ConfigurationPropertiesScan
@EnableEurekaClient
public class MailServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MailServiceApplication.class, args);
  }

}
