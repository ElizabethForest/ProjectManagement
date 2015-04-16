package com.example.lizzy.languageapp_10;

import java.util.ArrayList;

/**
 * Created by Lizzy on 31/03/2015.
 */
public class Settings {

    public static String name = null;
    public static int age = 0;
    public static int currentLevel = 0;
    public static int totalLevels = 10;
    public static ArrayList<Badge> badges;

    public static void setUpBadges(){
        badges = new ArrayList<Badge>();
        Badge badge;
        //TODO: Implement this with database stuff
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 7; j ++){
                badge = new Badge("Badge " + (i+j), (i+j) + "", (j % 2 == 0), i, j);
                badges.add(badge);
            }
        }
    }
}
