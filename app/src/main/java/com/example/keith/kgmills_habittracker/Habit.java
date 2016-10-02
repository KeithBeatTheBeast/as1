package com.example.keith.kgmills_habittracker;

/**
 * Created by kgmills
 * Core class of Assignment 1: Habit
 *
 * TO-DO:
 * Implement activity for log.
 * Equals function for assisting input/denying multiple of same habit.
 * Implement equals into droidMVC
 * Habit must not become active before create day.
 * Unit tests
 * Comments
 * UML.
 * Compiled apk
 */

public class Habit {

    private String name;              // As per
    private String dateOfCreation;    // assignment
    private Boolean[] daysOfWeek;     // specifications
    private Boolean dailyCompletion;  // Checks if habit complete during day
    private Integer completesToday;   // If done today
    private HabitLog myLog;           // Log of activity

    // Simple constructor
    public Habit(String name, String DoC, Boolean[] days) {
        this.name = name;
        this.dateOfCreation = DoC;
        this.daysOfWeek = days;
        this.dailyCompletion = false;
        this.completesToday = 0;
        this.myLog = new HabitLog();

    }

    /**
     * Called by GUI through MVC
     * Says it's done for today
     * And posts changes to log.
     */
    public void completion(){
        this.dailyCompletion = true;
        this.completesToday += 1;
        this.myLog.writeToLog(dateHandler.logDate(),
                this.completesToday);
    }

    /**
     * Used by HabitController to decide who is active
     * @param day: 1 is Sunday, 2 is Mon, ...., 7 is sat
     * @return Do I complete this habit on this day?
     */
    public Boolean isWorkingDay(Integer day) {
        return this.daysOfWeek[(day-1) % daysOfWeek.length];
    }

    /**
     * If we didn't complete the habit today write a 0
     * for the entry.
     * It's a new day so reset things.
     */
    public void newDayCheck() {

        if (!this.dailyCompletion) {
            this.myLog.writeToLog(dateHandler.logDate(),
                    0);
        }
        this.dailyCompletion = false;
        this.completesToday = 0;
    }

    /**
     * Called by GUI through MVC to get info about habit.
     * @return A string that will be used to toast.
     */
    public String sendCompletionInfo() {
        String[] info = this.myLog.getCumulativeInfo();
        return "Habit created on " + this.dateOfCreation + ". " +
                "Days for habit complete: " + info[1] + "/" +
                info[0] + ". " + "Total completes: " + info[2] +
                ". ";
    }

    /**
     * Used for ArrayAdapters
     * @return String with name and completion/overtime status
     */
    @Override
    public String toString() {
        String answer = this.name;
        if (this.dailyCompletion) {
            answer += " | Complete";
        }
        if (this.completesToday > 1) {
            answer += " with " +
                    (this.completesToday-1) +
                    " overtime.";
        }
        return answer;
    }

    // Standard getters, used for JUNIT testing mostly
    public String getName() {
        return this.name;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public HabitLog getMyLog() { return this.myLog; }
}