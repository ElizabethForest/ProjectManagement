package com.example.lizzy.languageapp_10;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

/**
 * Created by Lizzy on 10/04/2015.
 */
public class LevelButton extends Button implements View.OnClickListener{

    Bitmap open = BitmapFactory.decodeResource(getResources(), R.drawable.openlevel);
    Bitmap closed = BitmapFactory.decodeResource(getResources(), R.drawable.lockedlevel);
    int level = 0;
    int width = 0;
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public LevelButton(Context context) {
        super(context);
    }

    public LevelButton(Context context, int level) {
        super(context);
        this.level = level;
        this.setOnClickListener(this);
    }

    public LevelButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LevelButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setLevel(int level){
        this.level = level;
    }

    @Override
    public void onClick(View v) {
        if (level <= Settings.currentLevel) {
            Intent intent = new Intent(getContext(), LevelInfo.class);
//            Bundle b = new Bundle();
//            b.putInt("selectedLevel", level);
            intent.putExtra("selectedLevel", level);
            getContext().startActivity(intent);
        }
        else {
            Toast.makeText(getContext(), "Please finish level " + Settings.currentLevel + " before starting harder levels", Toast.LENGTH_SHORT).show();
        }
        //Settings.currentLevel = level + 1;
//        findViewById(R.id.levelslayout).invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (level <= Settings.currentLevel){
            canvas.drawBitmap(open, 0, 0, null);

            paint.setColor(Color.BLACK);
            paint.setTextSize(2 * (getResources().getDimension(R.dimen.level_button_text)));

            int xPos = (int) ((canvas.getWidth() / 2) + ((paint.descent() + paint.ascent()) / 2));
            int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;

            if (level == 0) {
                if (width == 0){
                    width = (int) (canvas.getWidth() * 2.5);
                    this.setWidth(width);
                }
                xPos = (int) ((canvas.getWidth() / 5) + ((paint.descent() + paint.ascent()) / 2));
                canvas.drawText(level + "\t\t Tutorial", xPos, yPos, paint);

            } else
                canvas.drawText(level + "", xPos, yPos, paint);
        } else {
            canvas.drawBitmap(closed, 0, 0, null);
        }


    }
}
