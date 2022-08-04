package tech.hdurmaz.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerName,
        String message
) {
}
