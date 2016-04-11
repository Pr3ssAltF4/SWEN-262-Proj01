package src;

import java.util.ArrayList;

import Trans.*;

public class Controller {

	private LoginFrame lg;
	
	public Controller(){
		lg = new LoginFrame();
		lg.setVisible(true);
	}
	
	//import
	//don't know what I need since tyler isn't done yet
	public void imp(){
		
	}
	
	//export
	//don't know what I need since tyler isn't done yet
	public void exp(){
		
	}
	
	//or save
	public void update(){
		
	}
	
	public void addEquity(String ticker, int numberOfStocks, double pricePerStock){
		
	}
	
	public void removeEquity(Equity e){
		
	}
	
	public void updateEquity(Equity equity, String ticker, int numberOfStocks, double pricePerStock){
		
	}
	
	public void YahooTimer(int timeInterval){
		
	}
	
	public void searchEquities(String searchString){
		
	}
	
	public void newEquityTransaction(Equity equity, int numberOfStock){
		
	}
	
	public void newAccountTransaction(String transType, Account account, double amount){
		
	}
	
	public void undoTransaction(Transaction t){
		
	}
	
	public void redoTransaction(Transaction t){
		
	}
	
	public void addAccount(String name, double balance){
		
	}
	
	public void removeAccount(Account a){
		
	}
	
	public void updateAccount(String name, double balance){
		
	}
	
	public double runSim(String simType, int steps, char stepSize, double pricePerStock, double percentChange){
		return 0;
	}
	
	public void resetSim(){
		
	}
	
	public static void main(String[] args) {
		LoginFrame lg = new LoginFrame();
		lg.setVisible(true);
	}

}
