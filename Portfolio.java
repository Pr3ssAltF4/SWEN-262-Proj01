package src.model;

// Author: Ian Taylor
import java.util.Date;
import src.util.IdGenorator;


public class Portfolio {

    private String name = "";
    private Date dateCreated = null;
    private ArrayList<Equity> equities = new ArrayList<Equity>();
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

    public String getUsername() {}
    public boolean validatePassword(String entered_password) {
	if (entered_password == getUser().getPassword())
	    return true;
	else
	    return false;
    }
    
    
}
