package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {

    //all buttons
    Button button1, button2, button3, button4;

    // TextViews
    TextView timeView, problemView, scoreView;

    // variables
    int a, b, answer, correctAnswer, totalAnswer, secondLeft;
    boolean allowGame;
    int[] choiceArray = {0,0,0,0};

    ////////// Functional Methods //////////////

    public boolean hasInChoiceArray(int target, int index){
        for(int i = 0; i < 4; i++){
            if(choiceArray[i] == target && index != i)
                return true;
        }
        return false;
    }

    public String formatTime(int s){
        if(s<10)
            return s/60 + ":0" + s%60;
        else
            return s/60 + ":" + s%60;
    }

    ///////////////////////////////////////////

    public void newProblem(){
        a = (int) Math.ceil(Math.random()*30);
        b = (int) Math.ceil(Math.random()*30);
        answer = a + b;

        choiceArray[(int) (Math.random()*4)] = answer;
        for(int i = 0; i < 4; i++) {
            if(choiceArray[i] != answer)
                do {
                    choiceArray[i] = (int) Math.ceil(Math.random() * answer) + answer / 2;
                } while(choiceArray[i] == answer || hasInChoiceArray(choiceArray[i], i));
        }
    }

    public void updateDisplay(){
        //display problem
        problemView.setText(a+" + "+b);
        //display score
        scoreView.setText(correctAnswer+"/"+totalAnswer);
        //display time
        timeView.setText(formatTime(secondLeft));

        //display answers
        button1.setText(Integer.toString(choiceArray[0]));
        button2.setText(Integer.toString(choiceArray[1]));
        button3.setText(Integer.toString(choiceArray[2]));
        button4.setText(Integer.toString(choiceArray[3]));
    }

    public void pickedChoice(View view) {
        Log.i("Button", "It is working");

        // Only allow to pick when game session
        if(allowGame) {

            // Check if selected answer is correct or not, and score them
            int index = Integer.parseInt(view.getTag().toString());
            if (choiceArray[index] == answer)
                correctAnswer += 1;
            totalAnswer += 1;

            // Generate new problem
            newProblem();
            updateDisplay();
        }
    }

    public void reset() {
        correctAnswer = 0;
        totalAnswer = 0;
        secondLeft = 30;
        allowGame = true;
        newProblem();
        updateDisplay();
    }

    public void restartButtonPressed(View view){
        reset();
    }

    public void startTimer(){
        final Handler handler = new Handler();

        Runnable run = new Runnable() {
            @Override
            public void run() {
                //When time runs out
                if(secondLeft == 0) {
                    allowGame = false;
                    
                }

                if(secondLeft >= 0 && allowGame) {
                    //update time display
                    timeView.setText(formatTime(secondLeft));
                    secondLeft -= 1;
                }
                handler.postDelayed(this, 1000);
            }
        };

        // start the runnable function
        handler.post(run);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initizial button
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

        //Initizial TextView
        timeView = (TextView) findViewById(R.id.textTime);
        problemView = (TextView) findViewById(R.id.textProblem);
        scoreView = (TextView) findViewById(R.id.textScore);

        //Initizial Variables and new problem
        reset();
        startTimer();
    }
}