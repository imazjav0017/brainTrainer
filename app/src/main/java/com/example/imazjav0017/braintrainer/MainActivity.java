package com.example.imazjav0017.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton, option0, option1, option2, option3,playAgain;
    TextView score, timer, result, question;
    RelativeLayout relativeLayout;
    ArrayList<Integer> list=new ArrayList<Integer>();
    GridLayout grid;
    int answerLocation;
    int sc=0,qst=0;
    public void generate()
    {
        list.clear();
        Random rand=new Random();
        int a=rand.nextInt(31);
        int b=rand.nextInt(31);
        question.setText(Integer.toString(a)+" + "+Integer.toString(b));
        answerLocation=rand.nextInt(4);
        for(int i=0;i<4;i++)
        {
            if(i==answerLocation)
            {
                list.add(a+b);
            }
            else
            {
                int incorrectAnswer=rand.nextInt(61);
                while(incorrectAnswer==a+b)
                {
                    incorrectAnswer=rand.nextInt(61);
                }
                list.add(incorrectAnswer);
            }
        }
        option0.setText(Integer.toString(list.get(0)));
        option1.setText(Integer.toString(list.get(1)));
        option2.setText(Integer.toString(list.get(2)));
        option3.setText(Integer.toString(list.get(3)));
    }

    public void play(View v) {
        playAgain.setVisibility(View.INVISIBLE);
        sc=0;
        qst=0;
        result.setVisibility(View.INVISIBLE);
        option0.setClickable(true);
        option1.setClickable(true);
        option2.setClickable(true);
        option3.setClickable(true);
        generate();
        score.setText("0/0");
        new CountDownTimer(30000 + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(Long.toString(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                gameOver();
            }
        }.start();

    }
    public void evaluate(View v)
    {
        if(v.getTag().toString().equals(Integer.toString(answerLocation)))
        {
            sc++;
            result.setText("Correct!");
        }
        else
            result.setText("Wrong!");
        qst++;
        score.setText(Integer.toString(sc)+"/"+Integer.toString(qst));
        result.setVisibility(View.VISIBLE);
        generate();
    }
public void gameOver()
{
    result.setText("Final Score= " +Integer.toString(sc)+"/"+Integer.toString(qst));
    option0.setClickable(false);
    option1.setClickable(false);
    option2.setClickable(false);
    option3.setClickable(false);
    playAgain.setVisibility(View.VISIBLE);

}

    public void start(View v)
    {
        startButton.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(View.VISIBLE);
        new CountDownTimer(30000 + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(Long.toString(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                gameOver();
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton=(Button)findViewById(R.id.startButton);
        relativeLayout=(RelativeLayout)findViewById(R.id.relativeLayout);
        option0=(Button)findViewById(R.id.option0);
        option1=(Button)findViewById(R.id.option1);
        option2=(Button)findViewById(R.id.option2);
        option3=(Button)findViewById(R.id.option3);
        score=(TextView)findViewById(R.id.score);
        timer=(TextView)findViewById(R.id.timer);
        result=(TextView)findViewById(R.id.result);
        question=(TextView)findViewById(R.id.question);
        grid=(GridLayout)findViewById(R.id.grid);
        playAgain=(Button)findViewById(R.id.playAgain);
        generate();
    }
}
