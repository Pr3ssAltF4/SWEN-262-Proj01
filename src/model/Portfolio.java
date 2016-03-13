package model;


import java.io.PrintWriter;
import java.util.ArrayList;

public class Portfolio{
	private String name;
	private ArrayList<Equity> equities;
	private int id;
	private User user;
	public ArrayList<Transaction> transaction_history; // complete transaction history
	public ArrayList<Account> accounts; // All account assigned to user

	//Portfolio constructor
	public Portfolio(String name, int id, User user){
		this.name = name;
		this.equities = new ArrayList<>();
		this.id = id;
		this.user = user;
		this.transaction_history = new ArrayList<>();
		this.accounts = new ArrayList<>();
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
	
	public ArrayList<Equity> getEquityByTicker(String ticker){
        ArrayList<Equity> filtered = new ArrayList<>();
		for (Equity e: this.equities){
			if (e.getTicker() == ticker){
				filtered.add(e);
			}
		}
        return filtered;
	}

    public int getShares(String ticker){
        int shares = 0;
		for (Equity e: this.equities){
			if (e.getTicker() == ticker){
				shares += e.getNumberOfStocks();
			}
		}
        return shares;
    }


	public void addTransaction(Transaction transaction) {transaction_history.add(transaction);}

	//removes the transaction from the transaction history based on the id
	public void removeTransaction(int transaction_id) {

		for(int x = 0; x < transaction_history.size(); x++){
			if(transaction_history.get(x).getId() == transaction_id) {
				transaction_history.remove(x);
				return;
			}
		}
	}
	public void addAccount(String name, double balance) {
		accounts.add(new Account(name, this.id, balance));
	}
	public void removeAccount(String name) {
		for (Account a: this.accounts){
			if (a.getName() == name){
				accounts.remove(a);
			}
		}
	}

	//returns total cash from all accounts
	public double evalCash(){
		double total = 0;
		for (Account a: this.accounts){
			total += a.getBalance();
		}
		return total;
	}


	/** NEED TO ADD TRANSACTION
	 *
	 * @param ticker - ticker symbol for equity
	 * @param shares - amount of equity you want to purchase
	 * @param price - price per equity
	 * @param useCash -
	 */
	public void buyEquity(String ticker, int shares, double price, boolean useCash){
		if (useCash){
			double cost = shares * price;
			ArrayList<Account> canPurchase = canPurchase(cost, this.accounts);
			//allow user to select what account to use through controller

			if (canPurchase.isEmpty()){
				System.out.println("You do not have enough cash to purchase these equities");
			} else {
				canPurchase.get(0).withdraw(cost);//put in first index until controllers handles this
				addEquity(ticker, shares, price);
			}
		} else {
			addEquity(ticker, shares, price);
		}
	}

	public void removeEquity(String ticker, int shares, double price, boolean toCash, int... id){
		if (toCash){
			double earned = shares * price;
			accounts.get(0).deposit(earned);
			ArrayList<Equity> toRemove = getEquityByTicker(ticker);
		}
	}

	/**
	 *
	 * @param cost - cost of equities
	 * @param accounts - list of cash accounts
	 * @return list of account with enough balance to buy equities
	 */
	public ArrayList<Account> canPurchase(double cost, ArrayList<Account> accounts){
		ArrayList<Account> canPurchase = new ArrayList<>();
		for (Account a: this.accounts){
			if (a.getBalance() >= cost){
				canPurchase.add(a);
			}
		}
		return canPurchase;
	}

	public void depostitByname(String name, double ammount){
		for(Account a: this.accounts){
			if (a.getName() == name){
				a.deposit(ammount);
				break;
			}
		}
	}
}
