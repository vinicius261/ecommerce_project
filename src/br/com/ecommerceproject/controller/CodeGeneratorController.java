package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.database.DataBase;

public class CodeGeneratorController {
    private DataBase dataBase;

    public CodeGeneratorController(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public String generate() {
        return "789" + dataBase.getProducts().size();
    }
}
