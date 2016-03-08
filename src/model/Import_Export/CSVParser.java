package Import_Export

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

import model.holding.Equity;

/*
Parses the CSV file and stors them as an ArrayList to be retreved latter
 */
public class CSVParser {
    private File file = File("../Resources/equities.csv");
    private static ArrayList<Equity> stocks = new ArrayList<>();

    /*
    Parces the CSV File
     */
    public boolean parceStocks() {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                stocks.add(parseLine(line));
            }
            scanner.close();
        } catch (Exceptio ex) {
            ex.printStackTrace();
            return false;
        }

        return true;

    }

    /*
    Returns the static ArrayList
     */
    public ArrayList<Equity> getStocks(){
        return stocks;
    }

    /*
    Processes a line.
     */
    private Equity parseLine(String line){
        String ticker = line.split(',')[0];
        double price = line.match("\d.\d");
        return Equity equity = new Equity(ticker,-1,price);
    }

}