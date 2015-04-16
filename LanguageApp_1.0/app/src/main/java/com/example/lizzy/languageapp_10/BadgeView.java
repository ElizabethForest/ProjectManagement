package com.example.lizzy.languageapp_10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Lizzy on 11/04/2015.
 */
public class BadgeView extends ImageView {

    Badge badge;
    AtlasManager manager;
    Rect sourceRect;
    Rect destRect;

    public BadgeView(Context context) {
        super(context);
    }

    public BadgeView(Context context, Badge badge) {
        super(context);
        this.badge = badge;
        manager = new AtlasManager(this.getContext());
    }

    public BadgeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public BadgeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public void setManager() {
        manager = new AtlasManager(this.getContext());
    }

    private Rect getSourceRect(){
        sourceRect = new Rect();
        int left =  badge.getColumn() * manager.getTileWidth() ;
        int right = left + manager.getTileWidth();
        int top = badge.getRow() * manager.getTileHight();
        int bottom = top + manager.getTileHight();
        sourceRect.set(left, top, right, bottom);
        Log.w("collumn", left + "");
        return sourceRect;
    }

    private Rect getDestRect(){
        destRect = new Rect();
        destRect.set(0, 0, getWidth(), getHeight());
        return destRect;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(manager.getAtlas(), getSourceRect(), getDestRect(), null);
    }
}
