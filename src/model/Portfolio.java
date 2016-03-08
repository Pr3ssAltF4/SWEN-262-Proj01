package model;

import java.util.ArrayList;

import model.holding.*;


public class Portfolio{ 
	public String name;
	private ArrayList<Equity> holdings;
	private CashAccount cashAccount;
	
	public Portfolio(String name){
		this.name = name;
		this.holdings = new ArrayList<Equity>();
		this.cashAccount = new CashAccount(0);
	}

	public ArrayList<Equity> getHoldings() {
		return holdings;
	}

	public String getName(){
		return name;
	}
	
	public void addHolding(Equity holding){
		holdings.add(holding);
	}
	
	public void removeHolding(Equity holding){
		holdings.remove(holding);
	}
	
	
}
