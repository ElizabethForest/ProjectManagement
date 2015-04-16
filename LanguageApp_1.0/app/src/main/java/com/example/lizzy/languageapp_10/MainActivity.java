package com.example.lizzy.languageapp_10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO database stuff
        Settings.setUpBadges();
        if (Settings.name == null){
            setContentView(R.layout.enter_name_age);
        } else {
            setContentView(R.layout.activity_welcome);
            ((TextView) findViewById(R.id.welcome)).setText("Welcome " + Settings.name);
        }


    }

    public void setAgeName(View v){
        TextView nameText = (TextView) findViewById(R.id.enterName);
        TextView ageText = (TextView) findViewById(R.id.enterAge);
        if (nameText.getText().toString().trim().equals("") || ageText.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Please enter details before continuing.", Toast.LENGTH_SHORT).show();
            return;
        }
        Settings.name = nameText.getText().toString();
        Settings.age = Integer.parseInt(ageText.getText().toString());
        setContentView(R.layout.inital_welcome);
        ((TextView) findViewById(R.id.welcome)).setText("Welcome " + Settings.name);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openLevelSelect(View v){
        Intent intent = new Intent(this, LevelSelect.class);
        startActivity(intent);
    }

    public void openDictionary(View v){
        Intent intent = new Intent(this, DictionaryAllActivity.class);
        startActivity(intent);
    }
}
