package com.example.keith.kgmills_habittracker;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by kgmills
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

    /**
     * This habit should only be active Mon-Fri
     */
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

    /**
     * Complete
     * See that there is one entry
     * See that the entry can be removed
     * Trigger two more checks
     * Make sure there's one entry but it's empty.
     */
    @Test
    public void testCompletion() {
        testHabit.newDayCheck();
        testHabit.completion();
        HabitLog h = testHabit.getMyLog();
        Integer[] a = h.getCumulativeInfoInInts();
        assertEquals(1, (long)a[0]);
        assertEquals(1, (long)a[1]);
        assertEquals(1, (long)a[2]);
        assertEquals(0, (long)h.removeCompletion(dateHandler.logDate()));
        testHabit.newDayCheck();
        testHabit.newDayCheck();
        a = h.getCumulativeInfoInInts();
        assertEquals(1, (long)a[0]);
        assertEquals(0, (long)a[1]);
        assertEquals(0, (long)a[2]);
        assertEquals(4, (long)h.removeCompletion(dateHandler.logDate()));
    }
}
