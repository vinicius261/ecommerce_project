package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.exceptions.InvalidPasswordException;
import br.com.ecommerceproject.exceptions.PasswordIncorrectException;

public class LoginCheckController {

    public void loginCheck(DataBase dataBase, int index, String password){
        try {
            if (!dataBase.getCostumers().get(index).validatePassword(password)) {
                throw new PasswordIncorrectException("Dados incorretos");
            }
        }catch (IndexOutOfBoundsException ex){
           throw new IndexOutOfBoundsException();
        }
    }

}
