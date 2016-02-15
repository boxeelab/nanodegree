package com.nanodegreedjm.p1.app.popularmovies;



import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dhruba on 2/9/16.
 */
public class Utility {

    public static final String DATE_FORMAT = "yyyyMMdd";

    public static long getLong(String date,String dateFormat)
    {
        DateFormat format = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
        try {
            Date d = format.parse(date);
            return d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;

        }
    }

    public static String getDayString(long dateInMillis) {
        SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("dd MMM yyyy");
        return shortenedDateFormat.format(dateInMillis);

    }

    public static String getFormatRating(float val)
    {

        DecimalFormat df = new DecimalFormat("#.#");
        return df.format(val);
    }




}
