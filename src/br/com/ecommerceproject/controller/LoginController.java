package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.exceptions.PasswordIncorrectException;
import br.com.ecommerceproject.model.Costumer;
import br.com.ecommerceproject.view.LoginView;

public class LoginController {
    private DataBase dataBase;

    public  LoginController(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public Costumer loginCheck(String email, String password) {
        int index = 0;
        Costumer LogginInCostumer = null;
        Costumer loggedInCostumer = null;

        for (Costumer costumer: dataBase.getCostumers()) {
            if(costumer.getLogin().equals(email)){
                index = dataBase.getCostumers().indexOf(costumer);
                LogginInCostumer = costumer;
            }
        }

        LoginCheckController loginCheckController = new LoginCheckController();
        try {
            loginCheckController.loginCheck(dataBase, index, password);
           loggedInCostumer = LogginInCostumer;

        }catch (IndexOutOfBoundsException ex){
            System.out.println("Dados incorretos.\n");
            LoginView view = new LoginView(dataBase);
            view.login();

        }catch (PasswordIncorrectException ex) {
            System.out.println(ex.getMessage());
            LoginView view = new LoginView(dataBase);
            view.login();
        }

        return loggedInCostumer;
    }
}
