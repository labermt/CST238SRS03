package reyes.silverio.chaos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewManager;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DisplayChaosImage extends AppCompatActivity{
    public String totalVerticies, totalDots, dotColor, period, startingVertex, distanceVal;
    View ChaosView;
    private Canvas mCanvas;
    private ImageView mImageView;
    private Bitmap mBitmap;
    private Chaos chaos;

    // Shared preferences
    public static final String sharedPrefs = "ChaosPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_chaos_image);

        InitializeVariables();
        //ChaosView = new Chaos(this);
       //mImageView.setImageBitmap(mBitmap);
        //mCanvas = new Canvas(mBitmap);
        //mCanvas.drawColor(Color.BLACK);
    }

    private void InitializeVariables() {
        // Get resources
        SharedPreferences preferences = this.getSharedPreferences(sharedPrefs, Context.MODE_PRIVATE);

        totalVerticies = preferences.getString("VerticesValue", null);
        totalDots = preferences.getString("IterationValue", null);
        dotColor = preferences.getString("ColorValue", null);
        period = preferences.getString("PeriodValue", null);
        startingVertex = preferences.getString("StartValue", null);
        distanceVal = preferences.getString("DistanceValue", null);

        // Pass values to chaos setter
        chaos.VariableSetter(totalVerticies, totalDots, dotColor, period, startingVertex, distanceVal);
    }

    public class Chaos extends View {
        Paint paint;
        private String totalVertices, totalDots, dotColor, period, startingVertex, distanceVal;

        public void VariableSetter(String totVert, String totDots, String dotColor, String period,
                                   String startVertex, String distanceValue)
        {
            totalVertices = totVert;
            totalDots = totDots;
            this.dotColor = dotColor;
            this.period = period;
            startingVertex = startVertex;
            distanceVal = distanceValue;
            invalidate();
        }
        public Chaos(Context context) {
            super(context);
            init();
        }

        private void init() {
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.FILL);
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawCircle(100, 100, 50, paint);
        }
    }
}

