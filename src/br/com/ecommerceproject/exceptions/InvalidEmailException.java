package br.com.ecommerceproject.exceptions;

import javax.management.RuntimeMBeanException;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String msg) {
        super(msg);
    }
}
