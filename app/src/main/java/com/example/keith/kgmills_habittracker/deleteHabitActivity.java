package com.example.keith.kgmills_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;

public class deleteHabitActivity extends AppCompatActivity {

    private ListView delListView;
    private TextView.BufferType forAllETexts = TextView.BufferType.EDITABLE;
    private ArrayAdapter<Habit> adapter;
    private CheckBox delConfirm;
    private Button delCommit;
    private Button delCompletes;
    private TextView delText;
    private droidMVC MVC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_habit);
        MVC = droidMVC.getInstance();

        // http://stackoverflow.com/questions/14802354/how-to-reset-a-buttons-background-color-to-default for button color changes
        delConfirm = (CheckBox) findViewById(R.id.deleteConfirmationCheck);
        delConfirm.setEnabled(false);

        delCommit = (Button) findViewById(R.id.finalDeleteButton);
        delCommit.setClickable(false);
        delCommit.setBackgroundColor(0x00000000);

        delCompletes = (Button) findViewById(R.id.goToCompletes);
        delCompletes.setClickable(false);

        delText = (TextView) findViewById(R.id.deleteText);
        delText.setText("Select a habit to delete", forAllETexts);

        delListView = (ListView) findViewById(R.id.deleteHabitsListView);
        adapter = MVC.setAdapter(this, getApplicationContext());
        delListView.setAdapter(adapter);

        delListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapt, View v, int position, long l) {
                delText.setText(MVC.askForConfirmation(position), forAllETexts);
                delConfirm.setEnabled(true);
                delCompletes.setClickable(true);
            }
        });

        delConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (delConfirm.isChecked()) {
                    delCommit.setClickable(true);
                    delCommit.setBackgroundResource(android.R.drawable.btn_default);
                }

                else {
                    delCommit.setBackgroundColor(0x00000000);
                    delCommit.setClickable(false);
                }
            }

        });
    }

    public void deleteHabit(View view) {
       // if (delConfirm.isChecked()) {
        MVC.delSelectedHabit(getApplicationContext());
        adapter.notifyDataSetChanged();

        delCommit.setBackgroundColor(0x00000000);
        delCommit.setClickable(false);

        delConfirm.setChecked(false);
        delConfirm.setEnabled(false);

        delText.setText("Select a habit to delete", forAllETexts);

       // }
    }

    public void activitiesIntent(View view) {
        delCompletes.setClickable(false);
        Intent completions = new Intent(this, deleteCompletionsActivity.class);
        startActivity(completions);
    }

}

