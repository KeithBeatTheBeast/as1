package com.example.keith.kgmills_habittracker;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by keith on 9/24/2016.
 * JUNIT tests for class Habit
 */

public class habitUnitTests {

    // Mon, Tue, Wed, Thu, Fri
    private static final Boolean[] daysInAWeek = {false, true, true, true, true, true, false};
    private String name = "aNewName";

    // Birthday of programmer xD
    private static final String testDate = "1995-01-25";
    private Habit testHabit = new Habit(name, testDate, daysInAWeek);

    // Test name getter
    @Test
    public void testName() {
        assertEquals(name, testHabit.getName());
    }

    // Important: Tests creation date. Works unless you run the code at the tick of midnight.
    @Test
    public void testCreateDate() {
        assertTrue(testHabit.getDateOfCreation().equals(testDate));
    }

    @Test
    public void testDays() {

        assertFalse(testHabit.isWorkingDay(1));
        assertTrue(testHabit.isWorkingDay(2));
        assertTrue(testHabit.isWorkingDay(3));
        assertTrue(testHabit.isWorkingDay(4));
        assertTrue(testHabit.isWorkingDay(5));
        assertTrue(testHabit.isWorkingDay(6));
        assertFalse(testHabit.isWorkingDay(7));
    }

    // Test values off the bat of creation
    @Test
    public void testInitialCompletion() {
        Habit habit = new Habit(name, testDate, daysInAWeek);
        assertFalse(habit.getDailyCompletion());
        assertEquals(1, (long)habit.getDaysToComplete());
        assertEquals(0, (long)habit.getDaysComplete());
        assertEquals(0, (long)habit.getDaysMissed());
        assertEquals(0, (long)habit.getOverTimeDays());
    }

    // Test after completing a habit
    @Test
    public void testGoodDay() {
        Habit habit = new Habit(name, testDate, daysInAWeek);
        habit.completion();
        assertTrue(habit.getDailyCompletion());
        assertEquals(1, (long)habit.getDaysToComplete());
        assertEquals(1, (long)habit.getDaysComplete());
        assertEquals(0, (long)habit.getDaysMissed());
        assertEquals(0, (long)habit.getOverTimeDays());
    }

    // Test after failing to complete a habit
    @Test
    public void testBadDay() {
        Habit habit = new Habit(name, testDate, daysInAWeek);
        habit.newDayCheck();
        assertFalse(habit.getDailyCompletion());
        assertEquals(2, (long)habit.getDaysToComplete());
        assertEquals(0, (long)habit.getDaysComplete());
        assertEquals(1, (long)habit.getDaysMissed());
        assertEquals(0, (long)habit.getOverTimeDays());
    }

    // Test after completing a habit twice in one day.
    @Test
    public void testOverTime() {
        Habit habit = new Habit(name, testDate, daysInAWeek);
        habit.completion();
        habit.completion();
        habit.newDayCheck();
        assertFalse(habit.getDailyCompletion());
        assertEquals(2, (long)habit.getDaysToComplete());
        assertEquals(1, (long)habit.getDaysComplete());
        assertEquals(0, (long)habit.getDaysMissed());
        assertEquals(1, (long)habit.getOverTimeDays());
    }


}
