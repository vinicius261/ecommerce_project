package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.exceptions.PasswordIncorrectException;
import br.com.ecommerceproject.model.Costumer;
import br.com.ecommerceproject.view.LoginView;

public class LoginController {
    private DataBase dataBase;
    public CostumerSearchController costumerSearch;

    public  LoginController(DataBase dataBase){
        this.dataBase = dataBase;
        this.costumerSearch = new CostumerSearchController(dataBase);
    }

    public Costumer processLogin(String email, String password) {
        Costumer loggedInCostumer = null;
        
        try {
            loggedInCostumer = confirmLoginDetails(email , password);;

        }catch (IndexOutOfBoundsException | NullPointerException | PasswordIncorrectException ex){
            System.out.println("Dados incorretos.\n");
            LoginView loginView = new LoginView(dataBase);
            loggedInCostumer = loginView.login();

        }

        return loggedInCostumer;
    }

    public Costumer confirmLoginDetails(String email, String password){
        Costumer logginInCostumer;

        try {
            logginInCostumer = costumerSearch.searchCostumer(email);
            if (! logginInCostumer.validatePassword(password)) {
                throw new PasswordIncorrectException("Dados incorretos");
            }
        }catch (IndexOutOfBoundsException ex){
            throw new IndexOutOfBoundsException();
        }catch (NumberFormatException ex){
            throw new NullPointerException();
        }

        return logginInCostumer;
    }
}
