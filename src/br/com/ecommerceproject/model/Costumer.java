package br.com.ecommerceproject.model;


import br.com.ecommerceproject.interfaces.Products;

import java.util.ArrayList;
import java.util.List;

public class Costumer {
    private String name;
    private String login;
    private String password;
    private List<Products> cart;

    public Costumer(String name, String login, String password){
        this.name = name;
        this.login = login;
        this.password = password;
        this.cart = new ArrayList<Products>();
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public boolean validatePassword(String input) {
        if (input.equals(this.password)){
            return true;
        }else {
            return  false;
        }
    }

    public List<Products> getCart() {
        return cart;
    }

    public void addToCart(Products product) {
        this.cart.add(product);
    }
}
