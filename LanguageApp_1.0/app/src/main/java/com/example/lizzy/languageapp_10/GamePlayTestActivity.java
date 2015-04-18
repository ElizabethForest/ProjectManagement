package com.example.lizzy.languageapp_10;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class GamePlayTestActivity extends Activity {

    List<Word> words;

    // variables for getting words from the database
    String englishword1;
    String englishword2;
    String englishword3;
    String englishword4;
    String guguword1;
    String guguword2;
    String guguword3;
    String guguword4;
    String guguword5;

    // boolean variables to check whether a button has already been pressed previously
    Boolean button1Pressed = false;
    Boolean button2Pressed = false;
    Boolean button3Pressed = false;
    Boolean button4Pressed = false;
    Boolean button5Pressed = false;
    // User guess variable, to set the button text for the 'guess'
    String userGuess = "";

    // TextView declarations
    TextView textenglish1;
    TextView textenglish2;
    TextView textenglish3;
    TextView textenglish4;
    TextView feedbackText;

    // user guess buttons
    Button buttonUserGuess1;
    Button buttonUserGuess2;
    Button buttonUserGuess3;
    Button buttonUserGuess4;

    // variable for testing the user guesses against the word list
    String message = "";
    Integer totalCorrect = 0;
    Boolean wordsMatchPair = false;
    int levelLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_test);
        testSQLiteDbSelectedWordsOnly();
        assignValuesfromWordList();
        textSetter();
        levelLevel = getIntent().getIntExtra("selectedLevel", 0);

        // refer to the user guess buttons
        buttonUserGuess1 = buttonUserGuess1Listener();
        buttonUserGuess2 = buttonUserGuess2Listener();
        buttonUserGuess3 = buttonUserGuess3Listener();
        buttonUserGuess4 = buttonUserGuess4Listener();
        guguWordListButtonListeners();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void testSQLiteDbSelectedWordsOnly() {

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
            words = db.getSelectedWords();
            // don't forget to close the database after use!!
            db.close();

        } else {
            // error opening DB.
        }
    }

    public void assignValuesfromWordList(){
        englishword1 = words.get(0).englishWord;
        englishword2 = words.get(1).englishWord;
        englishword3 = words.get(2).englishWord;
        englishword4 = words.get(3).englishWord;
        guguword1 = words.get(0).guguBadhunWord;
        guguword2 = words.get(1).guguBadhunWord;
        guguword3 = words.get(2).guguBadhunWord;
        guguword4 = words.get(3).guguBadhunWord;
        guguword5 = words.get(4).guguBadhunWord;
    }


    public void textSetter(){

        textenglish1 = (TextView) findViewById(R.id.englishWord1);
        textenglish1.setText(englishword1);
        textenglish2 = (TextView) findViewById(R.id.englishWord2);
        textenglish2.setText(englishword2);
        textenglish3 = (TextView) findViewById(R.id.englishWord3);
        textenglish3.setText(englishword3);
        textenglish4 = (TextView) findViewById(R.id.englishWord4);
        textenglish4.setText(englishword4);

    }

    public void guguWordListButtonListeners(){
        final Button buttonGuguwordlist1 = (Button) findViewById(R.id.guguwordlist1);
        buttonGuguwordlist1.setText(guguword1);
        final Button buttonGuguwordlist2 = (Button) findViewById(R.id.guguwordlist2);
        buttonGuguwordlist2.setText(guguword2);
        final Button buttonGuguwordlist3 = (Button) findViewById(R.id.guguwordlist3);
        buttonGuguwordlist3.setText(guguword3);
        final Button buttonGuguwordlist4 = (Button) findViewById(R.id.guguwordlist4);
        buttonGuguwordlist4.setText(guguword4);
        final Button buttonGuguwordlist5 = (Button) findViewById(R.id.guguwordlist5);
        buttonGuguwordlist5.setText(guguword5);


        //button listener for word 1 in guguword list
        buttonGuguwordlist1.setOnClickListener(new View.OnClickListener() {
            //Boolean Button1Pressed = false;
            @Override
            public void onClick(View arg0) {
                buttonGuguwordlist1.setBackgroundColor(Color.MAGENTA);
                buttonGuguwordlist2.setBackgroundColor(Color.WHITE);
                buttonGuguwordlist3.setBackgroundColor(Color.WHITE);
                buttonGuguwordlist4.setBackgroundColor(Color.WHITE);
                buttonGuguwordlist5.setBackgroundColor(Color.WHITE);
                enableAllGuessButtons();
                //buttonGuguwordlist1.setPressed(true);
                userGuess = buttonGuguwordlist1.getText().toString();

                if (button1Pressed == true) {
                    buttonGuguwordlist1.setBackgroundColor(Color.WHITE);
                    disableAllGuessButtons();
                    button1Pressed = false;
                } else {
                    button1Pressed = true;
                }
            }
        });

        //button listener for word 2 in guguword list
        buttonGuguwordlist2.setOnClickListener(new View.OnClickListener() {
            //Boolean Button1Pressed = false;
            @Override
            public void onClick(View arg0) {
                buttonGuguwordlist1.setBackgroundColor(Color.WHITE);
                buttonGuguwordlist2.setBackgroundColor(Color.MAGENTA);
                buttonGuguwordlist3.setBackgroundColor(Color.WHITE);
                buttonGuguwordlist4.setBackgroundColor(Color.WHITE);
                buttonGuguwordlist5.setBackgroundColor(Color.WHITE);
                enableAllGuessButtons();
                //buttonGuguwordlist1.setPressed(true);
                userGuess = buttonGuguwordlist2.getText().toString();

                if (button2Pressed == true) {
                    buttonGuguwordlist2.setBackgroundColor(Color.WHITE);
                    disableAllGuessButtons();
                    button2Pressed = false;
                } else {
                    button2Pressed = true;
                }
            }
        });

        //button listener for word 3 in guguword list
        buttonGuguwordlist3.setOnClickListener(new View.OnClickListener() {
            //Boolean Button1Pressed = false;
            @Override
            public void onClick(View arg0) {
                buttonGuguwordlist1.setBackgroundColor(Color.WHITE);
                buttonGuguwordlist2.setBackgroundColor(Color.WHITE);
                buttonGuguwordlist3.setBackgroundColor(Color.MAGENTA);
                buttonGuguwordlist4.setBackgroundColor(Color.WHITE);
                buttonGuguwordlist5.setBackgroundColor(Color.WHITE);
                enableAllGuessButtons();
                //buttonGuguwordlist1.setPressed(true);
                userGuess = buttonGuguwordlist3.getText().toString();

                if (button3Pressed == true) {
                    buttonGuguwordlist3.setBackgroundColor(Color.WHITE);
                    disableAllGuessButtons();
                    button3Pressed = false;
                } else {
                    button3Pressed = true;
                }
            }
        });

        //button listener for word 4 in guguword list
        buttonGuguwordlist4.setOnClickListener(new View.OnClickListener() {
            //Boolean Button1Pressed = false;
            @Override
            public void onClick(View arg0) {
                buttonGuguwordlist1.setBackgroundColor(Color.WHITE);
                buttonGuguwordlist2.setBackgroundColor(Color.WHITE);
                buttonGuguwordlist3.setBackgroundColor(Color.WHITE);
                buttonGuguwordlist4.setBackgroundColor(Color.MAGENTA);
                buttonGuguwordlist5.setBackgroundColor(Color.WHITE);
                enableAllGuessButtons();
                //buttonGuguwordlist1.setPressed(true);
                userGuess = buttonGuguwordlist4.getText().toString();

                if (button4Pressed == true) {
                    buttonGuguwordlist4.setBackgroundColor(Color.WHITE);
                    disableAllGuessButtons();
                    button4Pressed = false;
                } else {
                    button4Pressed = true;
                }
            }
        });

        //button listener for word 5 in guguword list
        buttonGuguwordlist5.setOnClickListener(new View.OnClickListener() {
            //Boolean Button1Pressed = false;
            @Override
            public void onClick(View arg0) {
                buttonGuguwordlist1.setBackgroundColor(Color.WHITE);
                buttonGuguwordlist2.setBackgroundColor(Color.WHITE);
                buttonGuguwordlist3.setBackgroundColor(Color.WHITE);
                buttonGuguwordlist4.setBackgroundColor(Color.WHITE);
                buttonGuguwordlist5.setBackgroundColor(Color.MAGENTA);
                enableAllGuessButtons();
                //buttonGuguwordlist1.setPressed(true);
                userGuess = buttonGuguwordlist5.getText().toString();

                if (button5Pressed == true) {
                    buttonGuguwordlist5.setBackgroundColor(Color.WHITE);
                    disableAllGuessButtons();
                    button5Pressed = false;
                } else {
                    button5Pressed = true;
                }
            }
        });

    }

    public Button buttonUserGuess1Listener(){
        final Button buttonUserGuess1 = (Button) findViewById(R.id.GuguWord1);
        buttonUserGuess1.setEnabled(false);
        // button listener for guess button 1
        buttonUserGuess1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                buttonUserGuess1.setText(userGuess);
            }
        });

        return buttonUserGuess1;

    }

    public Button buttonUserGuess2Listener(){
        final Button buttonUserGuess2 = (Button) findViewById(R.id.GuguWord2);
        buttonUserGuess2.setEnabled(false);
        // button listener for guess button 2
        buttonUserGuess2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                buttonUserGuess2.setText(userGuess);
            }
        });


        return buttonUserGuess2;

    }

    public Button buttonUserGuess3Listener(){
        final Button buttonUserGuess3 = (Button) findViewById(R.id.GuguWord3);
        buttonUserGuess3.setEnabled(false);
        // button listener for guess button 3
        buttonUserGuess3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                buttonUserGuess3.setText(userGuess);
            }
        });

        return buttonUserGuess3;

    }


    public Button buttonUserGuess4Listener(){
        final Button buttonUserGuess4 = (Button) findViewById(R.id.GuguWord4);
        buttonUserGuess4.setEnabled(false);
        // button listener for guess button 4
        buttonUserGuess4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                buttonUserGuess4.setText(userGuess);
            }
        });

        return buttonUserGuess4;

    }

    public void enableAllGuessButtons(){
        buttonUserGuess1.setEnabled(true);
        buttonUserGuess2.setEnabled(true);
        buttonUserGuess3.setEnabled(true);
        buttonUserGuess4.setEnabled(true);
    }

    public void disableAllGuessButtons(){
        buttonUserGuess1.setEnabled(false);
        buttonUserGuess2.setEnabled(false);
        buttonUserGuess3.setEnabled(false);
        buttonUserGuess4.setEnabled(false);
    }


    public void checkUserGuesses(View v) {
        feedbackText = (TextView) findViewById(R.id.Feedback);
        String englishText;
        String guguText;
        // check first word association
        englishText = textenglish1.getText().toString();
        guguText = buttonUserGuess1.getText().toString();
        compareGuessToWordsList(englishText, guguText);
        englishText = textenglish2.getText().toString();
        guguText = buttonUserGuess2.getText().toString();
        compareGuessToWordsList(englishText, guguText);
        englishText = textenglish3.getText().toString();
        guguText = buttonUserGuess3.getText().toString();
        compareGuessToWordsList(englishText, guguText);
        englishText = textenglish4.getText().toString();
        guguText = buttonUserGuess4.getText().toString();
        compareGuessToWordsList(englishText, guguText);


        message += totalCorrect;
        feedbackText.setText(message);
    }

    public void compareGuessToWordsList(String english, String guguword) {
        if ((english == words.get(0).englishWord) & (guguword == words.get(0).guguBadhunWord)) {
            wordsMatchPair = true;
            message = "Correct";
            totalCorrect += 1;
        }
        if ((english == words.get(1).englishWord) & (guguword == words.get(1).guguBadhunWord)) {
            wordsMatchPair = true;
            message = "Correct";
            totalCorrect += 1;
        }
        if ((english == words.get(2).englishWord) & (guguword == words.get(2).guguBadhunWord)) {
            wordsMatchPair = true;
            message = "Correct";
            totalCorrect += 1;
        }
        if ((english == words.get(3).englishWord) & (guguword == words.get(3).guguBadhunWord)) {
            wordsMatchPair = true;
            message = "Correct";
            totalCorrect += 1;
        }
        if ((english == words.get(4).englishWord) & (guguword == words.get(4).guguBadhunWord)) {
            wordsMatchPair = true;
            message = "Correct";
            totalCorrect += 1;
        }

    }

    public void LevelUp(View v){

//        if (levelLevel == Settings.currentLevel){
//            Settings.currentLevel += 1;
//        }
        finish();

    }
}
