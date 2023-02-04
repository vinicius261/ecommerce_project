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
        this.loginView = new LoginView(dataBase);
        this.mainMenuController = new MainMenuController();
    }

    public void showMainMenuView(){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("\n               Bem vindo ao Mercado Saúde!\n");

        processMainMenuChoice();
    }

    private void processMainMenuChoice() {
        System.out.println( "O seu Ecommerce de itens esportivos e alimentação saudável.\n");
        System.out.println("\nJá tem uma conta no Mercado Saúde?\n\n 1 - Sim! \n 2 - Não,quero fazer!");
        System.out.println("--------------------------------------------------------------------------------------------");

        try {
            switch (mainMenuController.getMainMenuChoice(scanner.nextLine())) {
                case 1:
                    loginView.showLoginView();
                    break;
                case 2:
                    costumerRegistrationView.showCostumerRegistrationView();
            }
        }catch (InvalidOptionException ex){
            System.out.println(ex.getMessage());
            showMainMenuView();
        }
    }
}
