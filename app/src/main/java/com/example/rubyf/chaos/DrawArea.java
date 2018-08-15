package com.example.rubyf.chaos;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.example.rubyf.chaos.Point;

import java.util.ArrayList;
import java.util.Stack;

/**
 * TODO: document your custom view class.
 */
public class DrawArea extends View {
    private int mExampleColor = Color.BLACK; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;
    private Paint mPaint;
    private Point center = new Point();
    public ArrayList<Point> defaultPoints = new ArrayList<>();
    public Stack<Point> points = new Stack<>();
    public int verts = 1;

    public DrawArea(Context context) {
        super(context);
        init(null, 0);
    }

    public DrawArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DrawArea(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.DrawArea, defStyle, 0);

        mExampleDimension = a.getDimension(
                R.styleable.DrawArea_exampleDimension,
                mExampleDimension);

        a.recycle();

        // Set up a default TextPaint object
        mPaint = new Paint();
        mPaint.setStrokeWidth(20);

        // Update TextPaint and text measurements from attributes
        invalidatePaintAndMeasurements();
    }

    private void invalidatePaintAndMeasurements() {
        mPaint.setColor(mExampleColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        center.setX(w/2);
        center.setY(h/2);
        setDefaultPoints();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        // Draw the default points.
        for (int i = 0; i < defaultPoints.size(); i++) {
            canvas.drawPoint(defaultPoints.get(i).getX(), defaultPoints.get(i).getY(), mPaint);
        }

    }

    public int getExampleColor() {
        return mExampleColor;
    }

    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
        invalidatePaintAndMeasurements();
    }

    public Point getCenter() {
        return center;
    }
//
//    public float getExampleDimension() {
//        return mExampleDimension;
//    }
//
//    public void setExampleDimension(float exampleDimension) {
//        mExampleDimension = exampleDimension;
//        invalidatePaintAndMeasurements();
//    }
//
//    public Drawable getExampleDrawable() {
//        return mExampleDrawable;
//    }
//
//    public void setExampleDrawable(Drawable exampleDrawable) {
//        mExampleDrawable = exampleDrawable;
//    }

    public void setVerts(int numVerts){
        verts = numVerts;
    }

    public void setDefaultPoints() {
        int cx = center.getX();
        int cy = center.getY();
        int radius = (cx < cy) ? cx : cy;
        double angle = (360 / verts);
        for (int theta = 0; theta < 360; theta += angle) {
            double rdegrees = Math.toRadians(theta);
            int x = (int) (radius * Math.cos(rdegrees));
            int y = (int) (radius * Math.sin(rdegrees));
            Point point = new Point((x + cx), (y + cy));
            defaultPoints.add(point);
        }
    }

}
