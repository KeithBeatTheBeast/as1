package com.example.keith.kgmills_habittracker;

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

    private ListView deleteHabitsListView;
    private HabitController hCtl = new HabitController();
    private TextView.BufferType forAllETexts = TextView.BufferType.EDITABLE;
    private ArrayAdapter<Habit> adapter;
    private CheckBox deleteConfirm;
    private Button commitDeleteButton;
    private TextView deleteText;
    private int indexToDelete = -1;

    // REMOVE LATER ONLY FOR TESTING/DESIGNING
    private static final Boolean[] daysInAWeek = {false, true, true, true, true, true, false};
    private String name = "aNewName";
    private static final String testDate = "1995-01-25";
    private Habit testHabit = new Habit(name, testDate, daysInAWeek);
    private Habit anotherTestHabit = new Habit("Another", testDate, daysInAWeek);

    // Maybe implement on click listener for Clickbox
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_habit);

        // http://stackoverflow.com/questions/14802354/how-to-reset-a-buttons-background-color-to-default for button color changes
        deleteHabitsListView = (ListView) findViewById(R.id.deleteHabitsListView);
        deleteConfirm = (CheckBox) findViewById(R.id.deleteConfirmationCheck);
        commitDeleteButton = (Button) findViewById(R.id.finalDeleteButton);
        deleteText = (TextView) findViewById(R.id.deleteText);
        deleteText.setText("Select a habit to delete", forAllETexts);
        hCtl.addAHabit(testHabit);
        hCtl.addAHabit(anotherTestHabit);
        adapter = new ArrayAdapter<Habit>(this, R.layout.delete_item, hCtl.getAllHabits());
        deleteHabitsListView.setAdapter(adapter);
        commitDeleteButton.setClickable(false);
        commitDeleteButton.setBackgroundColor(0x00000000);
        deleteConfirm.setEnabled(false);


        deleteHabitsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?>adapt, View v, int position, long l) {
                String rDelete = "Really delete " + hCtl.getAllHabits().get(position).getName() + "?";
                deleteText.setText(rDelete, forAllETexts);
                indexToDelete = position;
                deleteConfirm.setEnabled(true);
            }
        });

        deleteConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (deleteConfirm.isChecked() && indexToDelete > -1) {
                    commitDeleteButton.setClickable(true);
                    commitDeleteButton.setBackgroundResource(android.R.drawable.btn_default);
                }

                else {
                    commitDeleteButton.setBackgroundColor(0x00000000);
                    commitDeleteButton.setClickable(false);
                }
            }

        });
    }

    public void deleteHabit(View view) {
        if (deleteConfirm.isChecked()) {
            hCtl.getAllHabits().remove(this.indexToDelete);
            adapter.notifyDataSetChanged();
            commitDeleteButton.setBackgroundColor(0x00000000);
            commitDeleteButton.setClickable(false);
            deleteConfirm.setChecked(false);
            indexToDelete = -1;
            deleteText.setText("Select a habit to delete", forAllETexts);
            deleteConfirm.setEnabled(false);
        }
    }

}

