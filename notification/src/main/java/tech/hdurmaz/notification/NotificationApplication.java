package tech.hdurmaz.notification;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.hdurmaz.amqp.RabbitMQMessageProducer;

@SpringBootApplication(
        scanBasePackages = {
                "tech.hdurmaz.notification",
                "tech.hdurmaz.amqp",
        }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    /*@Bean
    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer,
                                        NotificationConfig notificationConfig) {
        return args -> {
            producer.publish(new Person("Hakan",1),notificationConfig.getInternalExchange(),notificationConfig.getInternalNotificationRoutingKey());
        };
    }

    record Person(String name,int age){}*/
}
