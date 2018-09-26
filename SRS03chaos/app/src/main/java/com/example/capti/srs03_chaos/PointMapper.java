package com.example.capti.srs03_chaos;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class PointMapper extends View
{
    Paint paint_;
    LinkedList<Point> InitialPoints;
    LinkedList<Point> DrawnPoints;
    int Height;
    int Width;
    public static Point Center;
    public int Radius ;
    public int RadiusFactor = 2;
    double ShapeVertCount = 3;
    Random rand;
    int DotSize = 4;
    private Handler handler = new Handler();
    float Rotation = 2;

    public PointMapper(Context context)
    {
        super(context);
        init(null, 0);
        constructionSetup();
    }

    public PointMapper(Context context, AttributeSet attribs)
    {
        super(context, attribs);
        init(attribs, 0);
        constructionSetup();
    }

    public PointMapper(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
        constructionSetup();
    }

    private void init(AttributeSet attribs, int defStyle)
    {
        paint_ = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_.setStyle(Paint.Style.FILL);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        constructionSetup();
        CanvasPainter(canvas);
    }

    void constructionSetup()
    {
        rand = new Random((long)ShapeVertCount);
        Center = new Point(Width/2,Height/2);
        Radius = Height / RadiusFactor;
        InitialPoints = new LinkedList<Point>();
        handler.postDelayed(runnable, 100);
        DrawnPoints = new LinkedList<Point>();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        Width = w;
        Height = h;
    }

    protected void CanvasPainter(Canvas canvas)
    {
        ShapePoints();
        for(Point p : InitialPoints)
        {
            canvas.drawCircle(p.x, p.y, DotSize, paint_);
        }
        VerticyChaser(canvas);
    }

    protected void ShapePoints()
    {
        for(double i = 1.0; i <= ShapeVertCount; i++)
        {
            double region = (i/ShapeVertCount);
            double y = Math.sin(((Math.PI * 2))* region);
            double x = Math.cos(((Math.PI * 2))* region);
            InitialPoints.add(new Point( (int)(Center.x + (Radius * x)), (int)(Center.y + (Radius * y))));
        }
    }

    private void VerticyChaser(Canvas canvas)
    {
        int Rando = rand.nextInt((int)ShapeVertCount);
        Point Start = InitialPoints.get(Rando);
        Point Current = Start;

        for(int i = 0; i < 50000; ++i)
        {
            Rando = rand.nextInt((int)ShapeVertCount);

            if(i==0)
            {
                Current = Start;
                DrawnPoints.addLast(Current);
            }
            else
            {
                Rando = rand.nextInt((int)ShapeVertCount);
                Current = Start;
                Start = NextPointPicker(Current,InitialPoints.get(Rando));
                DrawnPoints.add(Start);
            }
        }
            Point NextPoint = InitialPoints.get(rand.nextInt((int)ShapeVertCount));

            for(Point p : DrawnPoints) {

               //DrawnPoints.add(NextPointPicker(Start,NextPoint));
                canvas.drawCircle(p.x,p.y,DotSize,paint_);
            }

        }


    private Runnable runnable = new Runnable()
    {
        @Override
        public void run()
        {
            /* do what you need to do */
            //DrawnPoints.add();
            /* and here comes the "trick" */
            handler.postDelayed(this, 100);
        }
    };

    private Point NextPointPicker(Point at, Point goingto)
    {
        //int rando = rand.nextInt((int)ShapeVertCount);

        Point NextPosition = new Point();
        NextPosition.x = ((goingto.x - at.x) /2) + at.x;
        NextPosition.y = ((goingto.y - at.y) /2) + at.y;

        return NextPosition;
    }
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