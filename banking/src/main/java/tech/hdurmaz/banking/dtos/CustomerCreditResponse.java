package tech.hdurmaz.banking.dtos;

import lombok.Builder;

@Builder
public record CustomerCreditResponse(String message, String customerId, Integer amount) {

}