package com.example.keith.kgmills_habittracker;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by kgmills
 * Last Edit: Oct 2 2016
 * JUNIT Tests for dateHander static class
 */

public class dateHandlerTests {

    /**
     * Test the parsing function
     * uses Equals because I needed multiple error codes
     */
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

    /**
     * Test reformat function. See if it can go back and forth.
     */
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
