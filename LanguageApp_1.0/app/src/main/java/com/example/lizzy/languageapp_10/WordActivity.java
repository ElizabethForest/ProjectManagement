package com.example.lizzy.languageapp_10;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lizzy.languageapp_10.R;

public class WordActivity extends Activity {

    Word word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        String englishWord = getIntent().getStringExtra("word");
        DB db = new DB(this);
        if(db.open()) {
            word = db.getWord(englishWord);
        }
        db.close();

        ((TextView) findViewById(R.id.english)).setText(word.englishWord);
        ((TextView) findViewById(R.id.gugu)).setText(word.guguBadhunWord);
    }

    public void back(View v){
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.word, menu);
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
