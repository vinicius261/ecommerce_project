package br.com.ecommerceproject.view;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.model.Costumer;
import br.com.ecommerceproject.validationcode.Validations;

import java.util.Scanner;

public class PaymentView {
    private DataBase dataBase;
    private Costumer loggedInCostumer;
    private Validations validations;
    private Scanner scanner;

    public PaymentView(DataBase dataBase, Costumer loggedInCostumer) {
        this.dataBase = dataBase;
        this.loggedInCostumer = loggedInCostumer;
        this.validations = new Validations();
        this.scanner = new Scanner(System.in);
    }

    public void paymentView(Double totalValue) {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("\n		Pagamento");

        paymentChoice(totalValue);

    }

    public void paymentChoice(Double totalValue) {
        EcommerceView ecommerce = new EcommerceView(dataBase, loggedInCostumer);
        System.out.println("Digite o número correspondente a opção de pagamento: " +
                "\n1 - Boleto"+
                "\n2 - Cartão de crédito parcelado em até 3x"+
                "\n3 - Paypal");

        Integer choice = validations.integerInputValidation(scanner.nextLine());

        switch (choice){
            case 1:
                System.out.println("Boleto de " + totalValue + "R$ gerado! Aguardamos a confirmação do Banco.");
                finishSession();
                ecommerce.view();
                break;
            case 2:
                System.out.println("Insira o número do cartão: ");
                Double cardNumber = validations.doubleInputValidation(scanner.nextLine());
                cardPayment(totalValue);
                finishSession();
                ecommerce.view();
                break;
            case 3:
                System.out.println("Direcionando para o PayPal");
                System.out.println("Assim que confirmarmos o pagamento você receberá um email.");
                finishSession();
                ecommerce.view();
                break;
            default:
                System.out.println("Opção inválida.");
                CartView cart = new CartView(dataBase, loggedInCostumer);
                cart.view();
        }
    }

    public void cardPayment(Double totalValue){
        System.out.println("Digite o número correspondente ao número de parcelas: " +
                "\n1 - A vista"+
                "\n2 - 2x sem juros"+
                "\n3 - 3x sem juros");

        Integer installment = validations.integerInputValidation(scanner.nextLine());

        if (0 < installment && installment < 4) {
            System.out.println("Pagamento realizado!");
        }
    }

    public void finishSession(){
        loggedInCostumer.getCart().clear();
    }
}
