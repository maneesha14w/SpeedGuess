package com.maneesha14w.speedguess;

//TODO optimise randomising by using AsyncTask

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

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
        imgView.setImageResource(getRandomImageResource());
    }

    //method that returns int id of a random image
    private int getRandomImageResource() {
        Random rand = new Random();
        String fileNum = String.valueOf(rand.nextInt(10) + 1); //Random car number
        int carNameNum = rand.nextInt(3); // random index from car names array
        String fileName = carNames[carNameNum] + "_" + fileNum; //string that corresponds to a random image file
        Log.d("FILE_NAME", fileName); // log to check if any errors with any file
        return getResources().getIdentifier(fileName, "drawable", getPackageName()); // returns an int id of the filename
    }
}