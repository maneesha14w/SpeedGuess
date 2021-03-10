package com.maneesha14w.speedguess;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Identify_Image_Activity extends AppCompatActivity {
    private String chosenName = "";
    private View lastView;
    private ImageView img_1, img_2, img_3;
    private String fileName_1, fileName_2, fileName_3;
    private final CommonFunctions cf = new CommonFunctions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify__image_);

        //init
        Button submitBtn = findViewById(R.id.submitBtn);

        img_1 = findViewById(R.id.img_1);
        img_2 = findViewById(R.id.img_2);
        img_3 = findViewById(R.id.img_3);
        TextView textView = findViewById(R.id.textView);

        cf.setTagToEmpty(submitBtn);
        setup(textView); //setups the images and the model that is to be guessed

    }

    private void setup(TextView textView) {
        //get random filenames an tag set on imageView
        fileName_1 = cf.randomFileName(img_1);
        fileName_2 = cf.randomFileName(img_2);
        fileName_3 = cf.randomFileName(img_3);
        
        randomnessCheck(); //checks if all the

        img_1.setImageResource( getResources().getIdentifier(fileName_1, "drawable", getPackageName()));
        img_2.setImageResource( getResources().getIdentifier(fileName_2, "drawable", getPackageName()));
        img_3.setImageResource( getResources().getIdentifier(fileName_3, "drawable", getPackageName()));


        String [] carFileNames = {fileName_1, fileName_2, fileName_3}; //arr for selecting a random correct answer
        Random random = new Random();
        String chosenFileName = carFileNames[random.nextInt(3)];
        chosenName = (chosenFileName.substring(chosenFileName.indexOf("_", chosenFileName.indexOf("_") + 1) + 1, chosenFileName.lastIndexOf("_")));

        textView.setText(chosenName.toUpperCase());
    }

    private void randomnessCheck() {
        while (img_1.getTag() == img_2.getTag() || img_1.getTag() == img_3.getTag() || img_2.getTag() == img_3.getTag()) {
            if (img_1.getTag() == img_2.getTag()){ fileName_1 = cf.randomFileName(img_1); }
            if (img_2.getTag() == img_3.getTag()) { fileName_2 = cf.randomFileName(img_2); }
            if (img_3.getTag() == img_1.getTag()) {fileName_3 = cf.randomFileName(img_3);}
        }
    }


    public void imageBtnClick(View view) {
        if (lastView!=null) {
            lastView.setBackgroundColor(Color.GRAY);
        }
        view.setBackgroundColor(getResources().getColor(R.color.green));
        lastView = view;
    }

    public void submitBtnClick(View view) {
        Button submitBtn = findViewById(R.id.submitBtn);
        if (submitBtn.getTag().equals("next")) {
            Intent intent = new Intent(Identify_Image_Activity.this, Identify_Image_Activity.class);
            cf.resetActivity(intent, view);
        }
        else {
            if (lastView == null) { //no image has been selected
                Toast.makeText(this, "Please pick an image and submit!", Toast.LENGTH_SHORT).show();
            } else if (lastView.getTag().equals(chosenName)) { //
                cf.correctAnswer(view, submitBtn);
            } else {
                cf.wrongAnswer(view, false);
            }
            cf.setTagToNext(submitBtn);
        }
    }
}