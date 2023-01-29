package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.exceptions.InvalidPasswordException;
import br.com.ecommerceproject.interfaces.Validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordInputController implements Validations {
    @Override
    public void validate(String input) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,}$");
        Matcher matcher = pattern.matcher(input);

        if (! matcher.matches()){
            throw new InvalidPasswordException("Insira uma senha com:\nNo mínimo 6 caracteres" +
                    "\nUma letra maiúscula\nUma letra minúscula\nUm número");
        }
    }
}
