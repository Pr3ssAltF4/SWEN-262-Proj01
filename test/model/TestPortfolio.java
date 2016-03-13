package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class TestPortfolio {
    public static void main(String args[]) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        Calendar dates = Calendar.getInstance();
        System.out.println(dates.getTime());
    }
}