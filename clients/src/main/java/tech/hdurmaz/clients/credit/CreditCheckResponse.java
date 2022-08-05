package tech.hdurmaz.clients.credit;

import lombok.Builder;

@Builder
public record CreditCheckResponse(String message, String identityNumber, Integer amount) {

}
