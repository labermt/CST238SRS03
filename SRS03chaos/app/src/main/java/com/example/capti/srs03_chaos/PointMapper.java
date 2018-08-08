package com.example.capti.srs03_chaos;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PointMapper extends View
{
    Paint paint_;

    public PointMapper(Context context) {
        super(context);
        init(null, 0);
    }

    public PointMapper(Context context, AttributeSet attribs) {
        super(context, attribs);
        init(attribs, 0);
    }

    public PointMapper(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }
/*
    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.junk, defStyle, 0);

        mExampleString = a.getString(
                R.styleable.junk_exampleString);
        mExampleColor = a.getColor(
                R.styleable.junk_exampleColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.junk_exampleDimension,
                mExampleDimension);

        if (a.hasValue(R.styleable.junk_exampleDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.junk_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }
*/

    private void init(AttributeSet attribs, int defStyle)
    {
        paint_ = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_.setStyle(Paint.Style.FILL);
    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(500, 500, 200, paint_);
    }

}
