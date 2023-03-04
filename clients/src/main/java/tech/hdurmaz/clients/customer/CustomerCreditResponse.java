package tech.hdurmaz.clients.customer;

import lombok.Builder;

@Builder
public record CustomerCreditResponse(String message, String customerId, Integer amount) {

}