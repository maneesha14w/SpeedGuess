package com.maneesha14w.speedguess;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class CommonFunctions {

    private final String[] carNames = {"ford", "mercedes", "tesla"};

    // method that returns string of a random file name and sets tag of passed ImageView to the car selected
    protected String randomFileName(ImageView imgView) {
        Random rand = new Random();
        String carName = carNames[rand.nextInt(3)]; // random index from car names array
        String fileNum = String.valueOf(rand.nextInt(10) + 1); //Random car number

        imgView.setTag(carName); //setting the tag of the ImageView to whatever make of car is selected
        return carName + "_" + fileNum; //string that corresponds to a random image file
    }


    public void setTagToEmpty(Button btn) {
        btn.setTag("");
    }

    public void setTagToNext(Button btn) {
        btn.setText(R.string.next_btn_txt);
        btn.setTag("next");
    }


    public void resetActivity(Intent intent, View view) {
        ((Activity) view.getContext()).finish();
        ((Activity) view.getContext()).overridePendingTransition(1, 0);
        view.getContext().startActivity(intent);
        ((Activity) view.getContext()).overridePendingTransition(0, 0);
    }

    public void correctAnswer(View view, Button btn) {
        Toast toast = Toast.makeText((Activity) view.getContext(), "CORRECT!", Toast.LENGTH_SHORT);
        toast.getView().setBackgroundColor(view.getResources().getColor(R.color.green));
        toast.show();
        setTagToNext(btn);
    }

    public void wrongAnswer(View view,  boolean ignoreError) {
        if (!ignoreError) {
            Toast toast = Toast.makeText((Activity) view.getContext(), "INCORRECT!", Toast.LENGTH_SHORT);
            toast.getView().setBackgroundColor(view.getResources().getColor(R.color.light_red));
            toast.show();
        }
        else {
            System.out.println("ignore error true");
        }
    }

    public void showCorrectAnswer(TextView textView, View view, String displayText) {
        textView.setTextColor(view.getResources().getColor(R.color.yellow));
        textView.setText(displayText);
    }

    public String randomness(){


        return "";
    }
}
