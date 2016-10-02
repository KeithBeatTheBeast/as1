package com.example.keith.kgmills_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * Created by Kgmills
 * Main activity of Kgmills-HabitTracker
 * Shows active habits for the day, provides ability to
 * complete them, see their status, and branch to either
 * create new habits, or delete habits/their completions
 */
public class habitsViewActivity extends AppCompatActivity {

    private ListView activeHabitsListView;
    private ArrayAdapter<Habit> adapter;
    private droidMVC MVC; // Singleton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits_view);
        activeHabitsListView = (ListView) findViewById(R.id.activeHabitsListView);
        MVC = droidMVC.getInstance();
    }

    /**
     * I had to fiddle around with the adapter a bit to get it to work properly
     * We set it everytime and notify of changes.
     * We have an onClickListener for completing habits
     * and an onLongClickListener for getting their statuses.
     */
    @Override
    protected void onResume() {
        super.onResume();
        this.adapter = MVC.setAdapter(this, getApplicationContext());
        activeHabitsListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        activeHabitsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>adapt, View v, int position, long l) {
                MVC.mainCompleteClick(position, getApplicationContext());
                adapter.notifyDataSetChanged();
            }
        });

        activeHabitsListView.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?>adapt, View v, int position, long l) {
                Toast.makeText(getApplicationContext(), MVC.mainLongClick(position), Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    // Branch to create new habits
    public void addNewHabitIntent(View view) {
        Intent newHabitIntent = new Intent(this, newHabitActivity.class);
        startActivity(newHabitIntent);
    }

    // Branch to delete habits/their completions
    public void deleteHabitIntent(View view) {
        Intent deleteHabitIntent = new Intent(this, deleteHabitActivity.class);
        startActivity(deleteHabitIntent);
    }


}
