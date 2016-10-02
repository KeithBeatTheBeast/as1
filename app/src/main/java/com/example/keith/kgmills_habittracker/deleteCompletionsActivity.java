package com.example.keith.kgmills_habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class deleteCompletionsActivity extends AppCompatActivity {

    private TextView.BufferType forAllETexts = TextView.BufferType.EDITABLE;
    private droidMVC MVC;
    private TextView nameOfHabit;
    private TextView createDate;
    private EditText delY;
    private EditText delM;
    private EditText delD;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_completions);

        this.nameOfHabit = (TextView) findViewById(R.id.nameOfHabit);
        this.createDate = (TextView) findViewById(R.id.createDate);
        this.delY = (EditText) findViewById(R.id.delYearEditText);
        this.delM = (EditText) findViewById(R.id.delMonthEditText);
        this.delD = (EditText) findViewById(R.id.delDayEditText);
        this.confirm = (Button) findViewById(R.id.deleteCompleteButton);
        this.MVC = droidMVC.getInstance();

        String[] textFiller = this.MVC.habitInfo();
        this.nameOfHabit.setText(textFiller[0]);
        this.createDate.setText(textFiller[1]);
    }

    public void removeACompletion(View view) {
        Integer whatToSay = MVC.removal(this.delY.getText().toString(),
                this.delM.getText().toString(),
                this.delD.getText().toString(),
                getApplicationContext());

        if (whatToSay.equals(0)) {
            Toast.makeText(getApplicationContext(), "A completion has been deleted", Toast.LENGTH_SHORT).show();
        }

        else if (whatToSay.equals(1)) {
            Toast.makeText(getApplicationContext(), "Date format: YYYY-MM-DD", Toast.LENGTH_SHORT).show();
        }

        else if (whatToSay.equals(2)) {
            Toast.makeText(getApplicationContext(), "Date fields can only contain numbers", Toast.LENGTH_SHORT).show();
        }

        else if (whatToSay.equals(3)) {
            Toast.makeText(getApplicationContext(), "Date not found in habit's log", Toast.LENGTH_SHORT).show();
        }

        else if (whatToSay.equals(4)) {
            Toast.makeText(getApplicationContext(), "Date already at 0 completions", Toast.LENGTH_SHORT).show();
        }
    }
}
