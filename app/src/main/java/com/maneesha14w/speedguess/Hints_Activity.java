package com.maneesha14w.speedguess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Hints_Activity extends AppCompatActivity {

    private static ArrayList<String> list = new ArrayList<>();
    private short tries = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints_);

        ImageView imgView = findViewById(R.id.car_img_view);
        Button submitBtn = findViewById(R.id.submitBtnHint);
        submitBtn.setTag("");
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
        Button submitBtn = findViewById(R.id.submitBtnHint);
        if (submitBtn.getTag().equals("next")) {
            Intent intent = new Intent(Hints_Activity.this, Hints_Activity.class);
            finish();
            overridePendingTransition(1, 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
        } else {
            EditText editText = findViewById(R.id.charTextBox);
            ImageView imgView = findViewById(R.id.car_img_view);
            TextView textView = findViewById(R.id.dashTextView);


            String correctModel = imgView.getTag().toString();
            String newStr = imgView.getTag().toString();
            String enteredChar = editText.getText().toString();

            int i = correctModel.indexOf(enteredChar);
            if (i != -1) {
                list.add(enteredChar);
                StringBuilder concatStr = new StringBuilder();

                for (String e : list) {
                    concatStr.append(e);
                }

                newStr = newStr.replaceAll("[^" + concatStr + " ]", " _ ");
                if (newStr.equals(correctModel)) {
                    Toast toast = Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT);
                    toast.getView().setBackgroundColor(getResources().getColor(R.color.green));
                    toast.show();

                    submitBtn.setText(R.string.next_btn_txt);
                    submitBtn.setTag("next");
                    editText.setEnabled(false);
                }

                textView.setText(newStr);
            } else {
                tries--;
                if (tries > 0) {
                    Toast.makeText(this, "Incorrect, " +  tries + " tries left", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "3 tries over", Toast.LENGTH_SHORT).show();
                    submitBtn.setText(R.string.next_btn_txt);
                    submitBtn.setTag("next");
                    editText.setEnabled(false);
                    String displayText = "Correct Model: " + (correctModel.substring(0,1).toUpperCase() + correctModel.substring(1));
                    textView.setTextColor(getResources().getColor(R.color.yellow));
                    textView.setBackgroundColor(getResources().getColor(R.color.blue));
                    textView.setText(displayText);
                }
            }
            editText.getText().clear();
        }
    }
}