package br.com.ecommerceproject.view;

import br.com.ecommerceproject.controller.CostumerRegistrationController;
import br.com.ecommerceproject.controller.EmailInputController;
import br.com.ecommerceproject.controller.NameInputController;
import br.com.ecommerceproject.controller.PasswordInputController;
import br.com.ecommerceproject.database.DataBase;

import java.util.Scanner;

public class CostumerRegistrationView {
    private Scanner scanner;
    private CostumerRegistrationController costumerRegistrationController;
    private DataBase dataBase;

    public CostumerRegistrationView(DataBase dataBase) {
        this.costumerRegistrationController = new CostumerRegistrationController(dataBase);
        this.dataBase = dataBase;
        this.scanner = new Scanner(System.in);
    }

    public void showCostumerRegistrationView(){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("\n               Área de cadastro\n");
        processRegistration();

        LoginView loginView = new LoginView(dataBase);
        loginView.showLoginView();

    }

    public void processRegistration() {
        String name = costumerRegistrationController.validateCostumerData(new NameInputController(), "Qual o seu nome?");

        String email = costumerRegistrationController.validateCostumerData(new EmailInputController(dataBase), "Insira um email: ");

        String password = costumerRegistrationController.validateCostumerData(new PasswordInputController(), "Insira uma senha com:\n\n" +
                "No mínimo 6 caracteres\nUma letra maiúscula\nUma letra minúscula\nUm número");

        costumerRegistrationController.createNewRecord(name, email, password);
        System.out.println("Cadastro realizado!");
        System.out.println("--------------------------------------------------------------------------------------------");
    }
}
