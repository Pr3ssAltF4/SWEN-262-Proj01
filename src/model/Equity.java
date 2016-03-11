package model;

import java.util.Date;

    /*
	Equity (aka Holdings)
	 */
public class Equity{
        private String ticker;
        private int numberOfStocks;
        private double pricePerStock;
        private Date dateAcquired;

        /*
        A constructor for the Equity class
         */
        public Equity(String ticker, int numberOfStocks, double pricePerStock) {
            this.ticker = ticker;
            this.numberOfStocks = numberOfStocks;
            this.pricePerStock = pricePerStock;
            this.dateAcquired = new Date();
        }

        /*
        Returns the Ticker
        */
        public String getTicker() {
            return ticker;
        }

        /*
        Sets a Ticker
         */
        public void setTicker(String ticker) {
            this.ticker = ticker;
        }

        /*
        Returns the number of a given stock
         */
        public int getNumberOfStocks() {
            return numberOfStocks;
        }

        /*
        Set the number of stocks
         */
        public void setNumberOfStocks(int numberOfStocks) {
            this.numberOfStocks = numberOfStocks;
        }

        /*
        Get the price of the stock
         */
        public double getPricePerStock() {
            return pricePerStock;
        }

        /*
        Set the prive for the stock
         */
        public void setPricePerStock(double pricePerStock) {
            this.pricePerStock = pricePerStock;
        }

        /*
        Get the date aquired
         */
        public Date getDateAquired() {
            return dateAcquired;
        }

        /*
        Set the date aquired
         */
        public void setDateAquired(Date dateAquired) {
            this.dateAcquired = dateAquired;
        }
    }
