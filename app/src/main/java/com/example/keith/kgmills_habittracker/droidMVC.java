package com.example.keith.kgmills_habittracker;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
/**
 * Created by kgmills
 * Last Edit: Oct 2 2016
 * Android interface
 * NOTE: You will see me attach an @param Context context
 * in many different functions. This is because the saveFromFile
 * and loadFromFile functions do not work without some sort of context.
 * ORIGINALLY I had planned to just have the main activity (habitsViewActivity, as
 * it only goes down perminently when the app closes)
 * give the MVC it's Context in onStart() or onResume() and have it be stored
 * as a private variable. Android studio, while not throwing compile errors said
 * that this was a bad idea, causing memory leaks. Therefore, I settled for having the
 * activity calling a specific function that would trigger a save/load to give it's
 * context.
 * Unfortunetely, this limits the scope of potential JUnit testing. Confirm
 * correctness by running on an emulator.
 */

public class droidMVC <T extends AppCompatActivity> {

    /**
     * This is a singleton class with singleton methods.
     * It has a HabitController and an index for a
     * habit to either be deleted or have it's entries deleted
     */
    private static droidMVC ourInstance = new droidMVC();
    private static final String HABITFILE = "habitFile.sav";
    private HabitController hCtl;
    private int index = -1;
    private droidMVC() {}
    public static droidMVC getInstance() {
        return ourInstance;
    }

    /**
     * Attached adapters to respective lists.
     * Uses reflection to discern between main and delete activities
     * @param activity The activity with an adapter
     * @param context the getApplicationContext() of said activity.
     * @return a linked ArrayAdapter
     */
    public ArrayAdapter<Habit> setAdapter(T activity, Context context) {
        loadFromFile(context);
        if (activity.getClass().equals(deleteHabitActivity.class)) {
            return new ArrayAdapter<>(activity, R.layout.delete_item, hCtl.getAllHabits());
        }
        return new ArrayAdapter<>(activity, R.layout.list_item, hCtl.getActiveHabits());
    }

    /**
     * On click in GUI -> Habit registers a completion and save
     * @param pos position in arrayadapter
     * @param context context of activity
     */
    public void mainCompleteClick(int pos, Context context) {
        hCtl.getActiveHabits().get(pos).completion();
        saveInFile(context);
    }

    /**
     * Gets info about said Habit
     * @param pos Position in ArrayAdapter
     * @return String to be used in a toast.
     */
    public String mainLongClick(int pos) {
        this.index = pos;
        return hCtl.getActiveHabits().get(pos).sendCompletionInfo();
    }

    /**
     * On delete page, this asks if one really wants to delete a habit.
     * Loads it's name up for a confirmation click that unlocks the delete button
     * @param i Position in ArrayAdapter
     * @return String to be posted in a Clickbox.
     */
    public String askForConfirmation(int i) {
        this.index = i;
        return "Really delete " + hCtl.getAllHabits().get(i).getName() + "?";
    }

    /**
     * After GUI presses delete button, delete the Habit
     * @param context Needed because we save after.
     */
    public void delSelectedHabit(Context context) {
        hCtl.removeAHabit(hCtl.getAllHabits().get(this.index));
        saveInFile(context);
    }

    /**
     * Called by GUI to create new function
     * @param name Name of new habit
     * @param date Date of it's creation
     * @param daysOfWeek A 7-size boolean vector corresponding the the active days of week
     * @param context Needed for save
     * @return 0 -> Nothing bad happened
     *         1 -> Date parameters wrong lengths
     *         2 -> Date parameters do not just contain numbers
     */
    public Integer createNewHabit(String name, String[] date, Boolean[] daysOfWeek, Context context) {
        Integer testDate = dateHandler.parseDate(date);
        if (testDate > 0) {
            return testDate;
        }

        Habit newHabit = new Habit(name,
                dateHandler.reformatDate(date),
                daysOfWeek);
        hCtl.addAHabit(newHabit);
        saveInFile(context);
        return 0;
    }

    /**
     * Used by completion deletion activity
     * Asks for info about Habit... sloppy info
     * @return String array containing name and date of creation.
     */
    public String[] habitInfo() {
        Habit toBeEdited = hCtl.getAllHabits().get(this.index);

        return new String[] {toBeEdited.getName(),
                toBeEdited.getDateOfCreation()};
    }

    /**
     * Removes a completion from an activity
     * @param year Year from GUI - as a number
     * @param month Month from GUI - as a number
     * @param day Day from GUI - as a number
     * @param context Needed for save.
     * @return 0 -> All is well
     *         1 -> Date parameters wrong length
     *         2 -> Date parameters do not just contain numbers
     *         3 -> Date not found in habit
     *         4 -> Date exits in habit, but is already set for zero completions
     */
    public Integer removal(String year, String month, String day, Context context) {
        loadFromFile(context);
        Integer whatToSay = dateHandler.parseDate(new String[]{year, month, day});
        if (!dateHandler.parseDate(new String[]{year, month, day}).equals(0)) { return whatToSay; }

        String dateToRemove = year + month + day;
        whatToSay = hCtl.getAllHabits().get(this.index).getMyLog().removeCompletion(dateToRemove);
        saveInFile(context);
        return whatToSay;
    }

    /**
     * Load from file using GSON. Adapted from 301 Lab involving lonelyTwitter.
     * @param context Needed
     */
    private void loadFromFile(Context context) {
        try {
            FileInputStream fis = context.getApplicationContext().openFileInput(HABITFILE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type listType = new TypeToken<HabitController>() {
            }.getType();

            hCtl = gson.fromJson(in, listType);
            hCtl.checkDayOfWeek();
        } catch (FileNotFoundException e) {
            hCtl = new HabitController();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Save to file using GSON. Adapted from 301 Lab involving lonelyTwitter.
     * @param context Needed
     */
    private void saveInFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(HABITFILE, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(hCtl, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

