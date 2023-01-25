package br.com.ecommerceproject.exceptions;

public class InvalidOptionException extends RuntimeException {
    public InvalidOptionException(String msg){
        super(msg);
    }
}
