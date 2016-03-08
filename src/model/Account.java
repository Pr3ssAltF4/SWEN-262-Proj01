package models;

import java.util.ArrayList;
import model; // Again, this needs to change because design and visibilty actually matters

public class Account {

    private int id;
    public String name;
    private int amount;

    public Account(String name, int id, int amount) {
	this.id = id;
	this.name = name;
	this.amount = amount;
    }

    public int getId() { return id; }
    public int getAmount() { return amount; }
    public String getName() { return name; }
    // withdraws money from the account. Returns amount left.
    public int withdraw(int withdrawal) {
	if(this.amount - withdrawal >= 0)
	    return this.amount -= withdrawal; // don't remember if this works in java lol. I may or may not
	// be drunk coding right now...
	else
	    return -1; 
    }
    // deposits money into the account.
    public int deposit(int deposit) {
	this.amount += deposit;
	return this.amount;
    }
    
}
