package tech.hdurmaz.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {
    "tech.hdurmaz.banking",
    "tech.hdurmaz.amqp"
}
)
@EnableEurekaClient
@EnableFeignClients(
    basePackages = "tech.hdurmaz.clients"

)
public class BankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingApplication.class, args);
    }
}
