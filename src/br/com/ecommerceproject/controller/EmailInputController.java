package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.exceptions.InvalidEmailException;
import br.com.ecommerceproject.interfaces.Validations;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailInputController implements Validations {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void validate(String input) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(input);

        if (! matcher.matches()){
            throw new InvalidEmailException("Insira um email válido");
        }
    }
}
