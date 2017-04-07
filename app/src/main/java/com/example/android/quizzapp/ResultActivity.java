package com.example.android.quizzapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;

/**
 * Created by u327610 on 07/04/2017.
 */

public class ResultActivity extends AppCompatActivity{

    private RatingBar resultBar;
    private int score;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Get the rating bar:
        resultBar = (RatingBar) findViewById(R.id.ratingBar);

        //get the score from the intent:
        score = getIntent().getExtras().getInt("score");

        //Fulfill the rating bar:
        resultBar.setNumStars(score);

    }


}
