package com.maneesha14w.speedguess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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

    public void addCharClick(View view) {
        EditText editText = findViewById(R.id.charTextBox);
        ImageView imgView = findViewById(R.id.car_img_view);
        TextView textView = findViewById(R.id.dashTextView);

        ArrayList<String> list = new ArrayList<>();

        String newStr = imgView.getTag().toString();
        String enteredChar = editText.getText().toString();

        list.add(enteredChar);

        for (String e : list){
            newStr = newStr.replaceAll("[^" + e + "]", " _ ");
        }

        Log.d("LOG", newStr);
        editText.getText().clear();
        textView.setText(newStr);

    }
}