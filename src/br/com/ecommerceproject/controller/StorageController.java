package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.interfaces.Products;
import br.com.ecommerceproject.model.GymEquipment;
import br.com.ecommerceproject.model.VitaminsAndSuplements;

import java.util.Date;

public class StorageController {
    private DataBase dataBase;
    private ProductSearchController search;

    public StorageController(DataBase dataBase) {
        this.dataBase = dataBase;
        this.search = new ProductSearchController(dataBase);
    }

    public void createNewProductTypeRecord(String name, Double price, Integer quantity, String description) {
        GymEquipment product = new GymEquipment(name, price, quantity, description);
        dataBase.addProduct(product);
    }

    public void createNewProductTypeRecord(String name, Double price, Integer quantity, String description, Date date) {
        VitaminsAndSuplements product = new VitaminsAndSuplements(name, price, quantity, description, date);
        dataBase.addProduct(product);
    }

    public void increaseProductTypeQuantity(String productCode) {
        Products product = search.searchStorageProduct(productCode);
        product.increaseQuantity();

    }

    public void decreaseProductTypeQuantity(String productCode) {
        Products product = search.searchStorageProduct(productCode);
        product.decreaseQuantity();
    }
}
