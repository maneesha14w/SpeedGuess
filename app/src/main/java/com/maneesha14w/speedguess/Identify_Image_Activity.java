package com.maneesha14w.speedguess;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Identify_Image_Activity extends AppCompatActivity {
    private String chosenName = "";
    private View lastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify__image_);

        ImageView img_1 = findViewById(R.id.img_1);
        ImageView img_2 = findViewById(R.id.img_2);
        ImageView img_3 = findViewById(R.id.img_3);
        TextView textView = findViewById(R.id.textView);
        HorizontalScrollView horizontalScrollView = findViewById(R.id.horizontalScrollView);

        setup(img_1, img_2, img_3, textView);

    }

    private void setup(ImageView img_1, ImageView img_2, ImageView img_3, TextView textView) {
        Identify_Car_Make_Activity identifyObj = new Identify_Car_Make_Activity();
        String fileName_1 = identifyObj.randomFileName(img_1);
        String fileName_2 = identifyObj.randomFileName(img_2);
        String fileName_3 = identifyObj.randomFileName(img_3);
        img_1.setImageResource( getResources().getIdentifier(fileName_1, "drawable", getPackageName()));
        img_2.setImageResource( getResources().getIdentifier(fileName_2, "drawable", getPackageName()));
        img_3.setImageResource( getResources().getIdentifier(fileName_3, "drawable", getPackageName()));


        String [] carFileNames = {fileName_1, fileName_2, fileName_3};
        Random random = new Random();
        String chosenFileName = carFileNames[random.nextInt(3)];
        chosenName = (chosenFileName.substring(chosenFileName.indexOf("_", chosenFileName.indexOf("_") + 1) + 1, chosenFileName.lastIndexOf("_")));

        textView.setText(chosenName.toUpperCase());
    }


    public void imageBtnClick(View view) {
        if (lastView!=null) {
            lastView.setBackgroundColor(Color.GRAY);
        }

        view.setBackgroundColor(getResources().getColor(R.color.green));
        lastView = view;
    }

    public void submitBtnClick(View view) {
        if (lastView == null) {
            Toast.makeText(this, "Please pick an image and submit!", Toast.LENGTH_SHORT).show();
        }
        else if (lastView.getTag() == chosenName){
            Toast.makeText(this, lastView.getTag().toString() + " " + chosenName, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, lastView.getTag().toString() + " " + chosenName, Toast.LENGTH_SHORT).show();
        }
    }
}