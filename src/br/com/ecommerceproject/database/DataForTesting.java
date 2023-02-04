package br.com.ecommerceproject.database;

import br.com.ecommerceproject.controller.CostumerRegistrationController;
import br.com.ecommerceproject.controller.StorageController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataForTesting {
    private DataBase dataBase;
    private CostumerRegistrationController costumerRegistration;
    private StorageController storage;


    public DataForTesting(DataBase dataBase){
        this.dataBase = dataBase;
        this.costumerRegistration = new CostumerRegistrationController(dataBase);
        this.storage = new StorageController(dataBase);
    }

    public void createTestingData() {
        costumerRegistration.createNewRecord("Vinicius", "v", "0");

        costumerRegistration.createNewRecord("José", "j", "0");

        createTestingProducts("Creatina",
                100.5, 30, "Suplemento alimentar para ganho de força", "04/04/2023" );
        createTestingProducts("Halteres",
                500.0, 15, "Par de Halteres de aço");
        createTestingProducts("Esteira",
                1599.99, 5, "Esteira caseira");
        createTestingProducts("Proteina isolada de soja",
                149.0, 35, "Suplemento proteico vegano.", "25/12/2024");
    }

    public void createTestingProducts(String name, Double price, Integer quantity, String description, String inputDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = formatter.parse(inputDate);
            storage.createNewProductTypeRecord(name, price, quantity, description, date);
        }catch (ParseException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void createTestingProducts(String name, Double price, Integer quantity, String description){
        storage.createNewProductTypeRecord(name, price, quantity, description);
    }
}
