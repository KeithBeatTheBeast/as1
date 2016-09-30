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
 */
public class habitsViewActivity extends AppCompatActivity {

    private static final String HABITFILE = "habitFile.sav";
    private ListView activeHabitsListView;
    private HabitController hCtl = new HabitController();
    private ArrayAdapter<Habit> adapter;

    // REMOVE LATER ONLY FOR TESTING/DESIGNING
    private static final Boolean[] daysInAWeek = {false, true, true, true, true, true, false};
    private String name = "aNewName";
    private static final String testDate = "1995-01-25";
    private Habit testHabit = new Habit(name, testDate, daysInAWeek);
    private Habit anotherTestHabit = new Habit("Another", testDate, daysInAWeek);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits_view);
        activeHabitsListView = (ListView) findViewById(R.id.activeHabitsListView);
        hCtl.addAHabit(testHabit);
        hCtl.addAHabit(anotherTestHabit);
        adapter = new ArrayAdapter<Habit>(this, R.layout.list_item, hCtl.getActiveHabits());
        activeHabitsListView.setAdapter(adapter);

        // http://stackoverflow.com/questions/21295328/android-listview-with-onclick-items
        activeHabitsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?>adapt, View v, int position, long l) {
                hCtl.getActiveHabits().get(position).completion();
                adapter.notifyDataSetChanged();
            }
        });

        activeHabitsListView.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?>adapt, View v, int position, long l) {
                String lol = hCtl.getActiveHabits().get(position).sendCompletionInfo();
                Toast.makeText(getApplicationContext(), lol, Toast.LENGTH_LONG).show();
                return true;
            }

        });

    }

    public void addNewHabitIntent(View view) {
        Intent newHabitIntent = new Intent(this, newHabitActivity.class);
        startActivity(newHabitIntent);
        // Probably should attach controller to this.
        // So as to avoid all the saving and what not.
        // Or maybe that's a bad idea.
    }

    public void deleteHabitIntent(View view) {
        Intent deleteHabitIntent = new Intent(this, deleteHabitActivity.class);
        startActivity(deleteHabitIntent);
    }


}
