package src.model;

import src.util.IdGenorator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Transaction {

    static DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
    private double amount;
    private String type;
    private Equity equity;
    private Date date;

    public Transaction(String type, double amount, Equity equity) {
        this.amount = amount;
        this.type = type;
        this.equity = equity;
        this.date = new Date();
    }
    

    public String exportTransaction(){
        String export = "";
        export += type + "," + amount + "," + equity.exportEquity();
        return export;
    }

    public static Transaction importTransaction(String line) {

        String[] args = line.split(",");
        return new Transaction(args[0], Double.parseDouble(args[1]), new Equity(args[2], Integer.parseInt(args[3]), Double.parseDouble(args[4])));
    }

    public String toString(){
        String message = "";
        message += "Equity: " + this.equity.getTicker();
        message += "  Number of Shares: " + this.equity.getNumberOfStocks();
        message += "  Transaction type:  " + this.type;
        message += "  Transaction Amount:  " + this.amount;
        message += "  Transaction Date: " + dateFormat.format(this.date);
        return message;
    }

}
