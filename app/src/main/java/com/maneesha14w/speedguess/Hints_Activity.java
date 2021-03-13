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

    private static final ArrayList<String> list = new ArrayList<>();
    private final CommonFunctions cf = new CommonFunctions();
    private short tries = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints_);

        ImageView imgView = findViewById(R.id.car_img_view);
        Button submitBtn = findViewById(R.id.submitBtnHint);
        //setting tag so it isn't null
        cf.setTagToEmpty(submitBtn);
        setImage(imgView); //method that sets an ImageView
        setDashes(imgView); // method that sets the dashes which show the num of letters the user has to guess.
    }

    protected void setImage(ImageView imgView) {
        // use of identify_car_make_activity object to use randomFileName method
        String fileName = cf.randomFileName(imgView);
        imgView.setImageResource(getResources().getIdentifier(fileName, "drawable", getPackageName()));
    }


    private void setDashes(ImageView imgView) {
        TextView dashTextView = findViewById(R.id.dashTextView);
        String correctModel = imgView.getTag().toString(); // the tag has the correct model stored.

        // used a loop to concat underscores
        String finalDash = "";
        for (int i = 0; i < correctModel.length(); i++) {
            if (correctModel.charAt(i) != ' ') {
                finalDash = finalDash.concat(" _ ");
            }
        }

        dashTextView.setText(finalDash);
    }

    public void addCharClick(View view) {
        Button submitBtn = findViewById(R.id.submitBtnHint);
        // if user has completed the whole word
        if (submitBtn.getTag().equals("next")) {
            //restart activity
            Intent intent = new Intent(Hints_Activity.this, Hints_Activity.class);
            cf.resetActivity(intent, view);
        } else {
            EditText editText = findViewById(R.id.charTextBox);
            ImageView imgView = findViewById(R.id.car_img_view);
            TextView textView = findViewById(R.id.dashTextView);


            String correctModel = imgView.getTag().toString();
            String newStr = imgView.getTag().toString(); //copy of correct model
            String enteredChar = editText.getText().toString();

            //checks if the character entered exists in the correct model
            int i = correctModel.indexOf(enteredChar);
            if (i != -1) { //char exists in String
                list.add(enteredChar); //adds char to list
                StringBuilder concatStr = new StringBuilder(); //string builder that combines all elements of the list

                for (String e : list) { //loop through list and append
                    concatStr.append(e);
                }

                newStr = newStr.replaceAll("[^" + concatStr + " ]", " _ "); // the string mirrored in the dash TextView

                if (newStr.equals(correctModel)) { //if user has completed the guess
                    cf.correctAnswer(view, submitBtn); //toast
                    cf.setTagToNext(submitBtn); //setting tag to next
                    editText.setEnabled(false);
                    list.clear(); //clear list for next car
                }

                textView.setText(newStr); //sets text view to the new dashed str
            } else { //char not found in correctModel
                tries--;
                if (tries > 0) {
                    Toast.makeText(this, "Incorrect, " + tries + " tries left", Toast.LENGTH_SHORT).show();
                } else {
                    cf.wrongAnswer(view, false);
                    cf.setTagToNext(submitBtn);// tries over so need to set tag as next
                    editText.setEnabled(false); //disable editText
                    String displayText = "Correct Model: " + cf.capitalize(correctModel);
                    cf.showCorrectAnswer(textView, view, displayText);
                    list.clear(); // clear list for next car
                }
            }
            editText.getText().clear(); //clear editText to remove last submitted text
        }
    }
}