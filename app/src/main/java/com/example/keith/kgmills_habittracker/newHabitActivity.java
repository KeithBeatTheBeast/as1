package com.example.keith.kgmills_habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class newHabitActivity extends AppCompatActivity {

    private String[] newHabitDate;
    private TextView.BufferType forAllETexts = TextView.BufferType.EDITABLE;
    private EditText yearText, monthText, dayText;
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
        this.newHabitDate = dateHandler.getCurrentDate();
        // http://stackoverflow.com/questions/4590957/how-to-set-text-in-an-edittext
        yearText.setText(newHabitDate[0], forAllETexts);
        monthText.setText(newHabitDate[1], forAllETexts);
        dayText.setText(newHabitDate[2], forAllETexts);

    }

    public void newHabitClick(View view) {

        this.newHabitDate[0] = yearText.getText().toString();
        this.newHabitDate[1] = monthText.getText().toString();
        this.newHabitDate[2] = dayText.getText().toString();

        Integer testDate = dateHandler.parseDate(this.newHabitDate);
        if (testDate == 1) {
            // THROWING TOASTS BECAUSE HINDLE DID SO IN STUDENT PICKA!
            Toast.makeText(getApplicationContext(),
                    "Date format: YYYY-MM-DD", Toast.LENGTH_SHORT).show();
        }

        else if (testDate == 2) {
            Toast.makeText(getApplicationContext(),
                    "Date fields can only contain numbers", Toast.LENGTH_SHORT).show();
        }

        else {
            String stringNewHabit = findViewById(R.id.newHabitName).toString();
            // Probably best to make a checkbox controller for this.
            // Reconfigure Habit so that it takes an array of booleans.
            Boolean[] daysOfWeek = {false, false, false, false, false, false, false};
            for (int i = 0; i < 7; i++) {
                CheckBox cBox = (CheckBox) findViewById(daysInAWeek[i]);
                daysOfWeek[i] = cBox.isChecked();
            }
            Habit newHabit = new Habit(stringNewHabit,
                    dateHandler.reformatDate(this.newHabitDate),
                    daysOfWeek);

            Toast.makeText(getApplicationContext(), newHabit.sendCompletionInfo(), Toast.LENGTH_SHORT).show();
        }
    }
}
