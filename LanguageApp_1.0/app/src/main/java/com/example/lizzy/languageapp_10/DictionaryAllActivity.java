package com.example.lizzy.languageapp_10;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.DB;
import database.Word;


public class DictionaryAllActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_all);
        testPreLoadedSQLiteDb();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dictionary_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void testPreLoadedSQLiteDb() {

        DB db = new DB(this);

        // copy assets DB to app DB.
        try {
            db.create();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }


        // get all locations
        if ( db.open() ) {
            Log.d("myTag", "Database is open  equals true");
            List<Word> words = db.getWords();


            //gets the first word and assigns it to a textview
            // for testing purpose
            //String englishword1 = words.get(0).englishWord;
            //String guguBadhumword1 = words.get(0).guguBadhunWord;
            TextView text = (TextView)findViewById(R.id.text);
            text.setText("Test Dictionary");
            TextView text2 = (TextView)findViewById(R.id.text2);
            text2.setText("English Word - Gugu Badhun Word");

            // don't forget to close the database after use!!
            db.close();
            //iterate through the words list to make a new list
            //with only the gugubadun and english word
            ArrayList<String> englishguguList = new ArrayList<String>();
            for (Word worddetails : words) {
                String english = worddetails.englishWord;
                String gugu = worddetails.guguBadhunWord;
                String listItem  = english + "  -  " + gugu;
                Log.d("myTag", listItem);
                englishguguList.add(listItem);

            }



            //testing a listview
            ListView listView = (ListView) findViewById(R.id.listView);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, englishguguList);

            // Assign adapter to ListView
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });



        } else {
            // error opening DB.
        }
    }

    public void listViewTest() {

        //used for testing the listview //add another listview or rename
        //as the listview is used above, before calling the method
        ListView listView = (ListView) findViewById(R.id.listView);
        // Defined Array values to show in ListView
        String[] values = new String[] { "item1",
                "item2",
                "item3"
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

    }

}
