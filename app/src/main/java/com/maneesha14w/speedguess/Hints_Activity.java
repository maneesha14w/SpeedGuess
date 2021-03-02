package com.maneesha14w.speedguess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Hints_Activity extends AppCompatActivity {

    private static ArrayList<String> list = new ArrayList<>();
    private short tries = 0;

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

    public void addCharClick(View view) {
        EditText editText = findViewById(R.id.charTextBox);
        ImageView imgView = findViewById(R.id.car_img_view);
        TextView textView = findViewById(R.id.dashTextView);
        Button submitBtn = findViewById(R.id.identify_btn);

        String correctModel = imgView.getTag().toString();
        String newStr = imgView.getTag().toString();
        String enteredChar = editText.getText().toString();

        int i = correctModel.indexOf(enteredChar);
        if (i != -1) {
            list.add(enteredChar);
            String concatStr = "";

            for (String e : list) {
                concatStr = concatStr + e;
            }

            newStr = newStr.replaceAll("[^" + concatStr + " ]", " _ ");
            if (newStr.equals(correctModel)) {
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            }
            textView.setText(newStr);
        }
        else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}