package com.maneesha14w.speedguess;

//TODO implement better design (material?)

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Identify_Car_Make_Activity extends AppCompatActivity {
    //array that stores the name of cars that correspond to the first part of the image file
    private final String[] carNames = {"ford", "mercedes", "tesla"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify__car__make_);

        // getting and setting the image view to a random img
        ImageView imgView = findViewById(R.id.car_img_view);
        imgView.setImageResource(getRandomImageResource(imgView));

        //sets spinner up
        spinnerSetter();
    }


    //method that returns int id of a random image and sets the tag of the ImageView to whatever make of car is selected
    private int getRandomImageResource(ImageView imgView) {
        Random rand = new Random();
        String carName = carNames[rand.nextInt(3)]; // random index from car names array
        String fileNum = String.valueOf(rand.nextInt(10) + 1); //Random car number

        imgView.setTag(carName); //setting the tag of the ImageView to whatever make of car is selected

        String fileName = carName + "_" + fileNum; //string that corresponds to a random image file
        return getResources().getIdentifier(fileName, "drawable", getPackageName()); // returns an int id of the filename
    }

    private void spinnerSetter() {
        Spinner carMakeSpinner = findViewById(R.id.carMakeSpinner); //get id of spinner

        String [] carMakeNames = getResources().getStringArray(R.array.car_names_array); //array to hold names of models

        // adapter draws spinner
        ArrayAdapter<String> adapter  = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carMakeNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //sets resource of a string array in strings.xml
        carMakeSpinner.setAdapter(adapter); //setting the adapter to the spinner
    }


    // function for when identify is clicked
    public void identifyBtnClick(View view) {
        //get the item currently in spinner
        Spinner carMakeSpinner = findViewById(R.id.carMakeSpinner);
        String selectedModel = carMakeSpinner.getSelectedItem().toString();

        // get the correct answer
        ImageView imgView = findViewById(R.id.car_img_view);
        String correctModel = imgView.getTag().toString();

        // if Select model has been selected.
        if (selectedModel.equals("Select Model")){
            Toast.makeText(this, "Please Select a Proper Model", Toast.LENGTH_SHORT).show();
        }
        else if (selectedModel.toLowerCase().equals(correctModel.toLowerCase())) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
    }
}