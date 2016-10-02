package com.example.keith.kgmills_habittracker;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;
/**
 * Created by kgmills
 * Last Edit: Oct 2 2016
 * JUnit tests for class HabitController
 */

public class habitControllerTests {

    private static final String testDate = "1995-01-25";

    /**
     * Check to see if list responds.
     */
    @Test
    public void testAddAndRemove() {
        HabitController hc = new HabitController();
        Habit h = new Habit("A habit", testDate, new Boolean[]{false, true, true, false, false, false, false});
        hc.addAHabit(h);
        assertTrue(hc.getAllHabits().contains(h));
        hc.removeAHabit(h);
        assertFalse(hc.getAllHabits().contains(h));
    }

    /**
     * Check to see if active list responds
     */
    @Test
    public void testActiveHabits() {
        Calendar cal = Calendar.getInstance();
        Boolean[] today = {false, false, false, false, false, false, false};
        Boolean[] tomorrow = {false, false, false, false, false, false, false};
        today[(cal.get(Calendar.DAY_OF_WEEK)-1)%7] = true;
        tomorrow[(cal.get(Calendar.DAY_OF_WEEK))%7] = true;

        Habit h1 = new Habit("Today's Habit", testDate,
                today);
        Habit h2 = new Habit("Tomorrow's Habit", testDate,
                tomorrow);
        HabitController hc = new HabitController();
        hc.addAHabit(h1);
        hc.addAHabit(h2);
        assertTrue(hc.getActiveHabits().contains(h1));
        assertFalse(hc.getActiveHabits().contains(h2));
    }
}
