package br.com.ecommerceproject.view;

import br.com.ecommerceproject.controller.EcommerceController;
import br.com.ecommerceproject.controller.ProductSearchController;
import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.interfaces.Perishable;
import br.com.ecommerceproject.interfaces.Products;
import br.com.ecommerceproject.model.Costumer;
import br.com.ecommerceproject.validationcode.GeneralValidations;

import java.util.List;
import java.util.Scanner;

public class EcommerceView {

    private DataBase dataBase;
    private Costumer loggedInCostumer;
    private EcommerceController ecommerceController;
    private Scanner scanner;
    private GeneralValidations generalValidations;
    public EcommerceView(DataBase dataBase, Costumer loggedInCostumer) {
        this.dataBase = dataBase;
        this.loggedInCostumer = loggedInCostumer;
        this.ecommerceController = new EcommerceController(dataBase, loggedInCostumer);
        this. scanner = new Scanner(System.in);
        this.generalValidations = new GeneralValidations();
    }

    public void showEcommerceView(){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("\n               Produtos\n");

        showAllEcommerceProducts();

        processEcommerceMenuChoice(scanner.nextLine());

    }

    public void showAllEcommerceProducts() {
        List<Products> products = ecommerceController.availableProducts();

        products.forEach(product -> { System.out.println(product.getCode() + " - "
                + product.getName() + " - " + product.getPrice());});

        System.out.println("--------------------------------------------------------------------------------------------");
    }

    public void processEcommerceMenuChoice(String input){
        System.out.println("\nPara ver em detalhes um produto digite o número dele.\n" +
                "Para ver seu carrinho digite 'c'.\nPara sair digite 's'.\n");

        if (input.equalsIgnoreCase("s")) {
            System.out.println("Volte sempre!");
            MainMenuView logout = new MainMenuView(dataBase);
            logout.showMainMenuView();
        }else if(input.equalsIgnoreCase("c")){
            CartView cartView = new CartView(dataBase, loggedInCostumer);
            cartView.showCartview();
        }

        showProductDetails(input);
    }

    public void showProductDetails(String productCode) {
        Products product = null;

        ProductSearchController search = new ProductSearchController(dataBase);
        product = search.searchStorageProduct(productCode);

        if(product == null) {
            System.out.println("Esse código não corresponde a nenhum produto.");
            showEcommerceView();
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

        processProductDetailMenuChoice(scanner.nextLine(), productCode);
    }

    public void processProductDetailMenuChoice(String input, String productCode) {
        System.out.println("\nDeseja adicionar o produto ao carrinho?\nDigite 's' para adicionar ou qualquer tecla " +
                "para voltar aos produtos.");

        if(input.equalsIgnoreCase("s")) {
            ecommerceController.sendToCart(productCode);

            System.out.println("\nProduto adicionado ao Carrinho!\nPara continuar comprando digite qualquer tecla.\n"+
                    "Para ver o Carrinho digite 'c'");
            System.out.println("--------------------------------------------------------------------------------------------");

            if(scanner.nextLine().equalsIgnoreCase("c")){
                CartView cartView = new CartView(dataBase, loggedInCostumer);
                cartView.showCartview();
            }
        }else {
            showEcommerceView();
        }
    }
}
