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

/**
 * Created by Kgmills
 * Last Edit: Oct 2 2016
 * Activity for deleting entire habits
 * The user will select an existing habit (all of them,
 * not just the actives) from an ArrayAdapter.
 * They will then be asked if they really want to delete it.
 * At which point, they have two options:
 * 1) Hit another button to go to another activity (deleteCompletionsActivity)
 * to change their completion info
 * 2) Hit a "yes I do" clickbox, then another button to delete the habit.
 *
 * NOTE: For some reason the ArrayAdapter doesn't show the same information
 * as the arrayadapter in habitsViewActivity. I don't know why, since they both'
 * rely on the same habitController and MVC.
 */
public class deleteHabitActivity extends AppCompatActivity {

    private ListView delListView;
    private TextView.BufferType forAllETexts = TextView.BufferType.EDITABLE;
    private ArrayAdapter<Habit> adapter;
    private CheckBox delConfirm; // Assurance for deletion box.
    private Button delCommit; // Commits the deletion
    private Button delCompletes; // Button for going to delete completions page
    private TextView delText; // Text field above everything
    private droidMVC MVC;

    /**
     * When the activity starts up one cannot even press any of the switches or buttons
     * until a habit is selected. Once it is, one can go to it's delete completions page.
     * Or, they can check the "yes I want to delete it" switch (which previously was inactive)
     * which will then unlock the button that actually commits the deletion command to the MVC.
     * @param savedInstanceState
     */
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.adapter = MVC.setAdapter(this, getApplicationContext());
        delListView.setAdapter(adapter);

        // Listener for ArrayAdapter
        delListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapt, View v, int position, long l) {
                // Give the MVC the index of the Habit that will be deleted/modified
                // It will remember until you change that.
                delText.setText(MVC.askForConfirmation(position), forAllETexts);
                delConfirm.setEnabled(true);
                delCompletes.setClickable(true);
            }
        });

        // Listener for checkbox
        // Unlocks button that commits delete.
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

    // Commit delete button.
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

    // Go to delete a Habit's completions.
    public void activitiesIntent(View view) {
        delCompletes.setClickable(false);
        Intent completions = new Intent(this, deleteCompletionsActivity.class);
        startActivity(completions);
    }

}

