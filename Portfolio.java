package src.model;

// Author: Ian Taylor
import java.util.Date;
import src.util.IdGenorator;


public class Portfolio {

    private String name = "";
    private Date dateCreated = null;
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
    
    // Method headers to complete

    // (for both below) returns empty string iff the transaction was undone successfully.
    public String undoTransaction() {
	return ""
    }
    public String performTransaction() {
	return ""
    }

    public void removeWatchedEquity() {}
    public void addWatchedEquity() {}

    public void buyEquity() {}
    public void sellEquity() {}

    public void depositInAccount() {}
    public void withdrawFromAccount() {}

    public void simulateMarket() {}

    // These are done dead last.
    public Portfolio importPortfolio() {}
    public String exportPortfolio() {}

    public void addAccount() {}
    public void removeAccount() {}
    
}
