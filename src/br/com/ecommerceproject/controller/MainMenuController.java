package br.com.ecommerceproject.controller;

import br.com.ecommerceproject.exceptions.InvalidOptionException;

public class MainMenuController {
    public Integer menuOption(String optionInput) {        
        try {
            Integer option = Integer.parseInt(optionInput);
            if (option == 1 || option == 2) {
                return option;
            }
        }catch (NumberFormatException ex){
            throw new InvalidOptionException("Escolha um opção do menu.");
        }
        throw new InvalidOptionException("Escolha um opção do menu.");
    }
}
