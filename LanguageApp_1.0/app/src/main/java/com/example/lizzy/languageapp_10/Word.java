package com.example.lizzy.languageapp_10;

/**
 * Created by zoe on 8/04/2015.
 */
public class Word {

    public int id;
    public String englishWord, guguBadhunWord, definition, imageFile, soundFile, adult, level;

    public Word(){}

    @Override
    public String toString() {
            return "Location [id=" + id
                    + ",englishWord=" + englishWord
                    + ",guguBadhunWord=" + guguBadhunWord
                    + ",definition=" + definition
                    + ",imageFile=" + imageFile
                    + ",soundFile=" + soundFile
                    + ",adult=" + adult
                    + ",level=" + level + "]";
        }
    }