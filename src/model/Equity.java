package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

    /*
	Equity (aka Holdings)
	 */
public class Equity{

        //Here to be used to put date to string.
        static DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
        private String ticker;
        private int numberOfStocks;
        private double pricePerStock;


        Date date = new Date();
        private Date dateAcquired = date;

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
        Sets the DateAcquired
         */
        public void setDateAcquired(Date dateAcquired) {
            this.dateAcquired = dateAcquired;
        }

        public String exportEquity(){
            String export = "";
            export += ticker + ",";
            export += numberOfStocks + ",";
            export += pricePerStock + ",";
            export += dateFormat.format(dateAcquired);

            return export;
        }
    }
