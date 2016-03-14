package src.model;


import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import src.model.Transaction;
import src.model.Equity;
import src.model.User;
import src.model.Account;
import src.util.IdGenorator;

import javax.sound.sampled.Port;

public class Portfolio {
    static private ArrayList<Portfolio> allPortfolios = new ArrayList<>();

    private String name;
    private ArrayList<Equity> equities;
    private int id;
    private User user;
    private ArrayList<Transaction> transaction_history; // complete transaction history
    private ArrayList<Account> accounts; // All account assigned to user



    /**
     *
     * @param name - name of portfolio owner
     * @param id - unique portfolio id
     * @param user - User associated with portfolio
     */
    public Portfolio(String name, int id, User user) {
        this.name = name;
        this.id = IdGenorator.getInstance().getNewId();
        this.user = user;

        this.transaction_history = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.equities = new ArrayList<>();

        allPortfolios.add(this);

    }

    public Portfolio(String username, String name, String password){
        this.name = name;
        this.id = IdGenorator.getInstance().getNewId();
        this.user = new User(username, name);
        this.user.setPassword(password);
        this.transaction_history = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.equities = new ArrayList<>();

        allPortfolios.add(this);

    }

    public Portfolio(String fileName) {
        importPortfolio(fileName);
        allPortfolios.add(this);
    }

    /**
     *
     * @return returns users
     */
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return returns the list of equities in the portfolio
     */
    public ArrayList<Equity> getEquities() {
        return equities;
    }

    /**
     *
     * @return return name of portfolio owner
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param ticker - ticker symbol
     * @param shares - number of shares
     * @param price - price per ticker symbol
     */
    public void addEquity(String ticker, int shares, double price) {
        Equity equity = new Equity(ticker, shares, price);
        equities.add(equity);
    }

    /**
     *
     * @param equity - equity to add to portfolio
     */
    private void addEquity(Equity equity) {
        equities.add(equity);
    }

    /**
     *
     * @param equity - equity to remove from portfolio
     */
    public void removeEquity(Equity equity) {
        equities.remove(equity);
    }

    /**
     *
     * @return id of portfolio
     */
    public int getId(){
        return id;
    }
	/*
    Importing links to keep around

	https://dzone.com/articles/java-example-list-all-files

	https://docs.oracle.com/javase/tutorial/essential/io/dirs.html
	 */

    /**
     *
     * @param ticker ticker to search for
     * @return list of Equity objects with matching ticker
     */
    public ArrayList<Equity> getEquityByTicker(String ticker) {
        ArrayList<Equity> filtered = new ArrayList<>();
        for (Equity e : this.equities) {
            if (e.getTicker() == ticker) {
                filtered.add(e);
            }
        }
        return filtered;
    }

    /**
     *
     * @param ticker ticker to search for
     * @return number of shares in portfolio with matching ticker
     */
    public int getShares(String ticker) {
        int shares = 0;
        for (Equity e : this.equities) {
            if (e.getTicker() == ticker) {
                shares += e.getNumberOfStocks();
            }
        }
        return shares;
    }


    /**
     *
     * @param transaction - transaction object to add to list of transactions
     */
    public void addTransaction(Transaction transaction) {transaction_history.add(transaction);}

    /**
     *
     * @param transaction - Remove a transaction object from the transaction history
     */
    public void removeTransaction(Transaction transaction){
        transaction_history.remove(transaction);
    }

    /**
     * Remakes the portfolio with the current list of transactions in transaction_history
     */
    public void buildPortfolio(){
        this.equities = new ArrayList<Equity>();
        this.accounts = new ArrayList<Account>();
        for(Transaction transaction : transaction_history){
            transaction.prossessTransaction(this);
        }
    }

    /**
     *
     * @param name - name of account to add
     * @param balance - balance of account
     */
    public void addAccount(String name, double balance) {
		accounts.add(new Account(name, balance, this.id));
	}

    /**
     *
     * @param account - account object to add to list of accounts
     */
    private void addAccount(Account account) {
        accounts.add(account);
    }

    /**
     *
     * @param name - remove an account based on name
     */
	public void removeAccount(String name) {
		for (Account a: this.accounts){
			if (a.getName() == name){
				accounts.remove(a);
			}
		}
	}

    /**
     *
     * @return - returns the total cash from all accounts
     */
	public double evalCash(){
		double total = 0;
		for (Account a: this.accounts){
			total += a.getBalance();
		}
		return total;
	}

