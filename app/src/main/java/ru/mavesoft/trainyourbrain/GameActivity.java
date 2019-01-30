package ru.mavesoft.trainyourbrain;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameActivity extends AppCompatActivity
{

    TextView textViewTimer;
    TextView textViewSum;
    TextView textViewScore;

    TextView textViewEndInfo;

    ConstraintLayout layoutGame;
    GridLayout layoutButtons;
    LinearLayout layoutGameEnd;

    int gameType;
    boolean isRunning;

    int number1;
    int number2;
    int answer;

    int allTries;
    int score;

    CountDownTimer timer;

    public void startGame()
    {

        try
        {
            timer.cancel();
        }
        catch (Exception e)
        {

        }

        timer = new CountDownTimer(30050, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                textViewTimer.setText(Long.toString(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish()
            {
                textViewTimer.setText("0");

                layoutGame.setBackgroundColor(Color.WHITE);

                gameEnd();
            }
        }.start();

        allTries = 0;
        score = 0;
        updateScore();

        isRunning = true;

        if(isRunning)
        {
            makeNumber();
        }

    }

    public void gameEnd()
    {
        isRunning = false;

        // textViewEndInfo.setText(getResources().getString(R.string.info_your_result) + "\n" + Integer.toString(score) + "/" + Integer.toString(allTries));

        try
        {
            int percents = (score * 100 / allTries);

            textViewEndInfo.setText(getResources().getString(R.string.info_your_result) + "\n" + percents + "%");

            layoutGameEnd.animate().translationYBy(3000f).setDuration(2000);
        } catch (Exception e)
        {

        }



    }

    public void makeNumber()
    {

        switch (gameType)
        {
            case 0:

                number1 = (int) (Math.random() * 50 + 1);
                number2 = (int) (Math.random() * 50 + 1);
                answer = number1 + number2;

                textViewSum.setText(Integer.toString(number1) + " + " + Integer.toString(number2));

                break;
            case 1:

                number1 = (int) (Math.random() * 50 + 1);
                number2 = (int) (Math.random() * 50 + 1);

                if(number1 > number2)
                {
                    answer = number1 - number2;

                    textViewSum.setText(Integer.toString(number1) + " - " + Integer.toString(number2));
                } else
                {
                    answer = number2 - number1;

                    textViewSum.setText(Integer.toString(number2) + " - " + Integer.toString(number1));
                }

                break;
            case 2:

                number1 = (int) (Math.random() * 10 + 1);
                number2 = (int) (Math.random() * 10 + 1);
                answer = number1 * number2;

                textViewSum.setText(Integer.toString(number1) + " * " + Integer.toString(number2));

                break;
        }

        ArrayList<String> numberList = new ArrayList<>();

        String fNumber1;
        String fNumber2;
        String fNumber3;
        String fNumber4;

        if(answer > 10)
        {

            fNumber1 = Integer.toString(answer - (int) (Math.random() * 10 + 1));
            fNumber2 = Integer.toString(answer + (int) (Math.random() * 10 + 1));
            fNumber3 = Integer.toString(answer - (int) (Math.random() * 10 + 1));
            fNumber4 = Integer.toString(answer + (int) (Math.random() * 10 + 1));

        } else
        {

            fNumber1 = Integer.toString((int) (Math.random() * 50 + 1));
            fNumber2 = Integer.toString((int) (Math.random() * 50 + 1));
            fNumber3 = Integer.toString((int) (Math.random() * 50 + 1));
            fNumber4 = Integer.toString((int) (Math.random() * 50 + 1));

        }


        numberList.add(fNumber1);
        numberList.add(fNumber2);
        numberList.add(fNumber3);
        numberList.add(fNumber4);

        for (int i = 0; i < layoutButtons.getChildCount(); i++)
        {

            Button sumButton = (Button) layoutButtons.getChildAt(i);

            sumButton.setText(numberList.get(i));

        }

        Button buttonRightAnswer = (Button) layoutButtons.getChildAt((int) (Math.random() * 3 + 1));

        buttonRightAnswer.setText(Integer.toString(answer));

    }

    public void checkAnswer(View view)
    {

        if (isRunning) {

            String sAnswer = Integer.toString(answer);
            Button tappedButton = (Button) view;

            if (sAnswer.equals(tappedButton.getText().toString())) {
                allTries++;
                score++;

                new CountDownTimer(100, 50)
                {
                    @Override
                    public void onTick(long millisUntilFinished)
                    {
                        layoutGame.setBackgroundColor(Color.rgb(0, 200, 0));
                    }

                    @Override
                    public void onFinish()
                    {
                        layoutGame.setBackgroundColor(Color.WHITE);
                    }

                }.start();


                makeNumber();
            } else {
                allTries++;

                new CountDownTimer(100, 50)
                {
                    @Override
                    public void onTick(long millisUntilFinished)
                    {
                        layoutGame.setBackgroundColor(Color.rgb(255, 132, 132));
                    }

                    @Override
                    public void onFinish()
                    {
                        layoutGame.setBackgroundColor(Color.WHITE);
                    }

                }.start();

                makeNumber();
            }

            updateScore();
        }
    }

    public void playAgainClick(View view)
    {
        layoutGameEnd.animate().translationYBy(-3000f).setDuration(2000);

        startGame();
    }

    public void updateScore()
    {
        if (allTries == 0)
        {
            textViewScore.setText("0/0");
        }
        else
        {
            textViewScore.setText(Integer.toString(score) + "/" + Integer.toString(allTries));
        }

    }

    public void returnClick(View view)
    {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewTimer = (TextView) findViewById(R.id.textViewTimer);
        textViewSum = (TextView) findViewById(R.id.textViewSum);
        textViewScore = (TextView) findViewById(R.id.textViewScore);

        textViewEndInfo = (TextView) findViewById(R.id.textViewEndInfo);

        layoutGame = (ConstraintLayout) findViewById(R.id.layoutGame);
        layoutButtons = (GridLayout) findViewById(R.id.layoutButtons);
        layoutGameEnd = (LinearLayout) findViewById(R.id.layoutGameEnd);

        layoutGameEnd.setTranslationY(-3000f);

        gameType = getIntent().getExtras().getInt("GameType");
        startGame();
    }

    @Override
    protected void onDestroy()
    {
        timer.cancel();
        super.onDestroy();
    }
}
