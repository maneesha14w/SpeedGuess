package com.maneesha14w.speedguess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.Random;

public class Identify_Car_Make_Activity extends AppCompatActivity {
    private final String[] carNames = {"ford", "mercedes", "tesla"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify__car__make_);

        ImageView imgView = findViewById(R.id.car_img_view);
        imgView.setImageResource(getRandomImageResource());
    }

    private int getRandomImageResource() {
        Random rand = new Random();
        String fileNum = String.valueOf(rand.nextInt(10) + 1);
        int carNameNum = rand.nextInt(3);
        String fileName = carNames[carNameNum] + "_" + fileNum;
        return getResources().getIdentifier(fileName, "drawable", getPackageName());
    }
}