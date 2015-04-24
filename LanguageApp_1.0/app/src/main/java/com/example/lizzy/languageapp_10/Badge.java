package com.example.lizzy.languageapp_10;

/**
 * Created by Lizzy on 11/04/2015.
 */
public class Badge {

    String description = "empty badge description";
    int row = 0;
    int column = 0;
    boolean achieved = false;
    String id = "empty";

    public Badge(String description, String id, boolean achieved) {
        this.description = description;
        this.id = id;
        this.achieved = achieved;
    }

    public Badge(String description, String id, boolean achieved, int row, int column) {
        this.description = description;
        this.id = id;
        this.achieved = achieved;
        this.row = row;
        this.column = column;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAchieved(boolean achieved, DB db) {
        this.achieved = achieved;
        db.setBadgeCompleted(id);
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
