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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
