package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.model.Costumer;

public class SearchCostumerController {
    private DataBase dataBase;

    public SearchCostumerController(DataBase dataBase){
        this.dataBase = dataBase;
    }

    public Costumer searchCostumer(String email){
        Costumer wantedCostumer = null;

        for (Costumer costumer: dataBase.getCostumers()) {
            if(costumer.getLogin().equals(email)){
                wantedCostumer = costumer;
            }
        }

        return wantedCostumer;
    }

}
