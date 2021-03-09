package com.maneesha14w.speedguess;

import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;

import java.util.Random;

public class CommonFunctions {

    private final String[] carNames = {"ford", "mercedes", "tesla"};

    // method that returns string of a random file name and sets tag of passed ImageView to the car selected
    protected String randomFileName(ImageView imgView) {
        Random rand = new Random();
        String carName = carNames[rand.nextInt(3)]; // random index from car names array
        String fileNum = String.valueOf(rand.nextInt(10) + 1); //Random car number

        imgView.setTag(carName); //setting the tag of the ImageView to whatever make of car is selected
        return  carName + "_" + fileNum; //string that corresponds to a random image file
    }



}
