package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.exceptions.InvalidEmailException;
import br.com.ecommerceproject.interfaces.Validations;
import br.com.ecommerceproject.model.Costumer;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailInputController implements Validations {
    private Scanner scanner;
    private DataBase dataBase;
    private SearchCostumerController searchCostumer;

    public EmailInputController(DataBase dataBase){
        this.dataBase = dataBase;
        this.scanner = new Scanner(System.in);
        this.searchCostumer = new SearchCostumerController(dataBase);
    }

    @Override
    public void validate(String input) {
        Costumer alreadyRegistered = searchCostumer.searchCostumer(input);
        if (alreadyRegistered == null){
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
            Matcher matcher = pattern.matcher(input);

            if (! matcher.matches()){
                throw new InvalidEmailException("Insira um email válido");
            }
        }else {
            throw new InvalidEmailException("Email já cadastrado.");
        }
    }
}
