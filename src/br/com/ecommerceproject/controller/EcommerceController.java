package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.interfaces.Products;
import br.com.ecommerceproject.model.Costumer;
import br.com.ecommerceproject.model.GymEquipment;
import br.com.ecommerceproject.model.VitaminsAndSuplements;
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
        if ( productType instanceof VitaminsAndSuplements){
            cartProduct = new VitaminsAndSuplements(productType.getName(),
                    productType.getPrice(), 1, productType.getDescription(),
                    ((VitaminsAndSuplements) productType).getBestBeforeDate());
        }else {
            cartProduct = new GymEquipment(productType.getName(), +
                    productType.getPrice(), 1, productType.getDescription());
        }

        cart.addToCart(cartProduct);
    }
}
