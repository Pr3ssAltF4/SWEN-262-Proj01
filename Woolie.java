
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


public class Woolie {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {


        System.out.println(inMarket("FINANCE"));

    }

    /**
     *
     * @param symbol Ticker Symbol
     * @return a XML document from yahoo finance for the specified ticker symbol
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static Document retrieveXML(String symbol) throws IOException, ParserConfigurationException, SAXException{
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

    /*
    public static ArrayList<String> parseCSV(String path) throws IOException{
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;

        while((line = reader.readLine()) != null){
            Pattern pattern = Pattern.compile("(\"[^\"]*\")");
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()){
                list.add(matcher.group(0));
            }
        }
        System.out.println(list);
        return list;
    } */

    /**
     *
     * @param symbol symbol to search
     * @return realtime price of the specified ticker symbol from yahoo finance
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Double getPrice(String symbol) throws ParserConfigurationException, SAXException, IOException {
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
    public static ArrayList<String> inMarket(String market) throws IOException {
        ArrayList<String> inMarket = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/equities.csv"));
        String line;

        while((line = reader.readLine()) != null){
            String m = line.split(",")[line.split(",").length-1];
            m = m.replace("\"", "");
            if (m.equals(market)){
                inMarket.add(line);
            }
        }
        return inMarket;
    }

    /**
     *
     * @param symbol Ticker Symbol to search for
     * @return The market for the ticker symbol or null if none is found
     * @throws IOException
     */
    public static String getMarket(String symbol) throws IOException {
        ArrayList<String> inMarket = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/equities.csv"));
        String line;

        while((line = reader.readLine()) != null){
            String s = line.split(",")[0];
            String m = line.split(",")[line.split(",").length-1];
            if (s == symbol){
                return m;
            }

        }
        return null;
    }

    /**
     *
     * @param symbol Ticker Symbol
     * @return Information on the specified Ticker symbol from csv file
     * @throws IOException
     */
    public static String getTickerInfo(String symbol) throws IOException {
        ArrayList<String> inMarket = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/equities.csv"));
        String line;

        while((line = reader.readLine()) != null){
            String s = line.split(",")[0];
            if (s == symbol){
                return line;
            }

        }
        return null;
    }
}