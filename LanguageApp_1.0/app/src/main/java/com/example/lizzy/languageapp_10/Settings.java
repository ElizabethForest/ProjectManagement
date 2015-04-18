package com.example.lizzy.languageapp_10;

import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Lizzy on 31/03/2015.
 */
public class Settings {

    public static String name = null;
    public static int age = 0;
    public static int currentLevel = 0;
    public static int TOTAL_LEVELS = 10;
    public static boolean wordGame = false;
    public static boolean picGame = false;
    public static boolean dictViewed = false;
    public static ArrayList<Badge> badges;

    public static void setUpBadges(DB db){
        badges = new ArrayList<Badge>();
        Badge badge;
        Cursor cursor = db.getBadges();
        if (cursor.moveToFirst()){
            do{
                badge = new Badge(cursor.getString(1), cursor.getString(0), Boolean.parseBoolean(cursor.getString(2)), cursor.getInt(3), cursor.getInt(4));
                badges.add(badge);
            } while (cursor.moveToNext());
        }
//        //TODO: Implement this with database stuff
//        for (int i = 0; i < 6; i++){
//            for (int j = 0; j < 7; j ++){
//                badge = new Badge("Badge " + (i+j), (i+j) + "", (j % 2 == 0), i, j);
//                badges.add(badge);
//            }
//        }
    }

    public static void getData(DB db){
        Cursor cursor = db.getData();
        if(cursor.moveToFirst()){
            name = cursor.getString(0);
            age = Integer.parseInt(cursor.getString(1));
            currentLevel = Integer.parseInt(cursor.getString(2));
            wordGame = Boolean.parseBoolean(cursor.getString(3));
            picGame = Boolean.parseBoolean(cursor.getString(4));
            dictViewed = Boolean.parseBoolean(cursor.getString(5));
        }
    }

    public static void saveUserDetails(DB db){
        db.addUserDetails(name, age +"", currentLevel+"");
    }
}
