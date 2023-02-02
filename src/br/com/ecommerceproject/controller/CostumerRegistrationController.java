package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.interfaces.Validations;
import br.com.ecommerceproject.model.Costumer;

import java.util.Scanner;

public class CostumerRegistrationController {
    DataBase dataBase;

    public CostumerRegistrationController(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public Costumer createNewRecord(String name, String email, String password) {

        Costumer costumer = new Costumer(name, email, password);

        this.dataBase.addCostumer(costumer);

        return costumer;
    }

    public String validate (Validations validator, String msg){
        Scanner scanner =  new Scanner(System.in);
        String data;

        try {
            System.out.println(msg);
            data = scanner.nextLine();
            validator.validate(data);
        }catch (RuntimeException ex){
            System.out.println(ex.getMessage());
            data = validate(validator, msg);
        }

        return data;
    }
}
