package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.exceptions.PasswordIncorrectException;
import br.com.ecommerceproject.model.Costumer;
import br.com.ecommerceproject.view.LoginView;

public class LoginController {
    private DataBase dataBase;
    public SearchCostumerController searchCostumer;

    public  LoginController(DataBase dataBase){
        this.dataBase = dataBase;
        this.searchCostumer = new SearchCostumerController(dataBase);
    }

    public Costumer loginCheck(String email, String password) {
        Costumer logginInCostumer = searchCostumer.searchCostumer(email);
        Costumer loggedInCostumer = null;

        LoginCheckController loginCheckController = new LoginCheckController();
        try {
            loginCheckController.loginCheck(logginInCostumer , password);
            loggedInCostumer = logginInCostumer;

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
