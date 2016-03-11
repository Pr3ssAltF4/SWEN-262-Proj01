package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FPTS {

    public static void main(String[] args) {

        HashMap<String, Double> tickerprice = getTickerPrice("src/model/equities.csv");


    }

    private static HashMap<String, Double> getTickerPrice(String path){

        HashMap<String, Double> tickerprice = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line, ticker;

            while((line = reader.readLine()) != null){
                double price;
                ticker = line.split(",")[0];
                Pattern pattern = Pattern.compile("\\d+.\\d+");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    try {
                        price = Double.parseDouble(matcher.group(0));
                    } catch (NumberFormatException e) {
                        continue;
                    }
                    tickerprice.put(ticker, price);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return tickerprice;
    }
}
