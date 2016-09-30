package com.example.keith.kgmills_habittracker;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by keith on 9/26/2016.
 * A static class that is used when creating habits typically.
 *  Be sure to go through this class and
 * account for days and months that don't exist..
 */

public final class dateHandler {

    public static String[] getCurrentDate() {
        // Code from http://www.xyzws.com/Javafaq/how-to-use-simpledateformat-class-formating-parsing-date-and-time/142
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date()).split("-");
    }

    // I chose to make this method an INT instead of
    // boolean because I wanted non-binary error codes.
    public static Integer parseDate(String[] userInput) {

        if ((userInput.length != 3) ||
                (userInput[0].length() != 4) ||
                (userInput[1].length() != 2) ||
                (userInput[2].length() != 2)) {
            return 1;
        }

        try {
            for (String s: userInput) {
                Integer.parseInt(s);
            }
        }
        catch (NumberFormatException e) {
            return 2;
        }
        return 0;
    }

    public static String reformatDate(String[] date) {
        return date[0] + "-" + date[1] + "-" + date[2];
    }
}
