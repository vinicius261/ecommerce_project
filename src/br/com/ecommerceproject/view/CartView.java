package br.com.ecommerceproject.view;


import br.com.ecommerceproject.controller.CartController;
import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.interfaces.Products;
import br.com.ecommerceproject.model.Costumer;

import java.util.List;
import java.util.Scanner;

public class CartView{
    private DataBase dataBase;
    private Costumer loggedInCostumer;
    private CartController cartController;
    private Scanner scanner;

    public CartView(DataBase dataBase,Costumer loggedInCostumer){
        this.dataBase=dataBase;
        this.loggedInCostumer=loggedInCostumer;
        this.cartController = new CartController(dataBase, loggedInCostumer);
        this.scanner=new Scanner(System.in);
        }

    public void showCartview(){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("\n		Seu Carrinho");

        showCartProducts();

        showCartMenu();

        }

    public void showCartProducts() {
        List<Products> products = cartController.getCartProducts();

        if(products.isEmpty()){
            System.out.println("Seu carrinho esta vazio =/");
        }else {

            System.out.println("\nNº - Produto  	 Quantidade	    Valor total do item ");

            products.forEach(product -> {System.out.println(product.getCode() + " - " + product.getName() + "   -   " + product.getQuantity() + "   -   "
                    + product.getPrice() * product.getQuantity());});

            Double totalValue = cartController.calculateTotalValue();
            System.out.println("\n\n			    Valor total do Carrinho: " + totalValue + "R$");
        }
    }

    public  void showCartMenu(){
        System.out.println("\n\nEm relação a cada produto digite seu código, uma barra e um sinal de ação." +
                "\nExemplo: '7893/-' ou '7894/+ ou '7891/r" +
                "\n\nUse os sinais de mais '+' e de menos '-' para aumentar ou diminuir a quantidade." +
                "\nUse a letra 'r'para remover o produto." +
                "\n\nAgora, digite apenas 'p' para ir ao pagamento ou apenas '0' para voltar as compras.");
        System.out.println("--------------------------------------------------------------------------------------------");

        cartController.processMenuCartViewChoice(scanner.nextLine());
    }
}