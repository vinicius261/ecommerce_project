package br.com.ecommerceproject.view;

import br.com.ecommerceproject.controller.MainMenuController;
import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.exceptions.InvalidOptionException;

import java.util.Scanner;

public class MainMenuView {
    private static Scanner scanner;
    private CostumerRegistrationView costumerRegistrationView;
    private LoginView loginView;

    private MainMenuController mainMenuController;

    public MainMenuView(DataBase dataBase){
        this.scanner = new Scanner(System.in);
        this.costumerRegistrationView = new CostumerRegistrationView(dataBase);
        this.loginView = new LoginView();
        this.mainMenuController = new MainMenuController();
    }

    public void menu(){
        System.out.println("Já tem uma conta no Mercado Saúde?\n 1 - Sim! \n 2 - Não,quero fazer!");

        try {
            switch (mainMenuController.menuOption(scanner.nextLine())) {
                case 1:
                    loginView.login();
                    break;
                case 2:
                    costumerRegistrationView.registration();
            }
        }catch (InvalidOptionException ex){
            System.out.println(ex.getMessage());
            menu();
        }
    }
}
