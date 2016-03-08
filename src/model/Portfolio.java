package model;

import java.util.ArrayList;
import model.holding.Equity;


public class Portfolio{ 
	public String name;
	private ArrayList<Equity> equities;
	private CashAccount cashAccount;
	//private User user

	//Portfolio constructor
	public Portfolio(String name){
		this.name = name;
		this.equities = new ArrayList<Equity>();
		this.cashAccount = new CashAccount(0);
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
	public String getName(){
		return name;
	}

	/*
	Adds and Equity to the Portfilio given the equity
	 */
	public void addEquity(Equity equity){
		equities.add(equity);
	}

	/*
	Removes the Equity given the Equity
	 */
	public void removeEquity(Equity equity){
		equities.remove(equity);
	}
	
	
}
