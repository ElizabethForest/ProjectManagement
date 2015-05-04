package com.example.lizzy.languageapp_10;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DictionaryAllActivity extends Activity {
    String[] alphabet = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    String[] categories = new String[]{""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_all);
        fillDictionaryByletter();
        //fillDictionaryBylevel();
        TextView text = (TextView)findViewById(R.id.text);
        text.setText("Dictionary");
        TextView text2 = (TextView)findViewById(R.id.text2);
        text2.setText("English Word - Gugu Badhun Word");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void sortByLevel(View v){
        fillDictionaryBylevel();
    }

    public void sortByletter(View v){
        fillDictionaryByletter();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void fillDictionaryByletter() {

        DB db = new DB(this);
        //ArrayList<ArrayList<Word>> words = new ArrayList<ArrayList<Word>>();

        LinearLayout layout = (LinearLayout) findViewById(R.id.ll);
        layout.removeAllViews();
        TextView letter;

        if ( db.open() ) {
            Log.d("myTag", "Database is open  equals true");
            for (int i = 0; i < alphabet.length; i++) {
                final ArrayList<Word> temparray = db.getWordsByLetter(alphabet[i]);
                Log.w("tt", (temparray == null ? "null" : "not"));
                //words.add(temparray);
                letter = new TextView(this, null, R.style.AppTheme_creamtext);

                letter.setText((alphabet[i]).toUpperCase());
                layout.addView(letter);

                try {
                    if (temparray != null && temparray.get(0) != null) {
                        createWords(temparray, layout);
                    }
                } catch (Exception e) {
                    Log.w("dict", "Was exception");
                    e.printStackTrace();
                }
            }
        }

        db.close();
    }

    public void fillDictionaryBylevel(){
        DB db = new DB(this);
        //ArrayList<ArrayList<Word>> words = new ArrayList<ArrayList<Word>>();

        LinearLayout layout = (LinearLayout) findViewById(R.id.ll);
        layout.removeAllViews();

        TextView level;

        Log.w("DB", "filling by level");

        if ( db.open() ) {
            Log.d("myTag", "Database is open  equals true");
            for (int i = 1; i < Settings.TOTAL_LEVELS; i++) {
                final ArrayList<Word> temparray = db.getWordsByLevel(i);
                Log.w("tt", (temparray == null ? "null" : "not"));
                //words.add(temparray);
                level = new TextView(this, null, R.style.AppTheme_creamtext);

                level.setText("Level " + i);
                layout.addView(level);

                try {
                    if (temparray != null && temparray.get(0) != null) {
                        createWords(temparray, layout);
                    }
                } catch (Exception e) {
                    Log.w("dict", "Was exception");
                    e.printStackTrace();
                }
            }
        }

        db.close();
    }

    public void createWords(ArrayList<Word> wordList, LinearLayout layout){

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0.45f);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 0.1f);


        TextView dash;
        TextView english;
        TextView gugu;

        for (final Word word : wordList) {
            final LinearLayout horizontal = (LinearLayout) getLayoutInflater().inflate(R.layout.template, null);
            horizontal.setOrientation(LinearLayout.HORIZONTAL);

            dash = new TextView(this);
            english = new TextView(this);
            gugu = new TextView(this);

            english.setTextSize(20);
            dash.setTextSize(20);
            gugu.setTextSize(20);

            english.setTextColor(Color.rgb(119, 106, 53));
            dash.setTextColor(Color.rgb(119, 106, 53));
            gugu.setTextColor(Color.rgb(119, 106, 53));

            dash.setLayoutParams(params2);
            english.setLayoutParams(params);
            gugu.setLayoutParams(params);

            dash.setText("-");

            english.setText(word.englishWord);
            gugu.setText(word.guguBadhunWord);

            horizontal.addView(english);
            horizontal.addView(dash);
            horizontal.addView(gugu);

            horizontal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    horizontal.setBackgroundResource(R.drawable.blue_textbox);
                    Intent intent = new Intent(getApplicationContext(), WordActivity.class);
                    intent.putExtra("word", word.englishWord);
                    startActivity(intent);

                    new CountDownTimer(2000, 50) {

                        @Override
                        public void onTick(long arg0) {
                            // TODO Auto-generated method stub

                        }
                        @Override
                        public void onFinish() {
                            horizontal.setBackgroundResource(R.drawable.cream_textbox);
                        }
                    }.start();


                }
            });

            layout.addView(horizontal);
        }
    }

}
