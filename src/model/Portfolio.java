package model;

import java.util.ArrayList;
import java.util.StringTokenizer;

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

	/*
	Removes the Equity given the Equity
	 */
	public void removeEquity(Equity equity){
		equities.remove(equity);
	}

	public ArrayList<Equity> getEquityByTicker(String ticker){
        ArrayList<Equity> filtered = new ArrayList<>();
        Equity current;
        for (int i = 0; i < getEquities().size(); i++){
            current = this.getEquities().get(i);
            if (current.getTicker() == ticker){
                filtered.add(current);
            }
        }
        return filtered;
	}

    public int getShares(String ticker){
        int shares = 0;
        Equity current;
        for (int i = 0; i < getEquities().size(); i++){
            current = this.getEquities().get(i);
            if (current.getTicker() == ticker){
                shares += current.getNumberOfStocks();
            }
        }
        return shares;
    }
}
