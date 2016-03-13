package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class TestPortfolio {
    public static void main(String args[]) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
        Date date = new Date();
        String dateString = dateFormat.format(date);
        System.out.println(dateString);
        try {
            date = dateFormat.parse(dateString);
            dateString = dateFormat.format(date);
            System.out.println(dateString);
        }catch(Exception ex){

        }

    }
}