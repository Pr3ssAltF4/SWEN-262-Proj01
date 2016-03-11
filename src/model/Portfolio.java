package model;

import java.util.ArrayList;

public class Portfolio{ 
	private String name;
	private ArrayList<Equity> equities;
	private int id;

	//Portfolio constructor
	public Portfolio(String name, int id){
		this.name = name;
		this.equities = new ArrayList<>();
		this.id = id;
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
	public void addEquity(String ticker, int shares, double price){
        Equity equity = new Equity(ticker, shares, price);
        equities.add(equity);
	}

    public void addEquity(String ticker, int shares, double price, boolean cash){
        if (cash){
            
        }
    }
	/*
	Removes the Equity given the Equity
	 */
	public void removeEquity(Equity equity){
		equities.remove(equity);
	}
	
	
}
