package com.francescocervone.openratingview.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.francescocervone.openratingview.RatingView;


public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        RatingView ratingView = findViewById(R.id.rating_view);
        ratingView.setOnStarClickListener(position -> Log.d("simpleratingview", "Clicked star " + position));
    }
}
