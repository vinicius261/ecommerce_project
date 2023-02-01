package br.com.ecommerceproject.view;

import br.com.ecommerceproject.controller.CostumerRegistrationController;
import br.com.ecommerceproject.controller.EmailInputController;
import br.com.ecommerceproject.controller.NameInputController;
import br.com.ecommerceproject.controller.PasswordInputController;
import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.model.Costumer;

import java.util.Scanner;

public class CostumerRegistrationView {
    private Scanner scanner;
    private CostumerRegistrationController controller;
    private DataBase dataBase;

    public CostumerRegistrationView(DataBase dataBase) {
        this.controller = new CostumerRegistrationController(dataBase);
        this.dataBase = dataBase;
        this.scanner = new Scanner(System.in);
    }

    public void view(){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("\n               Área de cadastro\n");
        registration();

        LoginView loginView = new LoginView(dataBase);
        loginView.login();

    }

    public void registration() {
        String name = controller.validate(new NameInputController(), "Qual o seu nome?");

        String email = controller.validate(new EmailInputController(dataBase), "Insira um email: ");

        String password = controller.validate(new PasswordInputController(), "Insira uma senha com:\n\n" +
                "No mínimo 6 caracteres\nUma letra maiúscula\nUma letra minúscula\nUm número");

        controller.createNewRecord(name, email, password);
        System.out.println("Cadastro realizado!\n");
        System.out.println("--------------------------------------------------------------------------------------------");
    }
}
