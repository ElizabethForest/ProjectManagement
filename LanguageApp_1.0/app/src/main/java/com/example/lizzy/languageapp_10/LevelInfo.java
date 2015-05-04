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
        DB db = new DB(this);
        if (levelLevel == Settings.currentLevel){
            Settings.currentLevel += 1;
            if (db.open()){
                db.setLevel(Settings.currentLevel);
            }

        }
        switch (levelLevel){
            case 1 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("l1")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 2 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("w10")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 4 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("w20")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 5 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("l5")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 7 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("w30")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 9 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("w40")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 10 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("l10")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 12 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("w50")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 13 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("w60")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 14 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("w70")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 15 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("l15")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 16 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("w80")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 17 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("w90")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 19 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("w100")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 20 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("l20")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 25 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("l25")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 26 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("w150")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 30 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("l30")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 32 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("w250")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
                        }
                    }
                }
                break;
            case 35 :
                for (Badge badge : Settings.badges){
                    if (badge.getId().equals("l35")){
                        if(db.open()){
                            badge.setAchieved(true, db);
                            break;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
