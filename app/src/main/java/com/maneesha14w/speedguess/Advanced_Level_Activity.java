package com.maneesha14w.speedguess;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Advanced_Level_Activity extends AppCompatActivity {
    private final CommonFunctions cf = new CommonFunctions();
    private ArrayList<String> selectedNames = new ArrayList<>();
    private short score = 0;
    private short tries = 3;
    private boolean isCorrect_1, isCorrect_2, isCorrect_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced__level);
        Button submitBtn = findViewById(R.id.submitBtn);
        cf.setTagToEmpty(submitBtn);
        setImage();
    }

    private void setImage() {
        LinearLayout imgSlide = findViewById(R.id.linear_slide);

        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 1; i < 4; i++) {
            View view = inflater.inflate(R.layout.image_and_edit, imgSlide, false);
            ImageView imgView = view.findViewById(R.id.img);
            EditText edit_text = view.findViewById(R.id.edit_text);
            edit_text.setId(i);

            String fileName = cf.randomFileName(imgView);

            while (selectedNames.contains(imgView.getTag().toString())) {
                fileName = cf.randomFileName(imgView);
            }

            selectedNames.add(imgView.getTag().toString());
            if (i == 1) {
                imgView.setId(R.id.Img_1);
                edit_text.setId(R.id.Edit_1);
            } else if (i == 2) {
                imgView.setId(R.id.Img_2);
                edit_text.setId(R.id.Edit_2);
            } else {
                imgView.setId(R.id.Img_3);
                edit_text.setId(R.id.Edit_3);
            }

            imgView.setImageResource(getResources().getIdentifier(fileName, "drawable", getPackageName()));
            imgSlide.addView(view);
        }
    }

    public void submitBtnClick(View view) {
        tries--;

        Button submitBtn = findViewById(R.id.submitBtn);
        if (submitBtn.getTag().equals("next")) {
            Intent intent = new Intent(Advanced_Level_Activity.this, Advanced_Level_Activity.class);
            cf.resetActivity(intent, view);
        } else {
            ImageView img_1 = findViewById(R.id.Img_1);
            ImageView img_2 = findViewById(R.id.Img_2);
            ImageView img_3 = findViewById(R.id.Img_3);
            String correct_1 = img_1.getTag().toString();
            String correct_2 = img_2.getTag().toString();
            String correct_3 = img_3.getTag().toString();
            EditText edt_1 = findViewById(R.id.Edit_1);
            EditText edt_2 = findViewById(R.id.Edit_2);
            EditText edt_3 = findViewById(R.id.Edit_3);
            String e1_msg = edt_1.getText().toString().toLowerCase();
            String e2_msg = edt_2.getText().toString().toLowerCase();
            String e3_msg = edt_3.getText().toString().toLowerCase();

            if (tries == 0 && (!isCorrect_1 || !isCorrect_2 || !isCorrect_3)) {
                cf.wrongAnswer(view, false);
                showAnswer(correct_1, edt_1);
                showAnswer(correct_2, edt_2);
                showAnswer(correct_3, edt_3);
                cf.setTagToNext(submitBtn);
            } else {

                if (e1_msg.equals("") || e2_msg.equals("") || e3_msg.equals("")) {
                    Toast.makeText(this, "Please Fill in all Text Boxes", Toast.LENGTH_SHORT).show();
                } else {
                    isCorrect_1 = isCorrect(edt_1, e1_msg, correct_1);
                    isCorrect_2 = isCorrect(edt_2, e2_msg, correct_2);
                    isCorrect_3 = isCorrect(edt_3, e3_msg, correct_3);
                }

                TextView score_tv = findViewById(R.id.score_text_view);
                if (isCorrect_1 && isCorrect_2 && isCorrect_3) {
                    cf.correctAnswer(view, submitBtn);
                    score = 3;
                    cf.setTagToNext(submitBtn);
                }

                String scoreText = getString(R.string.score_text) + " " + String.valueOf(score);
                score_tv.setText(scoreText);
            }
        }
    }

    private void showAnswer(String correct_text, EditText edt_text) {
        edt_text.setText(cf.capitalize(correct_text));
        edt_text.setTextColor(getResources().getColor(R.color.yellow));
        edt_text.setEnabled(false);
    }

    private boolean isCorrect(EditText edt, String e_msg, String correct) {
        if (e_msg.equals(correct)) {
            correctAnswer(edt);
            return true;
        } else {
            wrongAnswer(edt);
            return false;
        }
    }

    private void wrongAnswer(EditText edt) {
        edt.setTextColor(getResources().getColor(R.color.red));
    }

    private void correctAnswer(EditText edt) {
        edt.setEnabled(false);
        edt.setTextColor(getResources().getColor(R.color.green));
        score++;
    }


}