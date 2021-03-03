package com.maneesha14w.speedguess;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Identify_Image_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify__image_);

        LinearLayout gallery = findViewById(R.id.gallery);
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i <=3; i++) {

            View view = inflater.inflate(R.layout.car, gallery, false);

            ImageView i_1 = view.findViewById(R.id.carImageView);
            i_1.setImageResource(R.drawable.mercedes_1);
        }
    }


}