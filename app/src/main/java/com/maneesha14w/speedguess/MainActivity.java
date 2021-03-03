package com.maneesha14w.speedguess;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init buttons
        Button car_make_btn = findViewById(R.id.car_make_btn);
        Button hints_btn = findViewById(R.id.hints_btn);
        Button car_image_btn = findViewById(R.id.car_image_btn);
        Button advanced_lvl_btn = findViewById(R.id.advanced_level_btn);

        //set onClicks
        car_make_btn.setOnClickListener(this);
        hints_btn.setOnClickListener(this);
        car_image_btn.setOnClickListener(this);
        advanced_lvl_btn.setOnClickListener(this);
    }

    //any click is passed through here
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.car_make_btn:
                carMakeClicked();
                break;
            case R.id.hints_btn:
                hintBtnClicked();
                break;
            case R.id.car_image_btn:
                carImageBtnClicked();
                break;
            case R.id.advanced_level_btn:
                advancedBtnClicked();
                break;
        }
    }

    //starts the identify car make activity
    private void carMakeClicked() {
        Intent intent = new Intent(this, Identify_Car_Make_Activity.class);
        startActivity(intent);
    }

    //starts hint activity
    private void hintBtnClicked() {
        Intent intent = new Intent(this, Hints_Activity.class);
        startActivity(intent);
    }

    private void carImageBtnClicked() {
        Intent intent = new Intent(this, Identify_Image_Activity.class);
        startActivity(intent);
    }

    private  void advancedBtnClicked() {
        Log.d("BTN_CLICK", "Advanced level btn clicked");
    }

}