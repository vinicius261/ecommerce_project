package br.com.ecommerceproject.view;

import br.com.ecommerceproject.controller.EcommerceController;
import br.com.ecommerceproject.controller.SearchProductController;
import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.interfaces.Perishable;
import br.com.ecommerceproject.interfaces.Products;
import br.com.ecommerceproject.model.Costumer;
import br.com.ecommerceproject.validationcode.Validations;

import java.util.List;
import java.util.Scanner;

public class EcommerceView {

    private DataBase dataBase;
    private Costumer loggedInCostumer;
    private EcommerceController controller;
    private Scanner scanner;
    private Validations validations;
    public EcommerceView(DataBase dataBase, Costumer loggedInCostumer) {
        this.dataBase = dataBase;
        this.loggedInCostumer = loggedInCostumer;
        this.controller = new EcommerceController(dataBase, loggedInCostumer);
        this. scanner = new Scanner(System.in);
        this.validations = new Validations();
    }

    public void view(){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("\n               Produtos\n" + "\nPara ver em detalhes um produto digite o número dele.\n" +
                "Para ver seu carrinho digite 'c'.\nPara sair digite 's'.\n");

        showAllProducts();

        String costumerOption = scanner.nextLine();

        if (costumerOption.equalsIgnoreCase("s")) {
            System.out.println("Volte sempre!");
            MainMenuView logout = new MainMenuView(dataBase);
            logout.menu();
        }else if(costumerOption.equalsIgnoreCase("c")){
            CartView cartView = new CartView(dataBase, loggedInCostumer);
            cartView.view();
        }

        showProductDetails(costumerOption);

        System.out.println("\nDeseja adicionar o produto ao carrinho?\nDigite 's' para adicionar ou qualquer tecla " +
                "para voltar aos produtos.");

        addToCartOption(scanner.nextLine(), costumerOption);

        System.out.println("\nProduto adicionado ao Carrinho!\nPara continuar comprando digite qualquer tecla.\n"+
                "Para ver o Carrinho digite 1");
        System.out.println("--------------------------------------------------------------------------------------------");

        ecommerceOption(scanner.nextLine());
    }

    public void showAllProducts() {
        List<Products> products = controller.availableProducts();

        for (Products product : products) {
            System.out.println(product.getCode() + " - " + product.getName() + " - " + product.getPrice());
        }
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    public void showProductDetails(String productCode) {
        Products product = null;

        try {
            SearchProductController search = new SearchProductController(dataBase);
            product = search.searchStorageProduct(productCode);
        }catch (IndexOutOfBoundsException ex){
            System.out.println("Esse código não corresponde a nenhum produto.");
            view();
        }

        System.out.println("--------------------------------------------------------------------------------------------");
        if (product instanceof Perishable){
            System.out.println("\n               " + product.getName() + "\n" + "Preço: " + product.getPrice() + "R$" +
                    "\nEstoque: " + product.getQuantity() + "\nDescrição: " + product.getDescription() +
                    "\nData de vencimento: " + ((Perishable) product).getBestBeforeDate());
        }else {
            System.out.println("\n               " + product.getName() + "\n" + "Preço: " + product.getPrice() + "R$" +
                    "\nEstoque: " + product.getQuantity() + "\nDescrição: " + product.getDescription());
        }
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    public void addToCartOption(String input, String productCode) {
        if(input.equalsIgnoreCase("s")) {
            controller.sendToCart(productCode);
        }else {
            view();
        }
    }

    public void ecommerceOption (String input){
        Integer option = 0;

        try {
            option = Integer.parseInt(input);
            if (option == 1) {
                CartView cart = new CartView(dataBase, loggedInCostumer);
                cart.view();
            }
        }catch (NumberFormatException ex){
            view();
        }
        view();
    }
}
