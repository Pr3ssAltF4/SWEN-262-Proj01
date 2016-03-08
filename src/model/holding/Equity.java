package model.holding;
 import java.util.Date;

    /*
	Equity (aka Holdings)
	 */
public class Equity{
        private String ticker = "";
        private int numberOfStocks = 0;
        private double pricePerStock = 0.0;
        private Date dateAquired = Date();

        /*
        A constructor for the Equity class
         */
        public Equity(String ticker, int numberOfStocks, double pricePerStock) {
            this.ticker = ticker;
            this.numberOfStocks = numberOfStocks;
            this.pricePerStock = pricePerStock;
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
        Get teh date aquired
         */
        public Date getDateAquired() {
            return dateAquired;
        }

        /*
        Set the date aquired
         */
        public void setDateAquired(Date dateAquired) {
            this.dateAquired = dateAquired;
        }
    }
