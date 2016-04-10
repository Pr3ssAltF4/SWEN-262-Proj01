package src.model;

// Author: Ian Taylor

import java.util.Date;
import src.util.IdGenorator;

public class Portfolio {

    private String name = "";
    private Date dateCreated = new Date();
    private ArrayList<Equity> equities = new ArrayList<Equity>();
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private int id = IdGenorator.getInstance().getNewId();
    private Transaction history;
    private User user;

    
    public Portfolio(User user, String name) {
	this.name = name;
	this.user = user;
    }

    
    public int getId() { return this.id; }
    public User getUser() { return this.user; }
    public Transaction getTransaction() { return this.transaction; }
    public Date getDate() { return this.date;  }
    public ArrayList<Equity> getEquities() { return this.equities; }
    public ArrayList<Account> getAccounts() { return this.accounts; }


    
    // this might be in the wrong place...
    public boolean validatePassword(String entered_password) {
	if (entered_password == getUser().getPassword())
	    return true;
	else
	    return false;
    }
    
    
    // (for both below) returns empty string iff the transaction was undone successfully.
    public String undoTransaction() {
	return "";
    }
    public String performTransaction() {
	return "";
    }

    // Searches for the equity by ticker symbol. Then removes it from watched by either setting it to false
    // and/or removing it from the list of equities completely. 
    public boolean removeWatchedEquity(String ticker) {
	for(Equity equity : this.equities) {
	    if(equity.getTicker() == ticker && equity.isWatched == true) {
		equity.isWatched = false;
		if(equity.getOwned() == 0) {
		    this.equities.remove(equity);
		}
		return true;
	    }
	} 
	return false;
    }

    // sets a new equity's isWatched to true and creates it if necessary.
    public boolean addWatchedEquity(String ticker) {
	for(Equity equity : this.equities) {
	    if(equity.getTicker() == ticker) {
		equity.isWatched = true;
		return true;
	    }
	}
        if(Woolies.getTickerInfo(ticker) != null) {
	    Equity newEquity = new Equity(ticker, 0, 0); // NEED TO FIGURE OUT HOW and when to update the numbers...
	    // should update by itself though...
	    equities.add(newEquity);
	    newEquity.isWatched = true;
	    return true;
	}
	return false;
    }


    
    public boolean buyEquity(String ticker, int number, Account account) {
	return false;
	// TALK TO ERIC (how to create equity that works)
    }
    public boolean sellEquity(String ticker, int number, Account account) {
	return false;
	// TALK TO ERIC
    }


    
    // Deposits money in a specified account.
    public boolean depositInAccount(String name, double amount) {
	for(Account account : this.accounts) {
	    if(account.getName() == name) {
		account.deposit(amount);
		return true;
	    }
	}
	return false;
    }
    // Withdraws money from a specified account.
    public boolean withdrawFromAccount(String name, double amount) {
	for(Account account : this.accounts) {
	    if(account.getName() == name) {
		int check = account.withdraw(amount);
		if(check == -1)
		    return false;
		else
		    return true;
	    }
	}
	return false;
    }


    
    // HOW DOES THIS WORK!!!???
    public ArrayList<Equity> simulateMarket(int choice) {
	if(choice == 1) {
	    return simulated;
	} else if(choice == 2) {
	    return simulated;
	} else if(choice == 3) {
	    return simulated;
	}
	return null;
    }


    
    // These are done dead last.
    public Portfolio importPortfolio() {}
    public String exportPortfolio() {}


    // Adds an account to accounts.
    public void addAccount(String name, double balance) {
	this.accounts.add(new Account(name, balance));
    }
    public boolean removeAccount(String name) {
	for(Account account : this.accounts) {
	    if(account.getName() == name) {
		accounts.remove(account);
		return true;
	    }
	}
	return false;
    }
    
}
