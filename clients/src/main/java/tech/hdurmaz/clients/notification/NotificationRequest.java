package tech.hdurmaz.clients.notification;

public record NotificationRequest(
        String identityNumber,
        String toCustomerName,
        String message
) {
}
