package com.example.lizzy.languageapp_10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.lizzy.languageapp_10.R;

public class LevelInfo extends Activity {

    int levelLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_info);
        levelLevel = getIntent().getIntExtra("selectedLevel", 12);
        ((TextView) findViewById(R.id.levelWelcome)).setText("Welcome to level " + levelLevel);
    }

    public void completeLevel(View v){
        if (levelLevel == Settings.currentLevel){
            Settings.currentLevel += 1;
        }
        DB db = new DB(this);
        switch (levelLevel){
            case 1 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("l1")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                        }
                    }
                }
                break;
            default:
                break;
        }
        db.close();
        finish();
    }

    public void startGame(View v){
        Intent intent = new Intent(this, GamePlayTestActivity.class);
        intent.putExtra("selectedLevel", levelLevel);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.level_info, menu);
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
}
