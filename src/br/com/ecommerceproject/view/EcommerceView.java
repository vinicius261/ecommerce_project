package br.com.ecommerceproject.view;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.model.Costumer;

public class EcommerceView {

    private DataBase dataBase;
    private Costumer loggedInCostumer;
    public EcommerceView(DataBase dataBase, Costumer loggedInCostumer) {
        this.dataBase = dataBase;
        this.loggedInCostumer = loggedInCostumer;
    }

    public void ecommerceView(){
        System.out.println("Ecommercer View");
    }
}
