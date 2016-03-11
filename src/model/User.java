package model;
import java.util.ArrayList;

//import model; // This needs to change. Visibility and such actually matters.

public class User {
    private int id;
    public String username; // username
    public String name; // first and last name
    public String password; // Might want to bit 64 encode or hash this shit
    public ArrayList<Account> accounts; // All account assigned to user
    public ArrayList<Transaction> transaction_history; // complete transaction history
    public Portfolio portfolio;

    // ctor
    public User(String username, String name, int id) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.accounts = new ArrayList<Account>();
        this.transaction_history = new ArrayList<Transaction>();
        this.portfolio = new Portfolio(name, id);
    }

    // Some method stubs
    public String getUsername() { return username; }
    public void addTransaction(Transaction transaction) {}
    public void removeTransaction(int transaction_id) {}
    public void addAccount(Account account) {}
    public void removeAccount(int account_id) {}
    public int getId() { return id; }
    
}
