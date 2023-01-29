package br.com.ecommerceproject.exceptions;

public class PasswordIncorrectException extends RuntimeException {
    public PasswordIncorrectException(String msg) {
        super(msg);
    }
}
