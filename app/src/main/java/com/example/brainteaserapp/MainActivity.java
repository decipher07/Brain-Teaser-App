package com.example.brainteaserapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton ;
    TextView sumTextView ;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectLocation;
    TextView resultTextView ;
    int score = 0 ;
    int numberOfQuestions = 0 ;
    TextView scoreTextView ;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timerTextView ;
    Button playAgainButton;
    ConstraintLayout gameLayout ;

    public void playAgain (View view){
        score = 0 ;
        numberOfQuestions = 0 ;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        new CountDownTimer(30000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done");
                playAgainButton.setVisibility(View.VISIBLE);
                gameLayout.setVisibility(View.INVISIBLE);
            }
        }.start();

    }

    public void chooseAnswer (View view) {

        if (Integer.toString(locationOfCorrectLocation).equals(view.getTag().toString())){
                resultTextView.setText("Correct!");
                score++;
        }
        else
            resultTextView.setText("Wrong!");
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    public void newQuestion (){

        Random rand = new Random ();

        int randomNumberElementOne = rand.nextInt(21);
        int randomNumberElementTwo = rand.nextInt(21);

        sumTextView.setText(Integer.toString(randomNumberElementOne) + " + " + Integer.toString(randomNumberElementTwo));

        locationOfCorrectLocation = rand.nextInt(4);

        answers.clear();

        for (int i = 0 ; i < 4 ; i++){
            if (i == locationOfCorrectLocation)
                answers.add(randomNumberElementOne+randomNumberElementTwo);
            else {
                int wrongAnswer = rand.nextInt(41);
                if (wrongAnswer == randomNumberElementOne+randomNumberElementTwo)
                    wrongAnswer = rand.nextInt(41);
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        sumTextView = (TextView)  findViewById(R.id.sumTextView);
        goButton = (Button) findViewById(R.id.goButton);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.Button7);
        gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}
