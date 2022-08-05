package tech.hdurmaz.banking.exceptions;

public class CustomerIsAlreadyExistException extends RuntimeException {
    public CustomerIsAlreadyExistException(String message) {
        super(message);
    }
}
