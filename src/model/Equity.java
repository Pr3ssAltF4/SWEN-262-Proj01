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

        /**
         *  Equity constructor
         * @param ticker - ticker symbol
         * @param numberOfStocks - number of stocks
         * @param pricePerStock - price per stock
         */
        public Equity(String ticker, int numberOfStocks, double pricePerStock) {
            this.ticker = ticker;
            this.numberOfStocks = numberOfStocks;
            this.pricePerStock = pricePerStock;
            this.dateAcquired = new Date();
        }

        /**
         *
         * @return returns Equity's ticker symbol
         */
        public String getTicker() {
            return ticker;
        }

        /**
         *
         * @return return number of stocks
         */
        public int getNumberOfStocks() {
            return numberOfStocks;
        }

        /**
         *
         * @param numberOfStocks - set the number of stocks
         */
        public void setNumberOfStocks(int numberOfStocks) {
            this.numberOfStocks = numberOfStocks;
        }

        /**
         *
         * @return return the price per stock
         */
        public double getPricePerStock() {
            return pricePerStock;
        }

        /**
         *
         * @param pricePerStock - set price per stock
         */
        public void setPricePerStock(double pricePerStock) {
            this.pricePerStock = pricePerStock;
        }

        /**
         *
         * @return - return date
         */
        public Date getDateAquired() {
            return dateAcquired;
        }

        /**
         *
         * @param dateAcquired - set the date of Equity
         */
        public void setDateAcquired(Date dateAcquired) {
            this.dateAcquired = dateAcquired;
        }

        /**
         * convert equity object into csv
         * @return string for equity object
         */
        public String exportEquity(){
            String export = "";
            export += ticker + ",";
            export += numberOfStocks + ",";
            export += pricePerStock + ",";
            export += dateFormat.format(dateAcquired);

            return export;
        }


        /**
         * reads string and convert it into an Equity object
         * @param line - string to convert
         * @return returns Equity object from string
         */
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