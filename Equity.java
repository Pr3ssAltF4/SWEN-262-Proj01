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
        private int owned;
        private double pricePerStock;
        private double pointHigh;
        private double pointLow;
        private String sector;
        private Date dateAcquired = new Date();



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

        public double getPointHigh() {
            return pointHigh;
        }

        public void setPointHigh(double pointHigh) {
            this.pointHigh = pointHigh;
        }

        public double getPointLow() {
            return pointLow;
        }

        public void setPointLow(double pointLow) {
            this.pointLow = pointLow;
        }

        public int getOwned() {
            return owned;
        }

        public void setOwned(int owned) {
            this.owned = owned;
        }

        public void addToOwned(int owned){
            this.owned += owned;
        }

        public String getSector() {
            return sector;
        }

        public void setSector(String sector) {
            this.sector = sector;
        }

        public double getTotalCost(){
            return this.numberOfStocks * this.pricePerStock;
        }

        /**
         * Method used to export the Equity
         * @return
         */
        public String exportEquity(){
            String export = "";
            export += ticker +"," +owned+","+ numberOfStocks +","+pricePerStock +","
                    +pointHigh+","+pointLow+","+sector+","+dateFormat.format(dateAcquired);
            return export;
        }

        /**
         * Used to import an equity from a string.
         * @param equityString
         */
        public void importEquity(String equityString){
            String[] varables = equityString.split(",");
            ticker = varables[0];
            owned = Integer.parseInt(varables[1]);
            numberOfStocks = Integer.parseInt(varables[2]);
            pricePerStock = Double.parseDouble(varables[3]);
            pointHigh = Double.parseDouble(varables[4]);
            pointLow = Double.parseDouble(varables[5]);
            sector = varables[6];
            try {
                dateAcquired = dateFormat.parse(varables[7]);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }finally {
                dateAcquired = new Date();
            }
        }

    }