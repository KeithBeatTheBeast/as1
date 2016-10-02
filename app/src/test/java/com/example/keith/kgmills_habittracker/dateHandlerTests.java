package com.example.keith.kgmills_habittracker;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by kgmills
 * JUNIT Tests for dateHander static class
 */

public class dateHandlerTests {

    @Test
    public void testParseFunction() {
        String[] validString = {"1995", "01", "25"};
        String[] inValidString1 = {"1995", "01"};
        String[] inValidString2 = {"", "", ""};
        String[] inValidString3 = {"5.75", "02", "25"};

        assertEquals(Long.valueOf(0),
                Long.valueOf(dateHandler.parseDate(validString)));
        assertEquals(Long.valueOf(1),
                Long.valueOf(dateHandler.parseDate(inValidString1)));
        assertEquals(Long.valueOf(1),
                Long.valueOf(dateHandler.parseDate(inValidString2)));
        assertEquals(Long.valueOf(2),
                Long.valueOf(dateHandler.parseDate(inValidString3)));
    }

    @Test
    public void testCurrentDate() {
        // Check it by looking at the console and comparing to computer clock.
        System.out.println(dateHandler.getCurrentDate());
    }

    @Test
    public void testReformat() {
        String[] testStringArray = {"1995", "01", "25"};
        String testString = dateHandler.reformatDate(testStringArray);
        String[] testStringArray2 = testString.split("-");
        for (int i = 0; i < testStringArray.length; i++) {
            assertTrue(testStringArray[i].equals(testStringArray2[i]));
        }
    }
}
