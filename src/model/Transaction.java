package src.model;

import src.util.IdGenorator;

import java.util.ArrayList;

public class Transaction {

<<<<<<< HEAD
    private double amount;
    private String type;
    private Equity equity;

    public Transaction(String type, double amount, Equity equity) {
        this.amount = amount;
        this.type = type;
        this.equity = equity;
=======
    public int id;
    
    public Transaction() {
        this.id = IdGenorator.getInstance().getNewId();
>>>>>>> 31e79b5298a1b384a1098254cca902842eb699ab
    }



    public String exportTransaction(){
        String export = "";
        export += type + ", " + amount + ", " + equity.exportEquity();
        return export;
    }

    public static Transaction importTransaction(String line) {
<<<<<<< HEAD
        String[] args = line.split(",");
        return new Transaction(args[0], Double.parseDouble(args[1]), new Equity(args[2], Integer.parseInt(args[3]), Double.parseDouble(args[4])));
=======
        return new Transaction();
>>>>>>> 31e79b5298a1b384a1098254cca902842eb699ab
    }
}
