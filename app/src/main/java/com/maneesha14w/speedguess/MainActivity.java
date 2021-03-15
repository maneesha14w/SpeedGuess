package com.maneesha14w.speedguess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private boolean isToggled = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //starts the identify car make activity
    public void carMakeClicked(View view) {
        Intent intent = new Intent(this, Identify_Car_Make_Activity.class);
        intent.putExtra("IS_TOGGLED", isToggled);
        startActivity(intent);
    }

    //starts hint activity
    public void hintBtnClicked(View view) {
        Intent intent = new Intent(this, Hints_Activity.class);
        intent.putExtra("IS_TOGGLED", isToggled);
        startActivity(intent);
    }

    public void carImageBtnClicked(View view) {
        Intent intent = new Intent(this, Identify_Image_Activity.class);
        intent.putExtra("IS_TOGGLED", isToggled);
        startActivity(intent);
    }

    public void advancedBtnClicked(View view) {
        Intent intent = new Intent(this, Advanced_Level_Activity.class);
        intent.putExtra("IS_TOGGLED", isToggled);
        startActivity(intent);
    }


    public void switchToggled(View view) {
        isToggled = true;
    }
}