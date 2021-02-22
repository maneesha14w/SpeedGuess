package com.maneesha14w.speedguess;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String btn_click_text = "BTNCLICK"   ;

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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.car_make_btn:
                Log.d(btn_click_text, "identify car make");
                car_make_click();
                break;
            case R.id.hints_btn:
                Log.d(btn_click_text, "Hint btn");
                break;
            case R.id.car_image_btn:
                Log.d(btn_click_text, "Car image Btn");
                break;
            case R.id.advanced_level_btn:
                Log.d(btn_click_text, "Advanced level btn");
                break;
        }
    }

    private void car_make_click() {
        Intent intent = new Intent(this, Identify_Car_Make_Activity.class);
        startActivity(intent);
    }
}