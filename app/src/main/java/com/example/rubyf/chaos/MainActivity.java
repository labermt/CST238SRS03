package com.example.rubyf.chaos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import com.example.rubyf.chaos.DrawArea;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public int num_verticiesNum = 3;
    public int iterationsNum = 100;
    public double distanceNum = .5;
    public int restrictionNum = 0;
    public int periodNum = 0;
    Random rand = new Random();
    public int startPoint = rand.nextInt(3);
    private Spinner distance;
    private Spinner period;
    private Spinner restriction;
    private Spinner color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        distance = findViewById(R.id.distance);
        period = findViewById(R.id.period);
        restriction = findViewById(R.id.restriction);
        color = findViewById(R.id.color);
    }
}
