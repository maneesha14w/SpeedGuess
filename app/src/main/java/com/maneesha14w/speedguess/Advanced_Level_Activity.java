package com.maneesha14w.speedguess;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Advanced_Level_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced__level);

        LinearLayout imgSlide = findViewById(R.id.linear_slide);

        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i < 3; i++) {
            View view = inflater.inflate(R.layout.image_and_edit, imgSlide, false);

            ImageView imgView = view.findViewById(R.id.img);
            imgView.setImageResource(R.drawable.tesla_1);

            imgSlide.addView(view);
        }


    }

    public void submitBtnClick(View view) {
    }
}