package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //all buttons
    Button button1, button2, button3, button4;

    // TextViews
    TextView timeView, problemView, scoreView;

    // variables
    int a, b, answer;
    int[] choiceArray = {0,0,0,0};

    public void newProblem(){
        a = (int) Math.ceil(Math.random()*10);
        b = (int) Math.ceil(Math.random()*10);
        answer = a + b;

        choiceArray[(int) (Math.random()*4)] = answer;
        for(int i = 0; i < 4; i++) {
            if(choiceArray[i] != answer)
                choiceArray[i] = (int) Math.ceil(Math.random()*answer) + answer/2;
        }
    }

    public void updateDisplay(){

    }

    public void pickedChoice(View view) {
        Log.i("Button", "It is working");
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
    }
}