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
        this.transaction_history = new ArrayList<Transaction>();
        this.portfolio = new Portfolio(name, id);
    }

    // Some method stubs
    public String getUsername() { return username; }
    public void addTransaction(Transaction transaction) {}
    public void removeTransaction(int transaction_id) {}
    public void addAccount(Account account) {}
    public ArrayList<Account> getAccounts() { return this.accounts;}
    public void removeAccount(int account_id) {}
    public int getId() { return id; }

    //returns total cash from all accounts
    public double evalCash(){
        double total = 0;
        ArrayList<Account> accounts =  this.getAccounts();
        for (int i = 0; i < accounts.size(); i++){
            total += accounts.get(i).getAmount();
        }
        return total;
    }

    /** NEED TO ADD TRANSACTION
     *
     * @param ticker - ticker symbol for equity
     * @param shares - amount of equity you want to purchase
     * @param price - price per equity
     * @param useCash -
     */
    public void buyEquity(String ticker, int shares, double price, boolean useCash){
        if (useCash){
            double cost = shares * price;
            ArrayList<Account> canPurchase = canPurchase(cost, getAccounts());
            //allow user to select what account to use through controller

            if (canPurchase.isEmpty()){
                System.out.println("You do not have enough cash to purchase these equities");
            } else {
                canPurchase.get(0).withdraw(cost);//put in first index until controllers handles this
                this.portfolio.addEquity(ticker, shares, price);
            }
        } else {
            this.portfolio.addEquity(ticker, shares, price);
        }
    }

    public void removeEquity(String ticker, int shares, double price, boolean toCash){
        if (toCash){
            double earned = shares * price;
            //Allow users to select what account they want to put the cash in
            accounts.get(0).deposit(earned); //put in first index until controller handles this
            ArrayList<Equity> toRemove = this.portfolio.getEquityByTicker(ticker);

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
            if (accounts.get(i).getAmount() >= cost){
                canPurchase.add(accounts.get(i));
            }
        }
        return canPurchase;
    }
}
