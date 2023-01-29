package br.com.ecommerceproject.model;

public class Costumer {
    private String name;
    private String login;
    private String password;
    private Cart cart;

    public Costumer(String name, String login, String password){
        this.name = name;
        this.login = login;
        this.password = password;
        this.cart = new Cart();
    }
}
