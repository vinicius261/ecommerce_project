package br.com.ecommerceproject.database;

import br.com.ecommerceproject.model.Costumer;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private List<Costumer> costumers;

    public  DataBase(){
        this.costumers = new ArrayList<Costumer>();
    }
    public void add(Costumer costumer) {
        costumers.add(costumer);
    }

    public List<Costumer> getCostumers() {
        return costumers;
    }
}
