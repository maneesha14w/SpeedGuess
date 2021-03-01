package com.maneesha14w.speedguess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Hints_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints_);

        ImageView imgView = findViewById(R.id.car_img_view);
        setImage(imgView);
        setDashes(imgView);
    }

    private void setImage(ImageView imgView) {
        Identify_Car_Make_Activity identifyObj = new Identify_Car_Make_Activity();
        String fileName = identifyObj.randomFileName(imgView);
        imgView.setImageResource( getResources().getIdentifier(fileName, "drawable", getPackageName()));
    }


    private void setDashes(ImageView imgView) {
        TextView dashTextView = findViewById(R.id.dashTextView);
        String correctModel = imgView.getTag().toString();

        String finalDash="";
        for (int i = 0; i < correctModel.length(); i++) {
            if (correctModel.charAt(i) != ' '){
                finalDash = finalDash.concat(" _ ");
            }
        }

        dashTextView.setText(finalDash);
    }

}