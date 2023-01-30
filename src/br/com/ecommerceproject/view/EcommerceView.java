package br.com.ecommerceproject.view;

import br.com.ecommerceproject.controller.EcommerceController;
import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.model.Costumer;

import java.util.Scanner;

public class EcommerceView {

    private DataBase dataBase;
    private Costumer loggedInCostumer;
    private EcommerceController controller;
    private Scanner scanner;
    public EcommerceView(DataBase dataBase, Costumer loggedInCostumer) {
        this.dataBase = dataBase;
        this.loggedInCostumer = loggedInCostumer;
        this.controller = new EcommerceController(dataBase, loggedInCostumer);
        this. scanner = new Scanner(System.in);
    }

    public void ecommerceView(){
        System.out.println("\n               Produtos\n" + "Para ver em detalhes um produto digite o número dele\n" +
                "Para sair digite qualquer 0");

        controller.showAllProducts();

        Integer productCode = controller.showProductDetails(scanner.nextLine());

        if (productCode == 0) {
            System.out.println("Volte sempre!");
            MainMenuView logout = new MainMenuView(dataBase);
            logout.menu();
        }

        System.out.println("\nDeseja adicionar o produto ao carrinho?\nDigite 's' para adicionar ou qualquer tecla " +
                "para voltar aos produtos.");
        controller.addToCartOption(scanner.nextLine(), productCode);

        System.out.println("\nProduto adicionado ao Carrinho!\nPara continuar comprando digite qualquer tecla.\n"+
                "Para ver o Carrinho digite 1");

        controller.ecommerceOption(scanner.nextLine());
    }
}
