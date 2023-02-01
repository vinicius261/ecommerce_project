package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.database.DataBase;

public class ProductCodeGeneratorController {
    private DataBase dataBase;

    public ProductCodeGeneratorController(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public String generate() {
        String code  = "0"+this.hashCode();
        code = code.substring(0,4);
        return code;
    }
}
