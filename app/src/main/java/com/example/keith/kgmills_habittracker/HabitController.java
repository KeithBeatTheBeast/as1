package com.example.keith.kgmills_habittracker;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by kgmills
 * Last Edit: Oct 2 2016
 * Class HabitController
 * Contains Habits, organizes them based on daily relevance
 * Tells them when it's a new day.
 */

public class HabitController {

    /**
     * allHabits is... all habits
     * activeHabits only those to be done today
     * Current day of week, 1-7
     */
    private ArrayList<Habit> allHabits;
    private ArrayList<Habit> activeHabits;
    private Integer currentDayOfWeek;

    public HabitController() {
        this.allHabits = new ArrayList<>();
        this.activeHabits = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        this.currentDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Adds habit to all, refreshes active to see if it's included
     * If it is included, tell it to write a "0" to it's log for today.
     * @param habit: The habit to be entered
     */
    public void addAHabit(Habit habit) {
        allHabits.add(habit);
        this.refreshRelevantHabits();
        if (this.activeHabits.contains(habit)) {
            habit.newDayCheck();
        }
    }

    /**
     * Removes habit from all, refreshes active to get rid of it too
     * @param habit: Habit to be removed
     */
    public void removeAHabit(Habit habit) {
        allHabits.remove(habit);
        this.refreshRelevantHabits();
    }

    /**
     * See if it's a new day.
     * If it is, tell the habits for this day to add a new entry to their logs.
     */
    public void checkDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        if (!this.currentDayOfWeek.equals(cal.get(Calendar.DAY_OF_WEEK))){
            this.currentDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

            if (!this.allHabits.isEmpty()) {
                this.refreshRelevantHabits();
                this.newDay();
            }
        }
    }

    /**
     * Refreshes active habits so that only those in allHabits
     * and that are active today are presented
     */
    private void refreshRelevantHabits() {
        this.activeHabits.clear();
        for (Habit h: allHabits) {
            if (h.isWorkingDay(this.currentDayOfWeek)) {
                activeHabits.add(h);
            }
        }
    }

    /**
     * Tells all habits in active habits it's a new day.
     */
    private void newDay() {
        for (Habit h: activeHabits) {
            h.newDayCheck();
        }
    }

    // Getters
    public ArrayList<Habit> getAllHabits() {
        return allHabits;
    }

    public ArrayList<Habit> getActiveHabits() {
        return activeHabits;
    }
}
