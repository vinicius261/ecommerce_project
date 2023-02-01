package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.interfaces.Products;
import br.com.ecommerceproject.model.*;
import br.com.ecommerceproject.validationcode.Validations;

import java.util.List;

public class EcommerceController {
    private DataBase dataBase;
    private Costumer loggedInCostumer;
    private Validations validations;

    public EcommerceController(DataBase dataBase,Costumer loggedInCostumer){
        this.dataBase = dataBase;
        this.loggedInCostumer = loggedInCostumer;
        this.validations = new Validations();
    }

    public List<Products> availableProducts(){
        return dataBase.getProducts();
    }

    public void sendToCart(String productCode) {
        SearchProductController search =new SearchProductController(dataBase);
        CartController cart = new CartController(dataBase,loggedInCostumer);

        Products productType = search.searchStorageProduct(productCode);

        Products cartProduct;
        if ( productType instanceof CartVitaminsAndSuplements){
            cartProduct = new CartVitaminsAndSuplements(productType.getName(),
                    productType.getPrice(), 1, productType.getDescription(),
                    ((CartVitaminsAndSuplements) productType).getBestBeforeDate(), productType.getCode());
        }else {
            cartProduct = new CartGymEquipment(productType.getName(), +
                    productType.getPrice(), 1, productType.getDescription(), productType.getCode());
        }

        cart.addToCart(cartProduct);
    }
}
