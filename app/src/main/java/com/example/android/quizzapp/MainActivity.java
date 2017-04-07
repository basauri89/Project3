package com.example.android.quizzapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Define the questions Radio Groups:
    private RadioGroup question1;
    private RadioGroup question2;
    private RadioGroup question3;
    private RadioGroup question4;
    private RadioGroup question5;
    private int nrQuestions;

    //Answers arrays:
    private int[] answers = null;
    public int[] userAnswers = null;

    //score
    public int totScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //game settings:
        nrQuestions = 5;
        answers = new int[nrQuestions];
        userAnswers = new int[nrQuestions];

        //set the answers:
        answers[0] = 3;
        answers[1] = 5;
        answers[2] = 9;
        answers[3] = 10;
        answers[4] = 14;

        //Initialize the questions groups:
        question1 = (RadioGroup) findViewById(R.id.question1);
        question2 = (RadioGroup) findViewById(R.id.question2);
        question3 = (RadioGroup) findViewById(R.id.question3);
        question4 = (RadioGroup) findViewById(R.id.question4);
        question5 = (RadioGroup) findViewById(R.id.question5);


    }

    /**
     * This is the main submit method called when a user submits its answers.
     * @param view View from the layout.
     */

    public void submitAnswers(View view){

        if (checkForm()){

            //calculate the score:
            totScore = calculateScore();


            //create the toast message with the score:

            String msg = "Congratulations! your score is "+totScore+"/5";

            //if not 5 out of 5:
            if (totScore < 5){
                msg += "\n Keep trying until you make it perfect ;)";
            }
            createToast(msg);

            return;

        }


        //Show a toast message whe not all questions have been answered.

        createToast("Please answer every question before submitting");

        //Jump to final activity:
        startActivity();

    }

    /**
     * A simple method in order to check that all of the questions have been answered.
     * Besides, we will store users checked buttons in the userAnswers array.
     * @return a boolean which will determine whether all the questions have been answered or not.
     */

    public boolean checkForm(){

        int ans1 = question1.getCheckedRadioButtonId();
        int ans2 = question2.getCheckedRadioButtonId();
        int ans3 = question3.getCheckedRadioButtonId();
        int ans4 = question4.getCheckedRadioButtonId();
        int ans5 = question5.getCheckedRadioButtonId();


        if (ans1==-1 || ans2==-1 || ans3==-1 || ans4==-1 || ans5==-1){
            return false;
        }

        //initialize the user answers array
        userAnswers[0] = ans1;
        userAnswers[1] = ans2;
        userAnswers[2] = ans3;
        userAnswers[3] = ans4;
        userAnswers[4] = ans5;

        //return to end the method
        return true;
    }

    /**
     * This method will create a toast message on the user Screen for a fixed period of time.
     * @param message this is the message it will show in the toast message
     */

    public void createToast(String message){
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     * This method will calculate the score based on the user's answers
     * @return you will get a score in return.
     */
    public int calculateScore(){
        int score = 0;

        //compare the answers from the user with the correct ones:
        for (int i=0; i<nrQuestions; i++){
            //compare
            if (userAnswers[i] == answers[i]){
                score++;
            }
        }

        return score;
    }

    public void startActivity() {
        Intent resultScreen = new Intent(MainActivity.this, ResultActivity.class);
        resultScreen.putExtra("score",totScore);
        startActivity(resultScreen);
    }
}

