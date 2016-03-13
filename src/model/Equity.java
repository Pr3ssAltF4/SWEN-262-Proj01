package src.model;

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

        //Should be in this formate "Ticker,numberOfSticks,pricePerStock,Date"
        public static Equity importEquity(String line){
            String[] args = line.split(",");
            String[] date =  args[3].split(" ");
            Equity equity = new Equity(args[0],Integer.parseInt(args[1]),Double.parseDouble(args[2]));
            try {
                equity.setDateAcquired(dateFormat.parse(args[3]));
            }catch(Exception ex){
                System.out.println("Could not import date:>>"+ex.getMessage());
            }
            return equity;
        }
    }
