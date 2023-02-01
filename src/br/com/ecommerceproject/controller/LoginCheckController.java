package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.exceptions.PasswordIncorrectException;
import br.com.ecommerceproject.model.Costumer;

public class LoginCheckController {

    public void loginCheck(Costumer logginInCostumer, String password){
        try {
            if (! logginInCostumer.validatePassword(password)) {
                throw new PasswordIncorrectException("Dados incorretos");
            }
        }catch (IndexOutOfBoundsException ex){
           throw new IndexOutOfBoundsException();
        }catch (NumberFormatException ex){
            throw new NullPointerException();
        }
    }

}
