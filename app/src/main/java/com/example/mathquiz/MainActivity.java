package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import java. util. ArrayList;
import android.widget.TextView;
import java.util.Random;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    TextView scoreTextView;
    TextView option1;
    TextView option2;
    TextView option3;
    TextView option4;
    Button startButton;
    Button playAgain;
    TextView qResultTextView;
    TextView questionTextView;
    ConstraintLayout constraintLayout;
    Boolean quizOn = false;
    int rand;
    int attempted = 0;
    int score = 0;

    public void setVisibility(){

        playAgain.setVisibility(View.INVISIBLE);
        if(!quizOn) {
            constraintLayout.setVisibility(View.INVISIBLE);

        }else
        {
            constraintLayout.setVisibility(View.VISIBLE);
            startButton.setVisibility(View.INVISIBLE);
        }

    }

    public void setTimer(){

        new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secs = (int) millisUntilFinished/1000;
                timerTextView.setText(String.format("%d" + "s",secs));

            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                qResultTextView.setText("Done...!");
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);
            }
        }.start();

    }

    public void optBtn(View view){

        attempted++;
        if (view.getTag().toString().equals(Integer.toString(rand))){
            qResultTextView.setText("Correct :)");
            score++;
        }
        else {
            qResultTextView.setText("Wrong :(");
        }
        giveQuestion();
        updateScore();
    }

    public void reset(View view){
        playAgain.setVisibility(View.INVISIBLE);
        qResultTextView.setText("");
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);
        giveQuestion();
        setTimer();
        scoreTextView.setText("0/0");
        score = 0;
        attempted = 0;

    }

    private void updateScore() {
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(attempted));
    }

    public void setOptions(int one, int two,String operator){

        int ans;
        if(operator.equals("-")){
            ans = one - two;
        }
        else {
            ans = one + two;
        }
        Random r = new Random();
        rand = r.nextInt(4);

        if (Integer.parseInt( option1.getTag().toString()) == rand) {
            option1.setText(Integer.toString(ans));
        }
        else {
            option1.setText(Integer.toString(ans-7));
        }

        if (Integer.parseInt( option2.getTag().toString()) == rand) {
            option2.setText(Integer.toString(ans));
        }
        else {
            option2.setText(Integer.toString(ans+6));
        }

        if (Integer.parseInt( option3.getTag().toString()) == rand) {
            option3.setText(Integer.toString(ans));
        }
        else {
            option3.setText(Integer.toString(ans+3));
        }

        if (Integer.parseInt( option4.getTag().toString()) == rand) {
            option4.setText(Integer.toString(ans));
        }
        else {
            option4.setText(Integer.toString(ans-2));
        }

    }

    public void giveQuestion(){

        Random rand = new Random();
        int one = rand.nextInt(21);
        int two = rand.nextInt(21);
        ArrayList<String> operators = new ArrayList<String>(asList("+","-"));
        int index = rand.nextInt(2);
        questionTextView.setText(String.format("%d %s %d",one,operators.get(index),two));
        setOptions(one,two,operators.get(index));

    }
    public void startQuiz(View view){

        startButton = findViewById(R.id.startButton);
        quizOn = true;
        setVisibility();
        setTimer();
        giveQuestion();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.constraintLayout);
        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        playAgain = findViewById(R.id.playAgainBtn);
        qResultTextView = findViewById(R.id.qResultTextView);
        questionTextView = findViewById(R.id.questionTextView);

        setVisibility();

    }
}