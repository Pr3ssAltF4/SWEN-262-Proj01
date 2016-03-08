package model.holding;

public class CashAccount {
	private int amount = 0;

	/*
	Constructor for the CashAccount
	 */
	public CashAccount(int cash){
		amount = cash;
	}

	/*
	Getter for the amount
	 */
	public int getAmount() {
		return amount;
	}

	/*
	Setter for the amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
