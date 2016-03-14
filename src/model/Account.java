package src.model;

import src.util.IdGenorator;

import java.util.Date;

public class Account {

    private int id;
    public String name;
    private double balance;
    public Date dateCreated;

    public Account(String name, double balance) {
        this.id = IdGenorator.getInstance().getNewId();
        this.name = name;
        this.balance = balance;
        this.dateCreated = new Date();
    }

    public int getId() { return id; }
    public double getBalance() { return balance; }
    public String getName() { return name; }

    // withdraws money from the account. Returns amount left.
    public double withdraw(double withdrawal) {
        if(this.balance - withdrawal >= 0)
            return this.balance -= withdrawal; // don't remember if this works in java lol. I may or may not
        // be drunk coding right now...
        else
            return -1;
    }

    // deposits money into the account.
    public double deposit(double deposit) {
        this.balance += deposit;
        return this.balance;
    }

    public Date getDateCreated(){
        return dateCreated;
    }

    public String exportAccount() {
        return "" + id + "," + name + "," + balance;
    }

    public static Account importAccount(String line) {
        String[] args = line.split(",");
        return new Account(args[1], Double.parseDouble(args[2]));
    }
}
