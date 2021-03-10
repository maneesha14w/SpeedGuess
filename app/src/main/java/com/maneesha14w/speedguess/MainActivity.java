package com.maneesha14w.speedguess;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final CommonFunctions cf = new CommonFunctions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //starts the identify car make activity
    public void carMakeClicked(View view) {
        Intent intent = new Intent(this, Identify_Car_Make_Activity.class);
        startActivity(intent);
    }

    //starts hint activity
    public void hintBtnClicked(View view) {
        Intent intent = new Intent(this, Hints_Activity.class);
        startActivity(intent);
    }

    public void carImageBtnClicked(View view) {
        Intent intent = new Intent(this, Identify_Image_Activity.class);
        startActivity(intent);
    }

    public void advancedBtnClicked(View view) {
        Log.d("BTN_CLICK", "Advanced level btn clicked");
    }




}