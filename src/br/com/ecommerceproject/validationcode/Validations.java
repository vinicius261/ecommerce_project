package br.com.ecommerceproject.validationcode;

import java.util.Scanner;

public class Validations {
    private Scanner scanner;

    public Validations (){
        this.scanner = new Scanner(System.in);
    }

    public Integer integerInputValidation(String input){
        Integer validatedInteger;

        try {
            validatedInteger = Integer.parseInt(input);
        }catch (NumberFormatException ex){
            System.out.println("Digite apenas números inteiros.");
            validatedInteger = integerInputValidation(scanner.nextLine());
        }

        return validatedInteger;
    }

    public Double doubleInputValidation(String input){
        Double validatedDouble;

        try {
            validatedDouble = Double.parseDouble(input);
        }catch (NumberFormatException ex){
            System.out.println("Digite apenas números. Use ponto para casas decimais. ");
            validatedDouble = doubleInputValidation(scanner.nextLine());
        }

        return validatedDouble;
    }
}
