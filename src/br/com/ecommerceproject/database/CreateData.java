package br.com.ecommerceproject.database;

import br.com.ecommerceproject.controller.CostumerRegistrationController;

public class CreateData {
    private DataBase dataBase;
    private CostumerRegistrationController costumerRegistration;


    public CreateData(DataBase dataBase){
        this.dataBase = dataBase;
        this.costumerRegistration = new CostumerRegistrationController(dataBase);
    }

    public void createData(){
        costumerRegistration.createNewRecord("Vinicius", "v", "0");
        costumerRegistration.createNewRecord("José", "j", "0");
    }
}
