package edu.oit.labermt.chaos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Drawing extends View {
    Paint paint_;
    List<Point> p_points = new ArrayList<>();
    List<Point> p_drawPoints = new ArrayList<>();
    //float [] points = new float [200];    // Hard coded for triangle 100 Vertex max
    //float [] new_points = new float [1000];
    float centerX;
    float centerY;
    float percent = .5f;           // Hard coded for start
    int vertices = 8;               // Hard coded for start
    int iterations = 50;           // Hard coded for start
    int myColor = Color.RED;
    int start = 2;                  // Hard coded for now
    int period = 2;                  // Hard coded for now

    public Drawing(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init()
    {
        paint_ = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_.setStyle(Paint.Style.FILL);
        paint_.setColor(myColor);
        paint_.setStrokeWidth(getResources().getDimension(R.dimen.point_size));
        paint_.setTextAlign(Paint.Align.CENTER);
    }

    protected void CalculateVertices(){
        double theta = 2* Math.PI / vertices;

        //int index = 0;
        for (int i = 0; i < vertices; i++ ){
            p_points.add(new Point((int)(centerX + centerX * (float)Math.cos(theta*i)), (int) (centerY + centerY *(float)Math.sin(theta*i))));
        }
    }

    protected void CalculatePoints(){
        Random rand = new Random();

        int first_v = rand.nextInt(vertices);

        p_drawPoints.add(new Point((int)((centerX + p_points.get(first_v).x)*percent),(int)((centerX + p_points.get(first_v).y)*percent)));

        for (int i = 0; i < iterations; i++ ) {
            int random_v = rand.nextInt(vertices);
            p_drawPoints.add(new Point((int)((p_drawPoints.get(i).x + p_points.get(random_v).x)*percent),(int)((p_drawPoints.get(i).y + p_points.get(random_v).y)*percent)));
        }

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawCircle(100, 100, 50, paint_);
        //points.add((float)canvas.getWidth() /2);
        //points.add((float)canvas.getHeight() /2);
        centerX = canvas.getWidth() /2;
        centerY = canvas.getHeight() /2;
        CalculateVertices();
        //canvas.drawPoints(Arrays.copyOfRange(points, 0, vertices*2), paint_);
        for (Point p : p_points)
        {
            canvas.drawPoint((float) p.x, (float) p.y, paint_);
        }
        CalculatePoints();
        for (Point p : p_drawPoints)
        {
            canvas.drawPoint((float) p.x, (float) p.y, paint_);
        }

        p_points.clear();
        p_drawPoints.clear();
    }

}
