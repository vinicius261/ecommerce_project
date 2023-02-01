package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.interfaces.Products;
import br.com.ecommerceproject.model.Costumer;

public class SearchProductController {
    private DataBase dataBase;

    public SearchProductController(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public Products searchStorageProduct(String code){
        Products wantedProduct = null;

        for (Products product: dataBase.getProducts()) {
            if(product.getCode().equals(code)){
                wantedProduct = product;
            }
        }

        return wantedProduct;
    }

    public Products searchCartProduct(String code, Costumer loggedInCostumer){
        Products wantedProduct = null;

        for (Products product: loggedInCostumer.getCart()) {
            if(product.getCode().equals(code)){
                wantedProduct = product;
            }
        }

        return wantedProduct;
    }
}
