package src.model;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import src.model.Transaction;
import src.model.Equity;
import src.model.User;
import src.model.Account;

public class Portfolio {
    private String name;
    private ArrayList<Equity> equities;
    private int id;
    private User user;
    public ArrayList<Transaction> transaction_history; // complete transaction history
    public ArrayList<Account> accounts; // All account assigned to user

    //Portfolio constructor
    public Portfolio(String name, int id, User user) {
        this.name = name;
        this.equities = new ArrayList<>();
        this.id = id;
        this.user = user;
        this.transaction_history = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public Portfolio(String name, int id) {
        this.name = name;
        this.equities = new ArrayList<>();
        this.id = id;
        this.transaction_history = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

//    public Portfolio(String fileName) {
//        importPortfolio(fileName);
//    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*
        Equities (aka Equity)
         */
    public ArrayList<Equity> getEquities() {
        return equities;
    }

    /*
    Returns the name
    */
    public String getName() {
        return name;
    }

    /*
    Adds and Equity to the Portfilio given the equity
     */
    public void addEquity(String ticker, int shares, double price) {
        Equity equity = new Equity(ticker, shares, price);
        equities.add(equity);
    }

    /*
    Removes the Equity given the Equity
     */
    public void removeEquity(Equity equity) {
        equities.remove(equity);
    }

	/*
    Importing links to keep around

	https://dzone.com/articles/java-example-list-all-files

	https://docs.oracle.com/javase/tutorial/essential/io/dirs.html
	 */

    public ArrayList<Equity> getEquityByTicker(String ticker) {
        ArrayList<Equity> filtered = new ArrayList<>();
        Equity current;
        for (int i = 0; i < getEquities().size(); i++) {
            current = this.getEquities().get(i);
            if (current.getTicker() == ticker) {
                filtered.add(current);
            }
        }
        return filtered;
    }

    public int getShares(String ticker) {
        int shares = 0;
        Equity current;
        for (int i = 0; i < getEquities().size(); i++) {
            current = this.getEquities().get(i);
            if (current.getTicker() == ticker) {
                shares += current.getNumberOfStocks();
            }
        }
        return shares;
    }


    public void addTransaction(Transaction transaction) {
    }

    public void removeTransaction(int transaction_id) {
    }

    public void addAccount(Account account) {
    }

    public void removeAccount(int account_id) {
    }

    //returns total cash from all accounts
    public double evalCash() {
        double total = 0;
        ArrayList<Account> accounts = this.accounts;
        for (int i = 0; i < accounts.size(); i++) {
            total += accounts.get(i).getBalance();
        }
        return total;
    }


    /**
     * NEED TO ADD TRANSACTION
     *
     * @param ticker  - ticker symbol for equity
     * @param shares  - amount of equity you want to purchase
     * @param price   - price per equity
     * @param useCash -
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

    /*
    Exports a portfolio and returns true if successful
     */
    public boolean exportPortfolio() {
        try {
            PrintWriter writer = new PrintWriter("exports/" + name + "-" + id + ".txt", "UTF-8");
            String export = id + "," + name + "," + equities.size() + "," + accounts.size()
                    + "," + transaction_history.size();
            writer.println(export);
            writer.println(user.exportUser());
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
        } catch (Exception ex) {
            System.out.println("Unable to export files:" + ex.getMessage());
            return false;
        }

        return true;
    }

//    public Portfolio importPortfolio(String fileName) {
//        File file = new File("exports/" + fileName);
//        try {
//            Scanner scanner = new Scanner(file);
//            int state = 0;
//            String[] args;
//            int[] sizes = new int[2];
//
//            while (scanner.hasNextLine())
//                if (state == 0) {
//                    args = scanner.nextLine().split(",");
//                    int portfolioId = Integer.parseInt(args[0]);
//                    String name = args[1];
//                    sizes[0] = Integer.parseInt(args[2]);
//                    sizes[1] = Integer.parseInt(args[3]);
//                    sizes[2] = Integer.parseInt(args[4]);
//                    args = scanner.nextLine().split(",");
//                    User user = new User(args[0], args[1], args[2], )
//                }
//
//        } catch (Exception ex) {
//            System.out.println("Could not import Portfolio:>>" + ex.getMessage());
//        }
//
//        return null;
//    }
}
