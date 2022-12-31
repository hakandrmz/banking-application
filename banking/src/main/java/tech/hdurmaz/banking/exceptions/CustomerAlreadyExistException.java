package tech.hdurmaz.banking.exceptions;

public class CustomerAlreadyExistException extends RuntimeException {

    public CustomerAlreadyExistException(String message) {
        super(message);
    }
}
