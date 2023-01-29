package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.exceptions.InvalidInputNameException;
import br.com.ecommerceproject.exceptions.InvalidPasswordException;
import br.com.ecommerceproject.interfaces.Validations;

import javax.naming.InvalidNameException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameInputController implements Validations {
    @Override
    public void validate(String input) {
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            throw new InvalidInputNameException("Insira uma senha com:\nNo mínimo 6 caracteres+" +
                    "\nUma letra maiúscula\nUma letra minúscula\nUm número");
        }
    }
}

