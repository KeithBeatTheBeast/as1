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
 * Created by keith
 * Singleton.
 */

public class droidMVC <T extends AppCompatActivity> {

    private static droidMVC ourInstance = new droidMVC();
    private static final String HABITFILE = "habitFile.sav";
    private HabitController hCtl;
    private int indexToDelete = -1;
    private droidMVC() {}
    public static droidMVC getInstance() {
        return ourInstance;
    }

    public ArrayAdapter<Habit> setAdapter(T activity, Context context) {
        loadFromFile(context);
        if (activity.getClass().equals(deleteHabitActivity.class)) {
            return new ArrayAdapter<>(activity, R.layout.delete_item, hCtl.getAllHabits());
        }
        return new ArrayAdapter<>(activity, R.layout.list_item, hCtl.getActiveHabits());
    }

    public void mainCompleteClick(int pos, Context context) {
        hCtl.getActiveHabits().get(pos).completion();
        saveInFile(context);
    }

    public String mainLongClick(int pos) {
        this.indexToDelete = pos;
        return hCtl.getActiveHabits().get(pos).sendCompletionInfo();
    }

    public String askForConfirmation(int i) {
        this.indexToDelete = i;
        return "Really delete " + hCtl.getAllHabits().get(i).getName() + "?";
    }

    public void delSelectedHabit(Context context) {
        hCtl.removeAHabit(hCtl.getAllHabits().get(this.indexToDelete));
        saveInFile(context);
    }

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


    private void loadFromFile(Context context) {
        try {
            FileInputStream fis = context.getApplicationContext().openFileInput(HABITFILE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // From StackOverFlow (GET ORIGINAL THREAD)
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

