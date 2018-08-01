package com.example.rubyf.chaos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    public int num_verticies = 5;
    public int iterations = 100;
    public double distance = .5;
    public int restriction = 0;
    public int period = 0;
    Random rand = new Random();
    public int startPoint = rand.nextInt(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
