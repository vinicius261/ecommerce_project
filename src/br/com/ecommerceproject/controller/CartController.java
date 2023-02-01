package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.interfaces.Products;
import br.com.ecommerceproject.model.Costumer;
import br.com.ecommerceproject.view.PaymentView;
import br.com.ecommerceproject.view.CartView;
import br.com.ecommerceproject.view.EcommerceView;

import java.util.List;
import java.util.Scanner;

public class CartController {
    private DataBase dataBase;
    private Costumer loggedInCostumer;
    private StorageController storage;
    private SearchProductController search;
    private Scanner scanner;

    public CartController(DataBase dataBase, Costumer loggedInCostumer) {
        this.dataBase = dataBase;
        this.loggedInCostumer = loggedInCostumer;
        this.storage = new StorageController(dataBase);
        this.search = new SearchProductController(dataBase);
        this.scanner = new Scanner(System.in);
    }

    public void cartViewOption(String input) {
        CartView view = new CartView(dataBase, loggedInCostumer);

        if (input.length() == 1){
            switch (input){
                case "0":
                    EcommerceView ecommerceView = new EcommerceView(dataBase, loggedInCostumer);
                    ecommerceView.view();

                case "p":
                    PaymentView paymentView = new PaymentView(dataBase, loggedInCostumer);
                    paymentView.paymentView();
            }
        }

        String[] inputs = null;
        String inputProductCode = null;
        String actionOption = null;

        try {
            inputs = input.split("/");
            inputProductCode = inputs[0];
            actionOption = inputs[1];
        }catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Insira uma opção válida");
            view.view();
        }

        Products cartProduct = search.searchCartProduct(inputProductCode, loggedInCostumer);
        Products productType = search.searchStorageProduct(inputProductCode);
        Integer cartProductIndex = loggedInCostumer.getCart().indexOf(cartProduct);

        if (productType != null && cartProduct != null) {

            switch (actionOption) {
                case "r":
                    loggedInCostumer.getCart().remove(cartProduct);
                    System.out.println("\nProduto removido do carrinho.");
                    view.view();
                    break;

                case "+":
                    if (productType.getQuantity() > 0) {
                        loggedInCostumer.getCart().get(cartProductIndex).increaseQuantity();
                        storage.decreaseProductQuantity(cartProduct.getCode());

                        System.out.println(loggedInCostumer.getCart().get(cartProductIndex).getQuantity() +
                                " unidades de " + loggedInCostumer.getCart().get(cartProductIndex).getName());

                        view.view();
                    } else {
                        System.out.println("\nEstoque insuficiente no momento.");
                        view.view();
                    }
                    break;

                case "-":
                    if (cartProduct.getQuantity() == 1) {
                        loggedInCostumer.getCart().remove(cartProduct);
                        storage.increaseProductQuantity(productType.getCode());
                        System.out.println("Produto removido do carrinho.");
                    } else {
                        loggedInCostumer.getCart().get(cartProductIndex).decreaseQuantity();
                        storage.increaseProductQuantity(productType.getCode());
                        System.out.println(loggedInCostumer.getCart().get(cartProductIndex).getQuantity() +
                                " unidades de " + loggedInCostumer.getCart().get(cartProductIndex).getName());
                    }
                    view.view();
                    break;

                default:
                    System.out.println("Algo deu errado.");
                    view.view();
                    break;
            }
        }else {
            System.out.println("Código não encontrado.");
            view.view();
        }
    }

    public Double totalValue () {
        Double totalValue = 0.0;

        for (Products product : loggedInCostumer.getCart()) {
            totalValue = totalValue + product.getPrice()* product.getQuantity();
        }
        return totalValue;
    }

    public void addToCart(Products cartProduct) {
        Products product = search.searchCartProduct(cartProduct.getCode(), loggedInCostumer);

        if (product == null){
            loggedInCostumer.addToCart(cartProduct);
            storage.decreaseProductQuantity(cartProduct.getCode());
        }else{
            product.increaseQuantity();
        }
    }

    public List<Products> cartProducts() {
        return loggedInCostumer.getCart();
    }
}

