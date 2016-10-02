package com.example.keith.kgmills_habittracker;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by kgmills
 * Last Edit: Oct 2 2016
 * JUnit tests for HabitLog
 */

public class habitLogTests {

    /**
     * Create Log
     * See that it's empty
     * write to it once
     * See that the entry value
     * (string version passed to present info about habit)
     * has changed.
     */
    @Test
    public void testWrite() {
        HabitLog h = new HabitLog();
        Integer[] a = h.getCumulativeInfoInInts();
        assertEquals(0, (long)a[0]);
        assertEquals(0, (long)a[1]);
        assertEquals(0, (long)a[2]);

        h.writeToLog("20161001", 1);
        a = h.getCumulativeInfoInInts();
        assertEquals(1, (long)a[0]);
        assertEquals(1, (long)a[1]);
        assertEquals(1, (long)a[2]);
    }

    /**
     * Create log
     * Write to log
     * See that it's updated
     * Delete that entry
     * See that it's updated.
     */
    @Test
    public void testRemove() {
        HabitLog h = new HabitLog();
        h.writeToLog("20161001", 1);
        Integer[] a = h.getCumulativeInfoInInts();
        assertEquals(1, (long)a[0]);
        assertEquals(1, (long)a[1]);
        assertEquals(1, (long)a[2]);
        assertEquals(0, (long)h.removeCompletion("20161001"));
        a = h.getCumulativeInfoInInts();
        assertEquals(1, (long)a[0]);
        assertEquals(0, (long)a[1]);
        assertEquals(0, (long)a[2]);
    }

}
