package com.maneesha14w.speedguess;

//TODO optimise randomising by using AsyncTask

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

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
        Spinner carMakeSpinner = findViewById(R.id.carMakeSpinner);
        String [] carMakeNames = getResources().getStringArray(R.array.car_names_array);
        ArrayAdapter<String> adapter  = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carMakeNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carMakeSpinner.setAdapter(adapter);
    }
}