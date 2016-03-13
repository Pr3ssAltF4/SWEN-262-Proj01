package model;

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


	public void addTransaction(Transaction transaction) {}
	public void removeTransaction(int transaction_id) {}
	public void addAccount(Account account) {}
	public void removeAccount(int account_id) {}

	//returns total cash from all accounts
	public double evalCash(){
		double total = 0;
		ArrayList<Account> accounts =  this.accounts;
		for (int i = 0; i < accounts.size(); i++){
			total += accounts.get(i).getBalance();
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

	public void removeEquity(String ticker, int shares, double price, boolean toCash){
		if (toCash){
			double earned = shares * price;
			//Allow users to select what account they want to put the cash in
			accounts.get(0).deposit(earned); //put in first index until controller handles this
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
		for (int i = 0; i < accounts.size(); i++){
			if (accounts.get(i).getBalance() >= cost){
				canPurchase.add(accounts.get(i));
			}
		}
		return canPurchase;
	}
}
