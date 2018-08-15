package com.example.dixon.drawableproject;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {
    private Canvas canvas = new Canvas();
    private Path path = new Path();
    private Path path2 = new Path();
    private Paint paint = new Paint();
    private Paint paint2 = new Paint();
    private Resources.Theme theme = getContext().getTheme();

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint.setColor(getResources().getColor(R.color.colorAccent, theme));
        paint2.setColor(getResources().getColor(R.color.colorPrimary, theme));
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(10);
        paint2.setStyle(Paint.Style.STROKE);
        path.moveTo(50, 50);
        path.lineTo(750, 750);
        path2.moveTo(750, 50);
        path2.lineTo(50, 350);
        path2.lineTo(50, 650);
        path2.lineTo(250, 650);
        path2.lineTo(250, 400);
        path2.lineTo(600, 400);
        path2.lineTo(600, 700);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
        canvas.drawPath(path2, paint2);

    }
}
