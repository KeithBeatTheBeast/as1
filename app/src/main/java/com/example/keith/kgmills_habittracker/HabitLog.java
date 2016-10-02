package com.example.keith.kgmills_habittracker;

import java.util.ArrayList;

/**
 * Created by kgmills
 * Last Edit: Oct 2 2016
 * CLASS HabitLog
 * Responsible for holding data regarding
 * a habit's completions.
 */
public class HabitLog {
    /**
     * Hold values in ArrayList
     * Where yyyy-MM-dd -> yyyyMMdd
     * 8 digit Integer as "key"
     * And number of times completed that day as "value"
     */
    private ArrayList<Integer[]> log;

    public HabitLog() {
        this.log = new ArrayList<>();
    }

    /**
     * write to log
     * @param d: Date in form yyyyMMdd
     * @param num: number of times habit complete that day
     * Function converts date to integer, then
     * store it in log, or updates log if already there.
     */
    public void writeToLog(String d, Integer num) {
        Integer date = Integer.parseInt(d);
        for (Integer[] aDay: this.log) {
            if (aDay[0].equals(date)) {
                aDay[1] = num;
                return;
            }
        }
        this.log.add(new Integer[]{date, num});
    }

    /**
     * remove a completion
     * @param d: date in form yyyyMMdd
     * @return: 0 -> all is well
     *          3 -> date not found
     *          4 -> date found, but already 0
     * Why no 1 or 2? This function works with another
     * in class dateHandler to control inputs
     * Return codes translate into GUI toasts,
     * so dateHandler holds domain over numbers 1 and 2
     */
    public Integer removeCompletion(String d) {
        Integer date = Integer.parseInt(d);
        for (Integer[] aDay: this.log) {
            if (aDay[0].equals(date)) {
                if (aDay[1].equals(0)) {
                    return 4;
                }
                aDay[1] -= 1;
                return 0;
            }
        }
        return 3;
    }

    /**
     * get habits cumulative info
     * @return An integer array containing
     * i) The number of dates habit has been active for
     * ii) Number of those dates that have been completed
     * iii) Total completes (counting multiple entries in a day)
     * This function is passed to the habit itself and used to
     * make a message that is presented as a toast to the user.
     */
    public String[] getCumulativeInfo() {
        Integer missedCompletes = 0;
        Integer totalCompletes = 0;
        for (Integer[] aDay: this.log) {
            if (aDay[1].equals(0)) {
                missedCompletes += 1;
            }
            totalCompletes += aDay[1];
        }

        return new String[]{String.valueOf(this.log.size()),
                String.valueOf(this.log.size() - missedCompletes),
                totalCompletes.toString()};
    }

    // Above function but only ints
    // Used for JUnit testing
    public Integer[] getCumulativeInfoInInts() {
        Integer missedCompletes = 0;
        Integer totalCompletes = 0;
        for (Integer[] aDay: this.log) {
            if (aDay[1].equals(0)) {
                missedCompletes += 1;
            }
            totalCompletes += aDay[1];
        }

        return new Integer[]{this.log.size(),
                this.log.size() - missedCompletes,
                totalCompletes};
    }
}
