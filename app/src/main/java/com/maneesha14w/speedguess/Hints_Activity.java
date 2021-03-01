package com.maneesha14w.speedguess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class Hints_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints_);

        setImage();
    }

    private void setImage() {
        ImageView imgView = findViewById(R.id.car_img_view);
        Identify_Car_Make_Activity identifyObj = new Identify_Car_Make_Activity();
        String fileName = identifyObj.randomFileName(imgView);
        imgView.setImageResource( getResources().getIdentifier(fileName, "drawable", getPackageName()));
    }
}