
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;


public class Woolie {
    /**
     *
     * @param symbol Ticker Symbol
     * @return a XML document from yahoo finance for the specified ticker symbol
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public Document retrieveXML(String symbol) throws IOException, ParserConfigurationException, SAXException{
        String front = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22";
        String back = "%22)&env=store://datatables.org/alltableswithkeys";
        String fullUrl = front + symbol + back;

        // Create a URL and open a connection
        URL YahooURL = new URL(fullUrl);
        HttpURLConnection con = (HttpURLConnection) YahooURL.openConnection();

        // Set the HTTP Request type method to GET (Default: GET)
        con.setRequestMethod("GET");
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);

        // Created a BufferedReader to read the contents of the request.
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        // response is the contents of the XML
        //System.out.println(response.toString());

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new InputSource(new StringReader(response.toString())));

        return doc;
    }


    /**
     *
     * @param line line from csv file
     * @return list with string values from line
     * @throws IOException
     */
    public ArrayList<String> parseLine(String line) throws IOException{
        String[] split = line.split("\",\"");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(split));
        for (int i = 0; i < list.size(); i++){
            list.set(i, list.get(i).replace("\"", ""));
        }
        return list;
    }

    /**
     *
     * @param symbol symbol to search
     * @return realtime price of the specified ticker symbol from yahoo finance
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public Double getPrice(String symbol) throws ParserConfigurationException, SAXException, IOException {
        Document d = retrieveXML(symbol);

        Double price = Double.parseDouble(d.getElementsByTagName("LastTradePriceOnly").item(0).getTextContent());
        System.out.println(price);
        return price;
    }

    /**
     *
     * @param market Market to search for
     * @return a list of all equities in the specfied market
     * @throws IOException
     */
    public ArrayList<ArrayList> inMarket(String market) throws IOException {
        ArrayList<ArrayList> inMarket = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/equities.csv"));
        String line;
        ArrayList<String> list;
        while((line = reader.readLine()) != null){
            list = parseLine(line);
            if (list.contains(market)) {
                inMarket.add(list);
            }
        }
        reader.close();
        return inMarket;
    }

    /**
     *
     * @param symbol Ticker Symbol to search for
     * @return The market for the ticker symbol or null if none is found
     * @throws IOException
     */
    public ArrayList<String> getMarket(String symbol) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/equities.csv"));
        String line;
        ArrayList<String> list;
        while((line = reader.readLine()) != null){
            list = parseLine(line);
            if (list.get(0) == symbol){
                ArrayList<String> market = new ArrayList<>();
                switch (list.size()){
                    case 4:
                        market.add(list.get(3));
                        break;
                    case 5:
                        market.add(list.get(3));
                        market.add(list.get(4));
                        break;
                }
                reader.close();
                return market;
            }

        }
        reader.close();
        return null;
    }

    /**
     *
     * @param symbol Ticker Symbol
     * @return Information on the specified Ticker symbol from csv file
     * @throws IOException
     */
    public ArrayList<String> getInfoByTicker(String symbol) throws IOException {
        ArrayList<String> inMarket = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/equities.csv"));
        String line;
        ArrayList<String> list;
        while((line = reader.readLine()) != null){
            list = parseLine(line);

            if (list.get(0) == symbol){
                reader.close();
                return list;
            }

        }
        reader.close();
        return null;
    }

    public ArrayList<String> getInfoByName(String name) throws IOException {
        ArrayList<String> inMarket = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/equities.csv"));
        String line;
        ArrayList<String> list;

        while((line = reader.readLine()) != null){
            list = parseLine(line);
            if (list.get(1) == name){
                reader.close();
                return list;
            }
        }
        return null;
    }
}