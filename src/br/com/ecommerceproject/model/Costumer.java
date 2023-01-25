package br.com.ecommerceproject.model;

public class Costumer {
    private String login;
    private String password;
    private Cart cart;

    public Costumer(String login, String password){
        this.login = login;
        this.password = password;
        this.cart = new Cart();
    }
}
