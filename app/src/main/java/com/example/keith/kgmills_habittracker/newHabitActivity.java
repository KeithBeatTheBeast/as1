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

        this.newHabitDate = dateHandler.getCurrentDate();
        yearText.setText(newHabitDate[0], forAllETexts);
        monthText.setText(newHabitDate[1], forAllETexts);
        dayText.setText(newHabitDate[2], forAllETexts);

        this.newHabitName = (EditText) findViewById(R.id.newHabitName);

        MVC = droidMVC.getInstance();

    }

    // Do not remove this function.
    // Have it call the droidMVC
    public void newHabitClick(View view) {

        Boolean[] daysOfWeek = {false, false, false, false, false, false, false};
        for (int i = 0; i < 7; i++) {
            CheckBox cBox = (CheckBox) findViewById(daysInAWeek[i]);
            daysOfWeek[i] = cBox.isChecked();
        }

        Integer check = MVC.createNewHabit(this.newHabitName.getText().toString(),
                this.newHabitDate,
                daysOfWeek,
                getApplicationContext());
        if (check == 1) {
            // THROWING TOASTS BECAUSE HINDLE DID SO IN STUDENT PICKA!
            Toast.makeText(getApplicationContext(),
                    "Date format: YYYY-MM-DD", Toast.LENGTH_SHORT).show();
        }

        else if (check == 2) {
            Toast.makeText(getApplicationContext(),
                    "Date fields can only contain numbers", Toast.LENGTH_SHORT).show();
        }

        else {

        }
    }
}
