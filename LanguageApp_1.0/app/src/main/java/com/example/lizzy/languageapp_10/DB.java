package com.example.lizzy.languageapp_10;

/**
 * Created by zoe on 8/04/2015.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

public class DB extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
//    private static String DB_PATH = "data/data/zoe.com.dictionarydatabasedemo/databases/";
    private static String DB_PATH = "data/data/com.example.lizzy.languageapp_10/databases/";

    private static String DB_NAME = "LanguageAppDatabase";
    private static String TABLE_DICT = "Dictionary";
    private static String TABLE_STATS = "Stats";
    private static String TABLE_DATA = "UserData";
    private static String TABLE_BADGES = "Badges";

    private final Context context;
    private SQLiteDatabase db;


    // constructor
    public DB(Context context) {
        super( context , DB_NAME , null , 1);
        this.context = context;

        if(!checkDataBase()){
            try {
                create();
            } catch (IOException e) {
                throw new Error("Unable to create database");
            }
        }
    }


    // Creates a empty database on the system and rewrites it with your own database.
    public void create() throws IOException{

//        boolean dbExist = checkDataBase();
//
//        if(dbExist){
//            //do nothing - database already exist
//        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
//        }

    }

    // Check if the database exist to avoid re-copy the data
    private boolean checkDataBase(){

        boolean exists;
        SQLiteDatabase checkDB = null;

        try{
            String path = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){
            // database don't exist yet.
            e.printStackTrace();
        }
        exists = (checkDB != null);
        if(exists){
            checkDB.close();
        }

        return exists;
    }

    // copy your assets db to the new system DB
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = context.getAssets().open("LanguageAppDatabase.sqlite");

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    //Open the database
    public boolean open() {

        try {
            String myPath = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            return true;

        } catch(SQLException sqle) {
            db = null;
            return false;
        }

    }

    @Override
    public synchronized void close() {

        if(db != null)
            db.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //
    // PUBLIC METHODS TO ACCESS DB CONTENT
    //


    // Get All words from Dictionary!
    public List<Word> getWords() {

        List<Word> words = null;

        try {

            String      query  = "SELECT * FROM " + TABLE_DICT;

            SQLiteDatabase  db    = SQLiteDatabase.openDatabase( DB_PATH + DB_NAME , null, SQLiteDatabase.OPEN_READWRITE);
            Cursor      cursor  = db.rawQuery(query, null);

            // go over each row, build elements and add it to list
            words = new LinkedList<Word>();

            if (cursor.moveToFirst()) {
                do {
                    Word word  = new Word();
                    word.id = Integer.parseInt(cursor.getString(0));
                    word.englishWord = cursor.getString(1);
                    word.guguBadhunWord = cursor.getString(2);
                    word.definition = cursor.getString(3);
                    word.imageFile = cursor.getString(4);
                    word.soundFile = cursor.getString(5);
                    word.adult = cursor.getString(6);
                    word.level = cursor.getString(7);

                    words.add(word);
                } while (cursor.moveToNext());
            }
        } catch(Exception e) {
            // sql error
        }

        return words;
    }


    // Get All words from Dictionary!
    public List<Word> getSelectedWords() {

        List<Word> words = null;

        try {

            String      query  = "SELECT * FROM " + TABLE_DICT + " WHERE Level = '6'";

            SQLiteDatabase  db    = SQLiteDatabase.openDatabase( DB_PATH + DB_NAME , null, SQLiteDatabase.OPEN_READWRITE);
            Cursor      cursor  = db.rawQuery(query, null);

            // go over each row, build elements and add it to list
            words = new LinkedList<Word>();

            if (cursor.moveToFirst()) {
                do {

                    Word word  = new Word();
                    word.id = Integer.parseInt(cursor.getString(0));
                    word.englishWord = cursor.getString(1);
                    word.guguBadhunWord = cursor.getString(2);
                    word.definition = cursor.getString(3);
                    word.imageFile = cursor.getString(4);
                    word.soundFile = cursor.getString(5);
                    word.adult = cursor.getString(6);
                    word.level = cursor.getString(7);

                    words.add(word);

                } while (cursor.moveToNext());
            }
        } catch(Exception e) {
            // sql error
        }

        return words;
    }

    // Get User Datafrom Dictionary!
    public Cursor getData() {

        try {
            String query = "SELECT * FROM " + TABLE_DATA;
            SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
            //Cursor      cursor  = db.rawQuery(query, null);
            return db.rawQuery(query, null);
        } catch (Exception e) {
            Log.e("SQL", "SQLite error: getting user stats");
            throw new Error("SQL TABLE UserData Error");
        }
    }

    // Get User Datafrom Dictionary!
    public Cursor getBadges() {

        try {
            String query = "SELECT * FROM " + TABLE_BADGES;
            SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
            //Cursor      cursor  = db.rawQuery(query, null);
            return db.rawQuery(query, null);
        } catch (Exception e) {
            Log.e("SQL", "SQLite error: getting user stats");
            throw new Error("SQL TABLE UserData Error");
        }
    }

    public boolean addUserDetails(String name, String age, String Level){
        try {
            String query = "UPDATE " + TABLE_DATA + " SET Name='" + name + "', Age='" + age + "' WHERE Age='0'";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
            db.execSQL(query);
            return true;
        } catch (Exception e) {
            Log.e("SQL", "SQLite error: adding user stats");
            return false;
        }
    }

    public boolean setBadgeCompleted(String id){
        try {
            String query = "UPDATE " + TABLE_BADGES + " SET Achieved=1 WHERE id='" + id + "'";
            SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
            db.execSQL(query);
            return true;
        } catch (Exception e) {
            Log.e("SQL", "SQLite error: achieving badge " + id);
            return false;
        }
    }
}