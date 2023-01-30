package br.com.ecommerceproject.model;

import br.com.ecommerceproject.interfaces.Products;

public class GymEquipment implements Products {
    private String name;
    private Double price;
    private Integer quantity;
    private String description;

    public GymEquipment(String name, Double price, Integer quantity, String description){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
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
