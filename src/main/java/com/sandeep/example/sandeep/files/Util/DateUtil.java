package com.sandeep.example.sandeep.files.Util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getDateTimeByPosition(String dateTime, int index) {
        System.out.println("dateTime:-->"+dateTime);
        String[] dateSplit = dateTime.split(" ");
        String newDate = dateSplit[0].concat(" ").concat(dateSplit[index]);
        return newDate;
    }


    //Format 5/10/2013 0:20:48, This is ignoring seconds value as I planned to get data every hour
    public static Long dateTime(String dateTime) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH");
        formatter.setLenient(false);
        try {
            Date date = formatter.parse(dateTime);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
