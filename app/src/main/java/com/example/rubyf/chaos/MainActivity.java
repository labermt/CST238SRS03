package com.example.rubyf.chaos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.rubyf.chaos.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Point> defaultPoints;
    public Stack<Point> points;

    public int num_verticiesNum = 3;
    public int iterationsNum = 100;
    public double distanceNum = .5;
    public int restrictionNum = 0;
    public int periodNum = 0;
    public DrawArea area;

    Random rand;
    public int startPoint;
    private Spinner distance;
    private Spinner period;
    private Spinner restriction;
    private Spinner color;
    private TextView verts;
    private TextView iters;
    private Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        defaultPoints = new ArrayList<>();
        points = new Stack<>();

        rand = new Random();
        int startPoint = rand.nextInt(3);

        setContentView(R.layout.activity_main);
        distance = findViewById(R.id.distance);
        period = findViewById(R.id.period);
        restriction = findViewById(R.id.restriction);
        color = findViewById(R.id.color);
        iters = findViewById(R.id.iterations);
        verts = findViewById(R.id.verticies);
        area = findViewById(R.id.drawCanvas);
        go = findViewById(R.id.draw);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tempInt = tryParseInt(verts.getText().toString());
                if(tempInt != -1){
                    num_verticiesNum = tempInt;
                }
                tempInt = tryParseInt(iters.getText().toString());
                if(tempInt != -1){
                    iterationsNum = tempInt;
                }

                tempInt = tryParseInt(restriction.getSelectedItem().toString());
                if(tempInt != -1){
                    restrictionNum = tempInt;
                }

                tempInt = tryParseInt(period.getSelectedItem().toString());
                if(tempInt != -1){
                    periodNum = tempInt;
                }

                String temp  = distance.getSelectedItem().toString();
                temp = "." + temp.substring(0, temp.length() - 1);
                double tempDBL = tryParseDouble(temp);
                if(tempDBL != -1){
                    distanceNum = tempDBL;
                }

                area.setVerts(num_verticiesNum);
                area.setDefaultPoints();
                area.invalidate();
            }
        });

    }
    public int tryParseInt(String s){
        try{
            return Integer.parseInt(s);
        } catch(NumberFormatException nfe){
            return -1;
        }
    }

    public double tryParseDouble(String s){
        try{
            return Double.parseDouble(s);
        } catch(NumberFormatException nfe){
            return -1;
        }
    }

}


//    public void Calculate(){
//        for(int i = 0; i < iterationsNum; i++){
//
//        }
//    }

