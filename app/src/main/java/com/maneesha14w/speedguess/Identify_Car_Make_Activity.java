package com.maneesha14w.speedguess;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Identify_Car_Make_Activity extends AppCompatActivity {
    //array that stores the name of cars that correspond to the first part of the image file can later be appended for more brands
    private final CommonFunctions cf = new CommonFunctions();
    private TextView timer_tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify__car__make_);

        // getting and setting the image view to a random img
        ImageView imgView = findViewById(R.id.car_img_view);
        String randomFileName = cf.randomFileName(imgView); //random file name
        imgView.setImageResource(getResources().getIdentifier(randomFileName, "drawable", getPackageName()));

        //setting tag for btn to check if next btn has been activated
        Button identify_btn = findViewById(R.id.identify_btn);
        cf.setTagToEmpty(identify_btn);

        //init spinner
        spinnerSetter();

        timer_tv = findViewById(R.id.timer_tv);

        Bundle bundle = getIntent().getExtras();
        boolean myBooleanVariable = bundle.getBoolean("IS_TOGGLED");

        if (myBooleanVariable) {

            new CountDownTimer(20000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timer_tv.setText(String.valueOf("Time: " + millisUntilFinished / 1000));
                    if (millisUntilFinished < 10000) {
                        timer_tv.setTextColor(getResources().getColor(R.color.light_red));

                    }
                }

                @Override
                public void onFinish() {
                    timer_tv.setText(R.string.times_up_txt);
                    Button identify_btn = findViewById(R.id.identify_btn);

                    Spinner carMakeSpinner = findViewById(R.id.carMakeSpinner);
                    String selectedModel = carMakeSpinner.getSelectedItem().toString();

                    // get the correct answer
                    ImageView imgView = findViewById(R.id.car_img_view);
                    String correctModel = imgView.getTag().toString();

                    if (selectedModel.equals("Select Model")) {
                        Toast.makeText(Identify_Car_Make_Activity.this, "Please Select a Proper Model", Toast.LENGTH_SHORT).show();
                    } else if (selectedModel.toLowerCase().equals(correctModel)) {
                        //if the correct option has been chosen
                        cf.correctAnswerCarMake(Identify_Car_Make_Activity.this, identify_btn);
                        carMakeSpinner.setEnabled(false);
                    } else {
                        // incorrect options has been chosen
                        cf.wrongAnswerCarMake(Identify_Car_Make_Activity.this);
                        // displaying the correct model
                        TextView correct_text = findViewById(R.id.correct_txt_view);
                        String displayText = "Correct Model: " + (correctModel.substring(0, 1).toUpperCase() + correctModel.substring(1));
                        correct_text.setText(displayText);
                        // added a delay and removed the textView.
                        correct_text.postDelayed(() -> correct_text.setVisibility(View.GONE), 3500);
                        carMakeSpinner.setEnabled(false);
                        cf.setTagToNext(identify_btn);
                    }
                }
            }.start();
        }
    }

    //initialises spinner
    private void spinnerSetter() {
        Spinner carMakeSpinner = findViewById(R.id.carMakeSpinner); //get id of spinner

        String[] carMakeNames = getResources().getStringArray(R.array.car_names_array); //array to hold names of models

        // adapter draws spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carMakeNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //sets resource of a string array in strings.xml
        carMakeSpinner.setAdapter(adapter); //setting the adapter to the spinner
    }

    // function for when identify is clicked
    public void identifyBtnClick(View view) {
        Button identify_btn = findViewById(R.id.identify_btn);

        // check btn tag if user has guessed correct answer
        if (identify_btn.getTag().equals("next")) {
            Intent intent = new Intent(Identify_Car_Make_Activity.this, Identify_Car_Make_Activity.class);
            cf.resetActivity(intent, view);
        } else { //first time activity is being run

            //get the item currently in spinner
            Spinner carMakeSpinner = findViewById(R.id.carMakeSpinner);
            String selectedModel = carMakeSpinner.getSelectedItem().toString();

            // get the correct answer
            ImageView imgView = findViewById(R.id.car_img_view);
            String correctModel = imgView.getTag().toString();

            // if Select model has been selected.
            if (selectedModel.equals("Select Model")) {
                Toast.makeText(this, "Please Select a Proper Model", Toast.LENGTH_SHORT).show();
            } else if (selectedModel.toLowerCase().equals(correctModel)) {
                //if the correct option has been chosen
                cf.correctAnswer(view, identify_btn);
                carMakeSpinner.setEnabled(false);
            } else {
                // incorrect options has been chosen
                cf.wrongAnswer(view, false);
                // displaying the correct model
                TextView correct_text = findViewById(R.id.correct_txt_view);
                String displayText = "Correct Model: " + (correctModel.substring(0, 1).toUpperCase() + correctModel.substring(1));
                correct_text.setText(displayText);
                // added a delay and removed the textView.
                correct_text.postDelayed(() -> correct_text.setVisibility(View.GONE), 3500);
                carMakeSpinner.setEnabled(false);
                cf.setTagToNext(identify_btn);
            }
        }
    }
}