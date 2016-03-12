package Import_Export;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CSVParser {

    public static HashMap<String, Double> tickerprice;

    public static void main(String[] args) throws IOException {
        tickerprice = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader("../Resources/equities.csv"));
        String line;

        while((line = reader.readLine()) != null){
            double price;
            String ticker = line.split(",")[0];
            Pattern pattern = Pattern.compile("\\d+.\\d+");
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()){
                try {
                    price = Double.parseDouble(matcher.group(0));
                } catch (NumberFormatException e) {
                    continue;
                }
                tickerprice.put(ticker, price);
            }
        }
        //System.out.println(tickerprice);
    }
}
