package com.example.keith.kgmills_habittracker;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by keith on 9/25/2016.
 I want this class to
 1. Store habits via an array list
 remove habits
 check to see if the day has changed
 Check to see which habits must be completed when a day changes
 store these new habits in another array list

 The active habit list must be emptied completely at the end of a day.

 SUN = 1
 MON = 2
 TUE =
 ...
 */

public class HabitController {

    private ArrayList<Habit> allHabits;
    private ArrayList<Habit> activeHabits;
    private Integer currentDayOfWeek;

    public HabitController() {
        this.allHabits = new ArrayList<>();
        this.activeHabits = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        this.currentDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    }

    public void addAHabit(Habit habit) {
        allHabits.add(habit);
        this.refreshRelevantHabits();
        if (this.activeHabits.contains(habit)) {
            habit.newDayCheck();
        }
    }

    public void removeAHabit(Habit habit) {
        allHabits.remove(habit);
        this.refreshRelevantHabits();
    }

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

    private void refreshRelevantHabits() {
        this.activeHabits.clear();
        for (Habit h: allHabits) {
            if (h.isWorkingDay(this.currentDayOfWeek)) {
                activeHabits.add(h);
            }
        }
    }

    private void newDay() {
        for (Habit h: activeHabits) {
            h.newDayCheck();
        }
    }

    public ArrayList<Habit> getAllHabits() {
        return allHabits;
    }

    public ArrayList<Habit> getActiveHabits() {
        return activeHabits;
    }
}
