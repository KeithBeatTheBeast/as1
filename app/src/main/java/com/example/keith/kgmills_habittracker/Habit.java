package com.example.keith.kgmills_habittracker;

/**
 * Created by keith on 9/24/2016.
 * Core class of Assignment 1: Habit
 */

public class Habit {

    private String name;               // As per
    private String dateOfCreation;     // assignment
    private Boolean[] daysOfWeek;       // specifications
    private Boolean dailyCompletion;   // Checks if habit complete during day
    private Integer daysToComplete;    // Number of relevant days since creation
    private Integer daysComplete;      // Days complete since creation
    private Integer daysMissed;        // Days missed since creation
    private Integer overTimeToday;     // Times additionally completed before reset.
    private Integer overTimeDays;      // For instances when habit is completed twice or more
                                       // in one day
    // Simple constructor
    public Habit(String name, String DoC, Boolean[] days) {
        this.name = name;
        this.dateOfCreation = DoC;
        this.daysOfWeek = days;
        this.dailyCompletion = false;
        this.daysToComplete = 1;
        this.daysComplete= 0;
        this.daysMissed = 0;
        this.overTimeToday = 0;
        this.overTimeDays = 0;
    }

    // Called when user declares a task complete
    public void completion(){

        // If you haven't done the habit yet
        if (!this.dailyCompletion) {
            this.dailyCompletion = true;
            this.daysComplete += 1;
        }
        // If you have
        else {
            this.overTimeToday += 1;
            this.overTimeDays += 1;
        }
    }

    // Check if this is a day I want to do the habit.
    public Boolean isWorkingDay(Integer day) {
        return this.daysOfWeek[(day-1) % daysOfWeek.length];
    }

    // Called when a new day I want to complete a habit happens
    public void newDayCheck() {

        if (!this.dailyCompletion) {
            this.daysMissed += 1;
        }

        this.daysToComplete += 1;
        this.dailyCompletion = false;
        this.overTimeToday = 0;
    }

    // Info to user typically done through a toast. Here is the string for it.
    public String sendCompletionInfo() {

        return "Habit created on " + this.dateOfCreation + ". " +
                "Days for habit complete: " + this.daysComplete.toString() +
                "/" + this.daysToComplete.toString() + ". " +
                "Days missed: " + this.daysMissed.toString() +
                ". Overtime: " + this.overTimeDays.toString();
    }

    @Override
    public String toString() {
        String answer = this.name;
        if (this.dailyCompletion) {
            answer += " | Complete";
        }
        if (this.overTimeToday > 0) {
            answer += " with " +
                    this.overTimeToday +
                    " overtime.";
        }
        return answer;
    }

    // Standard getters, used for JUNIT testing
    public String getName() {
        return this.name;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public Boolean getDailyCompletion() {
        return dailyCompletion;
    }

    public Integer getDaysToComplete() {
        return daysToComplete;
    }

    public Integer getDaysComplete() {
        return daysComplete;
    }

    public Integer getDaysMissed() {
        return daysMissed;
    }

    public Integer getOverTimeDays() {
        return overTimeDays;
    }
}