package br.com.ecommerceproject.view;

import br.com.ecommerceproject.controller.LoginController;
import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.model.Costumer;

import java.util.Scanner;

public class LoginView {
    private DataBase dataBase;
    private LoginController loginController;
    private Scanner scanner;
    public LoginView(DataBase dataBase){
        this.dataBase = dataBase;
        this.loginController = new LoginController(dataBase);
        this.scanner = new Scanner(System.in);
    }

    public void showLoginView(){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("\n               Área de login\n");

        Costumer loggedInCostumer = login();

        EcommerceView ecommerce = new EcommerceView(dataBase, loggedInCostumer);
        ecommerce.showEcommerceView();
    }
    public Costumer login() {
        System.out.println("Insira seu email cadastrado: ");
        String login = scanner.nextLine();

        System.out.println("Insira sua senha: ");
        String password = scanner.nextLine();

        Costumer loggedInCostumer = loginController.processLogin(login, password);

        System.out.println("Login efetuado, " + loggedInCostumer.getName() +"!");
        System.out.println("--------------------------------------------------------------------------------------------");

        return loggedInCostumer;
    }
}
