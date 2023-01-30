package br.com.ecommerceproject.view;

import br.com.ecommerceproject.model.Costumer;

public class CartView {
    private Costumer loggedInCostumer;

    public CartView(Costumer loggedInCostumer){
        this.loggedInCostumer = loggedInCostumer;
    }
    public void cartView() {
        System.out.println("\n               Seu Carrinho");
        System.out.println(loggedInCostumer.getCart().get(0).getName());
    }
}
