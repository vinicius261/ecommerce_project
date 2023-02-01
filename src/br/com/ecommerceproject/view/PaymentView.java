package br.com.ecommerceproject.view;

import br.com.ecommerceproject.database.DataBase;
import br.com.ecommerceproject.model.Costumer;

import java.util.Scanner;

public class PaymentView {
    private DataBase dataBase;
    private Costumer loggedInCostumer;
    private Scanner scanner;

    public PaymentView(DataBase dataBase, Costumer loggedInCostumer) {
        this.dataBase = dataBase;
        this.loggedInCostumer = loggedInCostumer;
        this.scanner = new Scanner(System.in);
    }

    public void paymentView() {
    }
}
