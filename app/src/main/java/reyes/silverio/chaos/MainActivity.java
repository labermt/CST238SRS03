/*************************************************************
 * Author:          Silverio Reyes
 * Filename:        MainActivity.java
 * Organization:    Oregon Institute of Technology
 * Class:           CST238 GUI
 *
 * Date Created:    7/24/18 - Mitch Besser-Laber Created .gitignore,
 *                            README.md, and REPORT.md files
 *
 * Date Modified:   8/10/18 - Created project and
 *                            added dependencies
 *
 *                  8/10/18 - Created UI layout and static
 *                            array adapters that generates
 *                            drop down features for displaying
 *                            the number of vertices, number of dots
 *                            to iterate and color for the user.
 *                            There are also 3 manuel input boxes
 *                            for user to enter.
 *
 **************************************************************/
package reyes.silverio.chaos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HeaderViewListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Variables instantiated to handle UI resources
    EditText etDistance;
    Button btnStartchaos;
    Spinner spVertices, spDotsIterator, spColorDots, spPeriod, spStart;

    private String verticesSelection, dotsIteratorSelection, colorDotsSelection, periodSelection, startSelection, distanceSelection;
    private ColorStateList defaultThemeColor;
    private boolean isDistanceFieldValid = true;

    // Shared preferences
    public static final String sharedPrefs = "ChaosPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitalizeVariables();
        TextWatcherListener();
        CreateArrayAdaptersForSpinners();
        HandleClickListenerEventForSpinners();
        ValidateFormToStartChaos();
    }

    private void ValidateFormToStartChaos() {
        btnStartchaos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if distance value settings are valid before sending to next intent
               distanceSelection = etDistance.getText().toString();
               if (distanceSelection.isEmpty())
               {
                   distanceSelection = "0.5";
               }
               if (distanceSelection.matches("[a-zA-Z]+") || Float.parseFloat(distanceSelection) < 0.0 || Float.parseFloat(distanceSelection) > 1.0){
                   etDistance.setTextColor(Color.RED);
                   Toast.makeText(MainActivity.this, "Please enter a value between 0.0 and 1.0", Toast.LENGTH_SHORT).show();
                }
                else{
                        HandlerForNextIntent();
                    }
            }
        });
    }

    private void HandleClickListenerEventForSpinners() {
        // Handle click listener event
        spVertices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
                // Save user selection
                verticesSelection = (String)parent.getItemAtPosition(pos);

                // Reset to default background. Use text view of xml file and not the actual spinner
                ((TextView)spVertices.getSelectedView()).setTextColor(Color.BLACK);

                //Toast.makeText(MainActivity.this, "The vertice you selected is " + verticesSelection, Toast.LENGTH_SHORT).show();

                // Check the vertices selection and Generate the columns for period and start spinners
                if (verticesSelection.equals("3")){
                    GeneratePeriodStateArrayAdapter(R.array.period3Vertices_array, R.array.start3Vertices_array);
                }
                else if (verticesSelection.equals("4")){
                    GeneratePeriodStateArrayAdapter(R.array.period4Vertices_array, R.array.start4Vertices_array);
                }
                else if (verticesSelection.equals("5")){
                    GeneratePeriodStateArrayAdapter(R.array.period5Vertices_array, R.array.start5Vertices_array);
                }
                else if (verticesSelection.equals("6")){
                    GeneratePeriodStateArrayAdapter(R.array.period6Vertices_array, R.array.start6Vertices_array);
                }
                else if (verticesSelection.equals("7")){
                    GeneratePeriodStateArrayAdapter(R.array.period7Vertices_array, R.array.start7Vertices_array);
                }
                else if (verticesSelection.equals("8")){
                    GeneratePeriodStateArrayAdapter(R.array.period8Vertices_array, R.array.start8Vertices_array);
                }
                else if (verticesSelection.equals("9")){
                    GeneratePeriodStateArrayAdapter(R.array.period9Vertices_array, R.array.start9Vertices_array);
                }
                else if (verticesSelection.equals("10")){
                    GeneratePeriodStateArrayAdapter(R.array.period10Vertices_array, R.array.start10Vertices_array);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){
                // Interface callback for default value: first in list
                verticesSelection = (String)parent.getItemAtPosition(0);
            }
        });

        // Handle click listener event
        spDotsIterator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
                // Save user selection
                dotsIteratorSelection = (String)parent.getItemAtPosition(pos);

                // Reset to default background. Use text view of xml file and not the actual spinner
                ((TextView)spDotsIterator.getSelectedView()).setTextColor(Color.BLACK);

                //Toast.makeText(MainActivity.this, "The number of dots to populate canvas is " + dotsIteratorSelection, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){
                // Interface callback for default value: first in list
                dotsIteratorSelection = (String)parent.getItemAtPosition(0);
            }
        });

        // Handle click listener event
        spColorDots.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
                // Save user selection
                colorDotsSelection = (String)parent.getItemAtPosition(pos);

                // Reset to default background. Use text view of xml file and not the actual spinner
                ((TextView)spColorDots.getSelectedView()).setTextColor(Color.BLACK);

               //Toast.makeText(MainActivity.this, "The dot color you selected is " + colorDotsSelection, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){
                // Interface callback for default value: first in list
                colorDotsSelection = (String)parent.getItemAtPosition(0);
            }
        });
    }

    private void GeneratePeriodStateArrayAdapter(int period_arrayID, int start_arrayID) {
        // Create an array adapter that will use the string array resources for days within a given month
        // It will also create from existing resource from design layout
        ArrayAdapter<CharSequence> periodAdapter = ArrayAdapter.createFromResource(this, period_arrayID, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> startAdapter = ArrayAdapter.createFromResource(this, start_arrayID, android.R.layout.simple_spinner_item);

        // Display the list of choices in drop down column
        periodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spPeriod.setAdapter(periodAdapter);
        spStart.setAdapter(startAdapter);

        // Handle click listener event
        spPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
                // Save user selection
                periodSelection = (String)parent.getItemAtPosition(pos);

                // Reset to default background. Use text view of xml file and not the actual spinner
                ((TextView)spPeriod.getSelectedView()).setTextColor(Color.BLACK);

                //Toast.makeText(MainActivity.this, "The period you selected is " + periodSelection, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){
                // Interface callback
            }
        });

        // Handle click listener event
        spStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
                // Save user selection
                startSelection = (String)parent.getItemAtPosition(pos);

                // Reset to default background. Use text view of xml file and not the actual spinner
                ((TextView)spStart.getSelectedView()).setTextColor(Color.BLACK);

                //Toast.makeText(MainActivity.this, "The starting vertex you selected is " + startSelection, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){
                // Interface callback
            }
        });
    }

    private void TextWatcherListener() {
        etDistance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                etDistance.setTextColor(defaultThemeColor);
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void InitalizeVariables() {
        // Variables for the UI elements and connects them to the XML layout
        etDistance = (EditText)findViewById(R.id.etDistance);
        btnStartchaos = (Button) findViewById(R.id.btnStartChaos);
        spVertices = (Spinner) findViewById(R.id.spVertices);
        spDotsIterator = (Spinner) findViewById(R.id.spDotIteration);
        spColorDots = (Spinner) findViewById(R.id.spDotColor);
        spPeriod = (Spinner) findViewById(R.id.spPeriod);
        spStart = (Spinner) findViewById(R.id.spStart);

        // Get the default theme text color for text views
        defaultThemeColor = etDistance.getTextColors();
    }

    private void CreateArrayAdaptersForSpinners() {
        // Create an array adapter that will use the string array resources
        // It will also create from existing resource from design layout
        ArrayAdapter<CharSequence> interfaceAdapterVerticies = ArrayAdapter.createFromResource(this, R.array.vertices_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> interfaceAdapterDotIterator = ArrayAdapter.createFromResource(this, R.array.dots_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> interfaceAdapterDotColor = ArrayAdapter.createFromResource(this, R.array.colors_array, android.R.layout.simple_spinner_item);

        // Display the list of choices in drop down column
        interfaceAdapterVerticies.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        interfaceAdapterDotIterator.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        interfaceAdapterDotColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spVertices.setAdapter(interfaceAdapterVerticies);
        spDotsIterator.setAdapter(interfaceAdapterDotIterator);
        spColorDots.setAdapter(interfaceAdapterDotColor);
    }

    private void HandlerForNextIntent() {
        // Get resources from shared preferences
        SharedPreferences preferences = this.getSharedPreferences(sharedPrefs, Context.MODE_PRIVATE);

        // Edit and save entry list
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.putString("VerticesValue", verticesSelection);
        editor.putString("IterationValue", dotsIteratorSelection);
        editor.putString("ColorValue", colorDotsSelection);
        editor.putString("PeriodValue", periodSelection);
        editor.putString("StartValue", startSelection);
        editor.putString("DistanceValue", distanceSelection);
        editor.apply();

        // Pass intent to next activity
        Intent ChaosIntent = new Intent(MainActivity.this, DisplayChaosImage.class);
        startActivity(ChaosIntent);
    }
}
