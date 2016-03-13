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
        this.accounts = new ArrayList<>();
        this.transaction_history = new ArrayList<>();
        this.portfolio = new Portfolio(name, id, this);
    }

    // Some method stubs
    public String getUsername() { return username; }

    //Add the given transaction to the arraylist transaction history
    public void addTransaction(Transaction transaction) {
        transaction_history.add(transaction);
    }

    //removes the transaction from the transaction history based on the id
    public void removeTransaction(int transaction_id) {
        for(int x = 0; x < transaction_history.size(); x++){
            if(transaction_history.get(x).getId() == transaction_id) {
                transaction_history.remove(x);
                return;
            }
        }
    }

    //Adds an account to the list of accounts
    public void addAccount(Account account) {
        accounts.add(account);
    }

    public ArrayList<Account> getAccounts() { return this.accounts;}
    public void removeAccount(int account_id) {}
    public int getId() { return id; }

    //returns total cash from all accounts
    public double evalCash(){
        double total = 0;
        ArrayList<Account> accounts =  this.getAccounts();
        for (int i = 0; i < accounts.size(); i++){
            total += accounts.get(i).getBalance();
        }
        return total;
    }


    public void buyEquity(String ticker, int shares, double price, boolean useCash){
        if (useCash){
            double cost = shares * price;
            ArrayList<Account> canPurchase = canPurchase(cost, getAccounts());
            //allow user to select what account to use through controller

            if (canPurchase.isEmpty()){
                System.out.println("You do not have enough cash to purchase these equities");
            } else {
                canPurchase.get(0).withdraw(cost);
                this.portfolio.addEquity(ticker, shares, price);
            }
        } else {
            this.portfolio.addEquity(ticker, shares, price);
        }
    }


    /**
     *
     * @param cost - cost of equities
     * @param accounts - list of cash accounts
     * @return list of account with enough balance to buy equities
     */
    public ArrayList<Account> canPurchase(double cost, ArrayList<Account> accounts){
        ArrayList<Account> canPurchase = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++){
            if (accounts.get(i).getBalance() >= cost){
                canPurchase.add(accounts.get(i));
            }
        }
        return canPurchase;
    }
}
