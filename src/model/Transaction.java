package src.model;

import java.util.ArrayList;

public class Transaction {

    private double amount;
    private String type;
    private Equity equity;

    public Transaction(String type, double amount, Equity equity) {
        this.amount = amount;
        this.type = type;
        this.equity = equity;
    }



    public String exportTransaction(){
        String export = "";
        export += type + ", " + amount + ", " + equity.exportEquity();
        return export;
    }

    public static Transaction importTransaction(String line) {
        String[] args = line.split(",");
        return new Transaction(args[0], Double.parseDouble(args[1]), new Equity(args[2], Integer.parseInt(args[3]), Double.parseDouble(args[4])));
    }
}
