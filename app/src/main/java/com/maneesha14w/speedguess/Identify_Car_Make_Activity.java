package com.maneesha14w.speedguess;

//TODO cleanup toasts, implement better design (material?)

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Identify_Car_Make_Activity extends AppCompatActivity {
    //array that stores the name of cars that correspond to the first part of the image file can later be appended for more brands
    private final String[] carNames = {"ford", "mercedes", "tesla"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify__car__make_);

        // getting and setting the image view to a random img
        ImageView imgView = findViewById(R.id.car_img_view);
        String randomFileName = randomFileName(imgView); //random file name
        imgView.setImageResource(getResources().getIdentifier(randomFileName, "drawable", getPackageName()));

        //setting tag for btn to check if next btn has been activated
        Button identify_btn = findViewById(R.id.identify_btn);
        identify_btn.setTag("");

        //init spinner
        spinnerSetter();
    }

    // method that returns string of a random file name
    protected String randomFileName(ImageView imgView) {
        Random rand = new Random();
        String carName = carNames[rand.nextInt(3)]; // random index from car names array
        String fileNum = String.valueOf(rand.nextInt(10) + 1); //Random car number

        imgView.setTag(carName); //setting the tag of the ImageView to whatever make of car is selected
        return  carName + "_" + fileNum; //string that corresponds to a random image file
    }

    //initialises spinner
    private void spinnerSetter() {
        Spinner carMakeSpinner = findViewById(R.id.carMakeSpinner); //get id of spinner

        String [] carMakeNames = getResources().getStringArray(R.array.car_names_array); //array to hold names of models

        // adapter draws spinner
        ArrayAdapter<String> adapter  = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carMakeNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //sets resource of a string array in strings.xml
        carMakeSpinner.setAdapter(adapter); //setting the adapter to the spinner
    }

    // function for when identify is clicked
    public void identifyBtnClick(View view) {
        Button identify_btn = findViewById(R.id.identify_btn);

        // check btn tag if user has guessed correct answer
        if (identify_btn.getTag().equals("next")){
            Intent intent = new Intent(Identify_Car_Make_Activity.this, Identify_Car_Make_Activity.class);
            finish();
            overridePendingTransition(1, 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
        else { //first time activity is being run

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
                Toast toast = Toast.makeText(this, "CORRECT!", Toast.LENGTH_SHORT);
                toast.getView().setBackgroundColor(getResources().getColor(R.color.green));
                toast.show();
                //btn changes
                identify_btn.setText(R.string.next_btn_txt);
                identify_btn.setBackgroundColor(getResources().getColor(R.color.green));
                identify_btn.setTag("next");
                carMakeSpinner.setEnabled(false);
            } else {
                // incorrect options has been chosen
                Toast toast = Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT);
                toast.getView().setBackgroundColor(getResources().getColor(R.color.light_red));
                toast.show();

                // displaying the correct model
                TextView correct_text = findViewById(R.id.correct_txt_view);
                String displayText = "Correct Model: " + (correctModel.substring(0,1).toUpperCase() + correctModel.substring(1));
                correct_text.setText(displayText);
                // added a delay and removed the textView.
                correct_text.postDelayed(new Runnable(){
                    @Override
                    public void run()
                    {
                        correct_text.setVisibility(View.GONE);
                    }
                }, 3500);
            }
        }
    }
}