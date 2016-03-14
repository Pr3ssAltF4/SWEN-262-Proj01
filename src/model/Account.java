package src.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {

    static DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
    private int id;
    private String name;
    private double balance;
    private Date dateCreated;

    /**
     *
     * @param name - name of account owner
     * @param balance - amount on money in account
     */
    public Account(String name, double balance, int id) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.dateCreated = new Date();
    }

    /**
     *
     * @return id own account owner
     */
    public int getId() { return id; }
    public double getBalance() { return balance; }
    public String getName() { return name; }

    /**
     *
     * @param withdrawal - amount to withdraw from account
     * @return amount withdrawed or -1 if insufficent funds
     */
    public double withdraw(double withdrawal) {
        if(this.balance - withdrawal >= 0)
            return this.balance -= withdrawal; // don't remember if this works in java lol. I may or may not
        // be drunk coding right now...
        else
            return -1;
    }

    /**
     *
     * @param deposit - amount to deposit into account
     * @return balance after deposit is complete
     */
    public double deposit(double deposit) {
        this.balance += deposit;
        return this.balance;
    }

    /**
     *
     * @return - the date account was created
     */
    public Date getDateCreated(){
        return dateCreated;
    }

    /**
     * convert Account object to string
     * @return - return String of Account object values
     */
    public String exportAccount() {
        return "" + id + "," + name + "," + balance;
    }

    /**
     * convert String into an Account object
     * @param line - lines of Account value
     * @param id - id of portfolio
     * @return Account object made with string values
     */
    public static Account importAccount(String line, int id) {
        String[] args = line.split(",");
        return new Account(args[1], Double.parseDouble(args[2]), id);
    }

    public String toString(){
        String message = "";
        message += "Date Created: " + dateFormat.format(this.dateCreated);
        message += "  ID: " + this.id;
        message += "  Name: " + this.name;
        message += "  Balance: " + this.balance;

        return message;
    }
}
