package br.com.ecommerceproject.database;

import br.com.ecommerceproject.interfaces.Products;
import br.com.ecommerceproject.model.Costumer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DataBase {
    private List<Costumer> costumers;
    private List<Products> products;
    public  DataBase(){
        this.costumers = new ArrayList<>();
        this.products = new ArrayList<>();
    }
    public void addCostumer(Costumer costumer) {
        costumers.add(costumer);
    }

    public List<Costumer> getCostumers() {
        return Collections.unmodifiableList(costumers);
    }

    public void addProduct(Products product) {
        products.add(product);
    }

    public List<Products> getProducts() {
        return Collections.unmodifiableList(products);
    }
}
