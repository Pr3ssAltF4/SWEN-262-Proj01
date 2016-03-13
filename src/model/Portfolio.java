package model;

import java.io.File;
import java.io.PrintWriter;
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

	/*
	Removes the Equity given the Equity
	 */
	public void removeEquity(Equity equity){
		equities.remove(equity);
	}

	/*
	Exports a portfolio and returns true if successful
	 */
	public boolean exportPortfolio(){
		try {
			PrintWriter writer = new PrintWriter("exports/"+name + "-" + id + ".txt", "UTF-8");
			String export = id +"," + name;
			writer.println(export);
			for(Equity equity : equities){
				writer.println(equity.exportEquity());
			}
			writer.close();
		}catch(Exception ex){
			System.out.println("Unable to export files:" + ex.getMessage());
			return false;
		}

		return true;
	}

	/*
	Importing links to keep around

	https://dzone.com/articles/java-example-list-all-files

	https://docs.oracle.com/javase/tutorial/essential/io/dirs.html
	 */
	
}
