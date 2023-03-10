package br.com.ecommerceproject.model;

import br.com.ecommerceproject.controller.ProductCodeGeneratorController;
import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.interfaces.Perishable;

import java.util.Date;

public class VitaminsAndSuplements implements Perishable {
    private  String code;
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

        ProductCodeGeneratorController codeGenerator = new ProductCodeGeneratorController(new DataBase());
        this.code = codeGenerator.generate();

    }
    @Override
    public Date getBestBeforeDate() {
        return this.bestBeforeDate;
    }

    @Override
    public String getCode() {
        return this.code;
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
    public void increaseQuantity() {
        this.quantity++;
    }

    @Override
    public void decreaseQuantity() {
        this.quantity--;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
