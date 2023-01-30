package br.com.ecommerceproject.model;

import br.com.ecommerceproject.interfaces.Perishable;
import br.com.ecommerceproject.interfaces.Products;

import java.util.Date;

public class VitaminsAndSuplements implements Perishable {

    private String name;
    private Double price;
    private Integer quantity;
    private String description;
    private Date bestBeforeDate;

    public VitaminsAndSuplements(String name, Double price, Integer quantity, String description, Date bestBeforeDate){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.bestBeforeDate = bestBeforeDate;

    }
    @Override
    public Date getBestBeforeDate() {
        return this.bestBeforeDate;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public Integer getQuantity() {
        return this.quantity;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
