package tech.hdurmaz.banking;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
