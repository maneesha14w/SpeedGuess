package com.maneesha14w.speedguess;

//Todo REMOVE IMAGE CLICKABLE FUNCTIONALITY

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Advanced_Level_Activity extends AppCompatActivity {
    private final CommonFunctions cf = new CommonFunctions();
    private ArrayList<String> selectedNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced__level);

        setImage();
    }

    public void submitBtnClick(View view) {
    }

    private void setImage() {
        LinearLayout imgSlide = findViewById(R.id.linear_slide);

        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i=1; i<4; i++) {
            View view = inflater.inflate(R.layout.image_and_edit, imgSlide, false);
            ImageView imgView = view.findViewById(R.id.img);
            EditText edit_text = view.findViewById(R.id.edit_text);
            edit_text.setId(i);
            
            String fileName = cf.randomFileName(imgView);;
            while (selectedNames.contains(imgView.getTag().toString())) {
                fileName = cf.randomFileName(imgView);
            }
            selectedNames.add(imgView.getTag().toString());
            imgView.setImageResource(getResources().getIdentifier(fileName, "drawable", getPackageName()));
            imgSlide.addView(view);
        }
    }



    public void imgBtnClicked(View view) {
        Toast.makeText(this, view.getTag().toString() + " " +view.getId(), Toast.LENGTH_SHORT).show();
    }
}