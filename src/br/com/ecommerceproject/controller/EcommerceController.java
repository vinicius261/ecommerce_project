package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.interfaces.Products;
import br.com.ecommerceproject.model.*;
import br.com.ecommerceproject.validationcode.GeneralValidations;

import java.util.List;

public class EcommerceController {
    private DataBase dataBase;
    private Costumer loggedInCostumer;
    private GeneralValidations generalValidations;

    public EcommerceController(DataBase dataBase,Costumer loggedInCostumer){
        this.dataBase = dataBase;
        this.loggedInCostumer = loggedInCostumer;
        this.generalValidations = new GeneralValidations();
    }

    public List<Products> availableProducts(){
        return dataBase.getProducts();
    }

    public void sendToCart(String productCode) {
        ProductSearchController search =new ProductSearchController(dataBase);
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
