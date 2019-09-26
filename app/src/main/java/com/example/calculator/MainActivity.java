package com.example.calculator;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;





public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    // Calorie calculator
    Spinner genderSpinner;
    Spinner activitySpinner;


    String[] activities = {"Passive","Medium","Active"};
    String[] genders= {"Male","Female"};

    int age;
    double height;
    double weight;
    double calories;
    double activityCoefficient;
    boolean isMale; // true - male, false - female

    EditText ageText;
    EditText heightText;
    EditText weightText;
    TextView caloriesText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        genderSpinner.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the genders list
        ArrayAdapter genderAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, genders);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        genderSpinner.setAdapter(genderAdapter);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        activitySpinner = (Spinner) findViewById(R.id.activitySpinner);
        activitySpinner.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the activities list
        ArrayAdapter activityAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, activities);
        activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        activitySpinner.setAdapter(activityAdapter);

        // connecting variables with EditTexts & TextViews
        ageText = (EditText) findViewById(R.id.age);
        heightText = (EditText) findViewById(R.id.heightField);
        weightText = (EditText) findViewById(R.id.weightField);
        caloriesText = (TextView) findViewById(R.id.caloriesField);

    }


    //works
    public void calculate(View view){
        age = Integer.valueOf(ageText.getText().toString());
        height = Double.valueOf(heightText.getText().toString());
        weight = Double.valueOf(weightText.getText().toString());
        isMale = (genderSpinner.getSelectedItem().toString().equals("Male")) ? true : false;

        if (activitySpinner.getSelectedItem().toString().equals("Passive")) {
            activityCoefficient = 1.2;
        } else if (activitySpinner.getSelectedItem().toString().equals("Medium")) {
            activityCoefficient = 1.55;
        } else {
            activityCoefficient = 1.9;
        }

        //BMR = 10W + 6.25H - 5A + 5   (  male  )
        //BMR = 10W + 6.25H - 5A - 161 ( female )
        if (isMale) {
            calories = ((10 * weight) + (6.25 * height) - (5 * age) + 5) * activityCoefficient;
        } else {
            calories = ((10 * weight) + (6.25 * height) - (5 * age) - 161) * activityCoefficient;
        }

        caloriesText.setText(Double.toString(calories));
    }
//
//
//    //works
//    public void calculateCalories(View view){
//        String a = ageText.getText().toString();
////        caloriesText.setText(a);
//        Toast.makeText(this, a, Toast.LENGTH_SHORT).show();
//    }
////
//    //works
//    public void gipgip(View view) {
//        String a = "gip gip ura";
//        caloriesText.setText(a);
//    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this, genderSpinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
//        Toast.makeText(this, "Nothing Selected, Baike", Toast.LENGTH_SHORT).show();
    }
}
