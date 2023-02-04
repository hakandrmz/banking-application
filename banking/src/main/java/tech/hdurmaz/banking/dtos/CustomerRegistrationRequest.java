package tech.hdurmaz.banking.dtos;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        Integer income,
        String identityNumber
) {

}
