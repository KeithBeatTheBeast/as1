package com.example.keith.kgmills_habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by Kgmills
 * Activity called by habitsViewActivity
 * responsible for GUI interface that relays information about
 * new habits to the MVC.
 */
public class newHabitActivity extends AppCompatActivity {

    /**
     * A new date, the MVC, a variable that makes toasts easier,
     * Texts for the yyyy-MM-dd, name of new habit, and the 7
     * day switches
     */
    private String[] newHabitDate;
    private droidMVC MVC;
    private TextView.BufferType forAllETexts = TextView.BufferType.EDITABLE;
    private EditText yearText, monthText, dayText;
    private EditText newHabitName;
    private Integer[] daysInAWeek = {R.id.sundaySwitch,
            R.id.mondaySwitch,
            R.id.tuesdaySwitch,
            R.id.wednesdaySwitch,
            R.id.thursdaysSwitch,
            R.id.fridaySwitch,
            R.id.saturdaySwitch};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);

        this.yearText = (EditText) findViewById(R.id.yearEditText);
        this.monthText = (EditText) findViewById(R.id.monthEditText);
        this.dayText = (EditText) findViewById(R.id.dayEditText);

        this.newHabitName = (EditText) findViewById(R.id.newHabitName);

        MVC = droidMVC.getInstance();

        // Sets everything up.
        resetFields();
    }

    /**
     * On click for "create new habit" button
     * Does all the "grab information from GUI" stuff necessary
     * and gives it to the MVC.
     * DID not have the days of the week parsing in MVC because it doesn't handle
     * Checkboxes cleanly.
     * @param view Required
     */
    public void newHabitClick(View view) {

        // What days of the week will the habit operate?
        Boolean[] daysOfWeek = {false, false, false, false, false, false, false};
        for (int i = 0; i < 7; i++) {
            CheckBox cBox = (CheckBox) findViewById(daysInAWeek[i]);
            daysOfWeek[i] = cBox.isChecked();
        }

        // If no boxes checked throw a toast.
        if (!Arrays.asList(daysOfWeek).contains(true)) {
            sendToast("You must select at least one weekday.");
        }

        this.newHabitDate[0] = this.yearText.getText().toString();
        this.newHabitDate[1] = this.monthText.getText().toString();
        this.newHabitDate[2] = this.dayText.getText().toString();
        Integer check = MVC.createNewHabit(this.newHabitName.getText().toString(),
                this.newHabitDate,
                daysOfWeek,
                getApplicationContext());
        if (check.equals(1)) {
            sendToast("Date format: YYYY-MM-DD");
        }

        else if (check.equals(2)) {
            sendToast("Date fields can only contain numbers");
        }

        // Resets fields of things went well.
        else {
            resetFields();
        }
    }

    /**
     * Makes the process of firing off a toast easier.
     * @param t String to be toasted
     */
    public void sendToast(String t) {
        Toast.makeText(getApplicationContext(), t, Toast.LENGTH_SHORT).show();
    }

    /**
     * Sets all checkboxes to false
     * CLear fields
     */
    public void resetFields() {
        for (int i = 0; i < 7; i++) {
            ((CheckBox) findViewById(daysInAWeek[i])).setChecked(false);
        }
        this.newHabitDate = dateHandler.getCurrentDate();
        this.yearText.setText(newHabitDate[0], forAllETexts);
        this.monthText.setText(newHabitDate[1], forAllETexts);
        this.dayText.setText(newHabitDate[2], forAllETexts);
        this.newHabitName.setText(null);
    }
}
