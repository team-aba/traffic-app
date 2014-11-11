package com.detroitlabs.trafficapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * Created by BFineRocks on 11/10/14.
 */
public class EventHeader extends Drawable {

    private final String eventHeaderText;
    private final Paint paint;

    public EventHeader(String eventHeaderText){
        this.eventHeaderText = eventHeaderText;

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(22f);

        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        paint.setStyle(Style.FILL);
        paint.setTextAlign(Align.CENTER);
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(eventHeaderText, 0, 0, paint);
    }

    @Override
    public void setAlpha(int i) {
        paint.setAlpha(i);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
