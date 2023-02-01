package br.com.ecommerceproject.database;

import br.com.ecommerceproject.controller.CostumerRegistrationController;
import br.com.ecommerceproject.controller.StorageController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateInitialData {
    private DataBase dataBase;
    private CostumerRegistrationController costumerRegistration;
    private StorageController storage;


    public CreateInitialData(DataBase dataBase){
        this.dataBase = dataBase;
        this.costumerRegistration = new CostumerRegistrationController(dataBase);
        this.storage = new StorageController(dataBase);
    }

    public void createData() {
        costumerRegistration.createNewRecord("Vinicius", "v", "0");

        costumerRegistration.createNewRecord("José", "j", "0");

        createProducts("Creatina",
                100.5, 30, "Suplemento alimentar para ganho de força", "04/04/2023" );
        createProducts("Halteres",
                500.0, 15, "Par de Halteres de aço");
        createProducts("Esteira",
                1599.99, 5, "Esteira caseira");
        createProducts("Proteina isolada de soja",
                149.0, 35, "Suplemento proteico vegano.", "25/12/2024");
    }

    public void createProducts(String name, Double price, Integer quantity, String description, String inputDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = formatter.parse(inputDate);
            storage.createNewProductRecord(name, price, quantity, description, date);
        }catch (ParseException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void createProducts(String name, Double price, Integer quantity, String description){
        storage.createNewProductRecord(name, price, quantity, description);
    }
}
