package com.example.lizzy.languageapp_10;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

public class LevelSelect extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_select);

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.levelslayout);
        RelativeLayout.LayoutParams params;
        LevelButton lb;
        int previousId = 0;
        for (int i = 0; i <= Settings.TOTAL_LEVELS; i++){
            lb = new LevelButton(this, i);
            lb.setId((i + 1));
            lb.setHeight((int) getResources().getDimension(R.dimen.level_button_dimen));
            lb.setWidth((int) getResources().getDimension(R.dimen.level_button_dimen));
            lb.setBackgroundColor(Color.TRANSPARENT);


            params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            if (i == 0){
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            } else {
                params.addRule(RelativeLayout.BELOW, previousId);
            }
            if( i % 2 == 1){
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params.addRule(RelativeLayout.ALIGN_PARENT_END);
            } else {
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                params.addRule(RelativeLayout.ALIGN_PARENT_START);
            }
            previousId = lb.getId();
            lb.setLayoutParams(params);
            rl.addView(lb);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.level_select, menu);
        return true;
    }

    public void openHelp(View v){
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }

    public void openAbout(View v){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    public void openBadges(View v){
        Intent intent = new Intent(this, BadgeActivity.class);
        startActivity(intent);
    }

    public void openDictionary(View v){
        Intent intent = new Intent(this, DictionaryAllActivity.class);
        startActivity(intent);
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


