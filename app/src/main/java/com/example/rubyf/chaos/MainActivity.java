package com.example.rubyf.chaos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Point> defaultPoints = new ArrayList();
    public Stack points = new Stack();

    public int num_verticiesNum = 3;
    public int iterationsNum = 100;
    public double distanceNum = .5;
    public int restrictionNum = 0;
    public int periodNum = 0;
    public DrawArea area;

    Random rand = new Random();
    public int startPoint = rand.nextInt(3);
    private Spinner distance;
    private Spinner period;
    private Spinner restriction;
    private Spinner color;
    private TextView verts;
    private TextView iters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        distance = findViewById(R.id.distance);
        period = findViewById(R.id.period);
        restriction = findViewById(R.id.restriction);
        color = findViewById(R.id.color);
        iters = findViewById(R.id.iterations);
        verts = findViewById(R.id.verticies);
        area = findViewById(R.id.drawCanvas);
        area.setVerts(num_verticiesNum);
    }


}
//    public void Calculate(){
//        for(int i = 0; i < iterationsNum; i++){
//
//        }
//    }

