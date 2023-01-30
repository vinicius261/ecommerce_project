package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.interfaces.Perishable;
import br.com.ecommerceproject.interfaces.Products;
import br.com.ecommerceproject.model.Costumer;
import br.com.ecommerceproject.view.CartView;
import br.com.ecommerceproject.view.EcommerceView;
import br.com.ecommerceproject.view.MainMenuView;

public class EcommerceController {
    private DataBase dataBase;
    private Costumer loggedInCostumer;

    public EcommerceController(DataBase dataBase,Costumer loggedInCostumer){
        this.dataBase = dataBase;
        this.loggedInCostumer = loggedInCostumer;
    }

    public void showAllProducts() {
        for (int i = 0; i < dataBase.getProducts().size(); i++){
            Products product = dataBase.getProducts().get(i);
            System.out.println(i+1 + " - " + product.getName() + " - " + product.getPrice());
        }
    }

    public Integer showProductDetails(String productCode) {
        EcommerceView view = new EcommerceView(dataBase, loggedInCostumer);
        Integer index = -1;
        Products product = null;

        try {
            index = Integer.parseInt(productCode)-1;
        }catch (NumberFormatException ex){
            System.out.println("Digite o número a frente do produto");
            view.ecommerceView();
        }

        try {
            product =  dataBase.getProducts().get(index);
        }catch (IndexOutOfBoundsException ex){
            System.out.println("Esse código não corresponde a nenhum produto.");
            view.ecommerceView();
        }


        if (product instanceof Perishable){
            System.out.println("\n               " + product.getName() + "\n" + "Preço: " + product.getPrice() +
                    "\nEstoque: " + product.getQuantity() + "\nDescrição: " + product.getDescription() +
                    "\nData de vencimento: " + ((Perishable) product).getBestBeforeDate());
        }else {
            System.out.println("\n               " + product.getName() + "\n" + "Preço: " + product.getPrice() +
                    "\nEstoque: " + product.getQuantity() + "\nDescrição: " + product.getDescription());
        }

        return index+1;
    }

    public void addToCart(Integer productCode) {
        int productIndex = productCode  - 1;
        EcommerceView view = new EcommerceView(dataBase, loggedInCostumer);
        Products product =  dataBase.getProducts().get(productIndex);
        loggedInCostumer.addToCart(product);
    }

    public void addToCartOption(String input, Integer productCode) {
        EcommerceView view = new EcommerceView(dataBase, loggedInCostumer);

        if(input.equalsIgnoreCase("s")) {
            addToCart(productCode);
        }else {
            view.ecommerceView();
        }
    }

    public void ecommerceOption (String input){
        EcommerceView view = new EcommerceView(dataBase, loggedInCostumer);
        Integer option = 0;

        try {
            option = Integer.parseInt(input);
        }catch (NumberFormatException ex){
            view.ecommerceView();
        }

        if (option == 1) {
            CartView cart = new CartView(loggedInCostumer);
            cart.cartView();
        }else {
            view.ecommerceView();
        }
    }

}
