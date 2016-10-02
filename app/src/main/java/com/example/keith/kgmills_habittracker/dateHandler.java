package com.example.keith.kgmills_habittracker;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kgmills
 * Last Edit: Oct 2 2016
 * A static class that is used when creating habits typically.
 *  Be sure to go through this class and
 * account for days and months that don't exist..
 */

public final class dateHandler {

    /**
     * Typically used for pre-filling the date fields for creating a new habit
     * @return A string array containing the year, month and day
     */
    public static String[] getCurrentDate() {
        // Code from http://www.xyzws.com/Javafaq/how-to-use-simpledateformat-class-formating-parsing-date-and-time/142
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date()).split("-");
    }

    // I chose to make this method an INT instead of
    // boolean because I wanted non-binary error codes.

    /**
     * Checks to see if the string array (when deleting habit completions
     * or making new habits) is a valid date
     * @param userInput The string array to be tested
     * @return 0 -> All is well
     *         1 -> Lengths are incorrect
     *         2 -> Fields did not contain only ints
     */
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

    /**
     * Turns a string array into a single string separated by "-"
     * @param date String array
     * @return Single string
     */
    public static String reformatDate(String[] date) {
        return date[0] + "-" + date[1] + "-" + date[2];
    }

    /**
     * HabitLog's make entries with keys "yyyyMMdd"
     * This function makes that happen
     * @return An eight charater length string
     */
    public static String logDate() {
        String[] s = getCurrentDate();
        return s[0]+s[1]+s[2];
    }
}
