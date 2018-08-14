package com.example.dixon.drawableproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner colorSpinner = (Spinner) findViewById(R.id.spinnerColors);
        Spinner numberSpinner = (Spinner) findViewById(R.id.spinnerNumbers);
        Spinner fractionSpinner = (Spinner) findViewById(R.id.spinnerFraction);
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this,
                R.array.color_array, R.layout.spinner_colors);
        colorAdapter.setDropDownViewResource(R.layout.spinner_list);
        ArrayAdapter<CharSequence> numberAdapter = ArrayAdapter.createFromResource(this,
                R.array.number_array, R.layout.spinner_colors);
        numberAdapter.setDropDownViewResource(R.layout.spinner_list);
        ArrayAdapter<CharSequence> fractionAdapter = ArrayAdapter.createFromResource(this,
                R.array.fraction_array, R.layout.spinner_colors);
        fractionAdapter.setDropDownViewResource(R.layout.spinner_list);
        // Apply the adapter to the spinner
        colorSpinner.setAdapter(colorAdapter);
        numberSpinner.setAdapter(numberAdapter);
        fractionSpinner.setAdapter(fractionAdapter);
    }



}
