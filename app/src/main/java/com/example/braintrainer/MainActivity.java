package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    int[] choiceArray = {0,0,0,0};

    public boolean hasInChoiceArray(int target, int index){
        for(int i = 0; i < 4; i++){
            if(choiceArray[i] == target && index != i)
                return true;
        }
        return false;
    }

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
        problemView.setText(a+" + "+b);

        scoreView.setText(correctAnswer+"/"+totalAnswer);

        button1.setText(Integer.toString(choiceArray[0]));
        button2.setText(Integer.toString(choiceArray[1]));
        button3.setText(Integer.toString(choiceArray[2]));
        button4.setText(Integer.toString(choiceArray[3]));
    }

    public void pickedChoice(View view) {
        Log.i("Button", "It is working");

        // Check if selected answer is correct or not, and score them
        int index = Integer.parseInt(view.getTag().toString());
        if(choiceArray[index] == answer)
            correctAnswer += 1;
        totalAnswer += 1;

        // Generate new problem
        newProblem();
        updateDisplay();
    }

    public void reset() {
        correctAnswer = 0;
        totalAnswer = 0;
        secondLeft = 0;
        newProblem();
        updateDisplay();
    }

    public void restartButtonPressed(View view){
        reset();
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
    }
}