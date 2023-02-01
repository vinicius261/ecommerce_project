package br.com.ecommerceproject.database;

import br.com.ecommerceproject.controller.CostumerRegistrationController;
import br.com.ecommerceproject.controller.StorageController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateData {
    private DataBase dataBase;
    private CostumerRegistrationController costumerRegistration;
    private StorageController storage;


    public CreateData(DataBase dataBase){
        this.dataBase = dataBase;
        this.costumerRegistration = new CostumerRegistrationController(dataBase);
        this.storage = new StorageController(dataBase);
    }

    public void createData() {
        costumerRegistration.createNewRecord("Vinicius", "v", "0");
        costumerRegistration.createNewRecord("José", "j", "0");

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = formatter.parse("30/01/2023");
            storage.createNewProductRecord("Creatina",
                    100.5, 30, "Suplemento alimentar para ganho de força", date );
        }catch (ParseException ex){
            System.out.println(ex.getMessage());
        }

    }
}
