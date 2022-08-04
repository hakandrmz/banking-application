package tech.hdurmaz.clients.credit;

import lombok.Builder;

@Builder
public record CreditCheckResponse(Integer customerId, Integer amount) {

}
