package com.example.keith.kgmills_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/** To do list according to notes:
    For the random strings held here and there, a string holder class analogous to strings.xml
    Strings.xml, do it
    Cannot add multiples of the same habit by name.
    When new habit added, clear habit stream
    Toast when no date selected.
 Be sure to make sure the habits are refreshed day by day.
 */
public class habitsViewActivity extends AppCompatActivity {

    private ListView activeHabitsListView;
    private ArrayAdapter<Habit> adapter;
    private droidMVC MVC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits_view);
        activeHabitsListView = (ListView) findViewById(R.id.activeHabitsListView);
        MVC = droidMVC.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.adapter = MVC.setAdapter(this, getApplicationContext());
        activeHabitsListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // http://stackoverflow.com/questions/21295328/android-listview-with-onclick-items
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

    public void addNewHabitIntent(View view) {
        Intent newHabitIntent = new Intent(this, newHabitActivity.class);
        startActivity(newHabitIntent);
    }

    public void deleteHabitIntent(View view) {
        Intent deleteHabitIntent = new Intent(this, deleteHabitActivity.class);
        startActivity(deleteHabitIntent);
    }


}
