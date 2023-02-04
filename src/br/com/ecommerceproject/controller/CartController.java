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
    private ProductSearchController search;
    private Scanner scanner;

    public CartController(DataBase dataBase, Costumer loggedInCostumer) {
        this.dataBase = dataBase;
        this.loggedInCostumer = loggedInCostumer;
        this.storage = new StorageController(dataBase);
        this.search = new ProductSearchController(dataBase);
        this.scanner = new Scanner(System.in);
    }

    public void processMenuCartViewChoice(String input) {
        CartView cartView = new CartView(dataBase, loggedInCostumer);

        if (input.length() == 1){
            switch (input){
                case "0":
                    EcommerceView ecommerceView = new EcommerceView(dataBase, loggedInCostumer);
                    ecommerceView.showEcommerceView();

                case "p":
                    Double totalValue = calculateTotalValue();
                    if (totalValue> 0) {
                        PaymentView paymentView = new PaymentView(dataBase, loggedInCostumer);
                        paymentView.showPaymentView(totalValue);
                    }
            }
        }

        String[] inputs = null;
        String inputProductCode = null;
        String inputActionOption = null;

        try {
            inputs = input.split("/");
            inputProductCode = inputs[0];
            inputActionOption = inputs[1];
        }catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Insira uma opção válida");
            cartView.showCartview();
        }

        Products cartProduct = search.searchCartProduct(inputProductCode, loggedInCostumer);
        Products productType = search.searchStorageProduct(inputProductCode);
        Integer cartProductIndex = loggedInCostumer.getCart().indexOf(cartProduct);

        if (productType != null && cartProduct != null) {

            switch (inputActionOption) {
                case "r":
                    loggedInCostumer.removeProduct(cartProduct);
                    System.out.println("\nProduto removido do carrinho.");
                    cartView.showCartview();
                    break;

                case "+":
                    if (productType.getQuantity() > 0) {
                        loggedInCostumer.getCart().get(cartProductIndex).increaseQuantity();
                        storage.decreaseProductTypeQuantity(cartProduct.getCode());

                        System.out.println(loggedInCostumer.getCart().get(cartProductIndex).getQuantity() +
                                " unidades de " + loggedInCostumer.getCart().get(cartProductIndex).getName());

                        cartView.showCartview();
                    } else {
                        System.out.println("\nEstoque insuficiente no momento.");
                        cartView.showCartview();
                    }
                    break;

                case "-":
                    if (cartProduct.getQuantity() == 1) {
                        loggedInCostumer.removeProduct(cartProduct);
                        storage.increaseProductTypeQuantity(productType.getCode());
                        System.out.println("Produto removido do carrinho.");
                    } else {
                        loggedInCostumer.getCart().get(cartProductIndex).decreaseQuantity();
                        storage.increaseProductTypeQuantity(productType.getCode());
                        System.out.println(loggedInCostumer.getCart().get(cartProductIndex).getQuantity() +
                                " unidades de " + loggedInCostumer.getCart().get(cartProductIndex).getName());
                    }
                    cartView.showCartview();
                    break;

                default:
                    System.out.println("Algo deu errado.");
                    cartView.showCartview();
                    break;
            }
        }else {
            System.out.println("Código não encontrado.");
            cartView.showCartview();
        }
    }

    public Double calculateTotalValue() {
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
        }else{
            product.increaseQuantity();
        }

        storage.decreaseProductTypeQuantity(cartProduct.getCode());
    }

    public List<Products> getCartProducts() {
        return loggedInCostumer.getCart();
    }
}

