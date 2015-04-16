package com.example.lizzy.languageapp_10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by Lizzy on 11/04/2015.
 */
public class AtlasManager {

    Bitmap atlas;
    int totalRows = 6;
    int totalColumns = 7;
    Context context;

    public AtlasManager(Context context){
        this.context = context;
        atlas = BitmapFactory.decodeResource(context.getResources(), R.drawable.badgeatlas);
    }

    public Bitmap getAtlas() {
        return atlas;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public int[] getTileSize(){
        return new int[]{atlas.getWidth() / totalColumns, atlas.getHeight() / totalRows};
    }

    public int getTileWidth(){
        return (atlas.getWidth() / totalColumns);
    }

    public int getTileHight(){
        return (atlas.getHeight() / totalRows);
    }
}