    /**
     *
     * @param ticker  - ticker symbol for equity
     * @param shares  - amount of equity you want to purchase
     * @param price   - price per equity
     * @param useCash - true if using cash to buy equity
     */
    public void buyEquity(String ticker, int shares, double price, boolean useCash) {
        if (useCash) {
            double cost = shares * price;
            ArrayList<Account> canPurchase = canPurchase(cost, this.accounts);
            //allow user to select what account to use through controller

            if (canPurchase.isEmpty()) {
                System.out.println("You do not have enough cash to purchase these equities");
            } else {
                canPurchase.get(0).withdraw(cost);//put in first index until controllers handles this
                addEquity(ticker, shares, price);
            }
        } else {
            addEquity(ticker, shares, price);
        }
    }

    /**
     *
     * @param ticker - ticker symbol
     * @param shares - number of shares to remove
     * @param price - price of each share
     * @param toCash - convert equity to cash
     */
    public void removeEquity(String ticker, int shares, double price, boolean toCash) {
        if (toCash) {
            double earned = shares * price;
            //Allow users to select what account they want to put the cash in
            accounts.get(0).deposit(earned); //put in first index until controller handles this
            ArrayList<Equity> toRemove = getEquityByTicker(ticker);
        }
    }

    /**
     * @param cost     - cost of equities
     * @param accounts - list of cash accounts
     * @return list of account with enough balance to buy equities
     */
    public ArrayList<Account> canPurchase(double cost, ArrayList<Account> accounts) {
        ArrayList<Account> canPurchase = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getBalance() >= cost) {
                canPurchase.add(accounts.get(i));
            }
        }
        return canPurchase;
    }

    /**
     *
     * @param name -
     * @param ammount
     */
	public void depostitByname(String name, double ammount){
		for(Account a: this.accounts){
			if (a.getName() == name){
				a.deposit(ammount);
				break;
			}
		}
	}

    /**
     * exports all portfolios to text documents
     * @return returns true if all were exported successfully
     */
    public static boolean exportAllPortfolios(){
        for(Portfolio portfolio : allPortfolios) if(!portfolio.exportPortfolio()) return false;
        return true;
    }

    /**
     * convert portfolio to text document
     * @return returns true if portfolio is successfully written to document
     */
    public boolean exportPortfolio() {
        try {
            PrintWriter writer = new PrintWriter("exports/" + name + "-" + id + ".txt", "UTF-8");
            writer.println(user.exportUser());
            String export = "" + equities.size() + "," + accounts.size()
                    + "," + transaction_history.size();
            writer.println(export);
            for (Equity equity : equities) {
                writer.println(equity.exportEquity());
            }
            for (Transaction transaction : transaction_history) {
                writer.println(transaction.exportTransaction());
            }
            for (Account account : accounts) {
                writer.println(account.exportAccount());
            }
            writer.close();
            return true;
        } catch (Exception ex) {
            System.out.println("Unable to export files:" + ex.getMessage());
        }

        return false;
    }

    /**
     * reads portfolio document and convert data into a portfolio object
     * @param fileName - portfolio document to read
     * @return returns portfolio object from document
     */
    public Portfolio importPortfolio(String fileName) {
        File file = new File("exports/" + fileName);

        try {
            Scanner scanner = new Scanner(file);
            int state = 0;
            String[] args;
            int[] sizes = new int[2];
            Portfolio portfolio;
            while (scanner.hasNextLine()) {
                if (state == 0) {
                    //imports user and start on portfolio
                    args = scanner.nextLine().split(",");
                    User user = new User(args[0], args[1]);
                    user.setPassword(args[3]);
                    //find the number of each array
                    args = scanner.nextLine().split(",");
                    sizes[0] = Integer.parseInt(args[0]);
                    sizes[1] = Integer.parseInt(args[1]);
                    sizes[2] = Integer.parseInt(args[2]);
                    state = 1;
                }

                //Equities loop
                for (int x = 0; x < sizes[0] && scanner.hasNextLine(); x++) {
                    this.addEquity(Equity.importEquity(scanner.nextLine()));
                }
                //Transaction loop
                for (int x = 0; x < sizes[1] && scanner.hasNextLine(); x++) {
                    this.addTransaction(Transaction.importTransaction(scanner.nextLine()));
                }
                for (int x = 0; x < sizes[2] && scanner.hasNextLine(); x++) {
                    this.addAccount(Account.importAccount(scanner.nextLine(), getId()));
                }

                return this;

            }
        } catch (Exception ex) {
            System.out.println("Could not import Portfolio:>>" + ex.getMessage());
                return null;

        }

        return null;
    }

    public ArrayList<Account> getAccounts(){
        return accounts;
    }

    public ArrayList<Transaction> getTransaction_history(){
        return transaction_history;
    }

    public String toString(){
        return this.name;
    }

}
