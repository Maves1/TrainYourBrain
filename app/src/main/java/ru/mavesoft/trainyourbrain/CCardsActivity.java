package ru.mavesoft.trainyourbrain;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CCardsActivity extends AppCompatActivity
{
    ConstraintLayout cardsLayout;

    Button buttonYes;
    Button buttonNo;

    TextView textViewColor;
    TextView textViewCardTimer;

    ArrayList<Integer> colorList = new ArrayList<>();
    ArrayList<String> colorNamesList = new ArrayList<>();

    CountDownTimer countDownTimer;

    boolean isRunning;
    int score;
    int tries;

    int firstId;
    int secondId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ccards);

        cardsLayout = (ConstraintLayout) findViewById(R.id.cardsLayout);

        buttonYes = (Button) findViewById(R.id.buttonYes);
        buttonNo = (Button) findViewById(R.id.buttonNo);

        textViewColor = (TextView) findViewById(R.id.textViewColor);
        textViewCardTimer = (TextView) findViewById(R.id.textViewCardTimer);

        // Adding colors to the list
        colorList.add(Color.RED);
        colorList.add(Color.parseColor("#FFA500"));
        colorList.add(Color.GRAY);
        colorList.add(Color.GREEN);
        colorList.add(Color.CYAN);
        colorList.add(Color.BLUE);
        colorList.add(Color.parseColor("#800080"));

        //Adding color names to another list
        colorNamesList.add("red");
        colorNamesList.add("orange");
        colorNamesList.add("gray");
        colorNamesList.add("green");
        colorNamesList.add("cyan");
        colorNamesList.add("blue");
        colorNamesList.add("purple");

        startGame();
    }

    public void startGame()
    {
        isRunning = true;
        score = 0;
        tries = 0;

        changeColor();

        countDownTimer = new CountDownTimer(30000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                textViewCardTimer.setText(Long.toString(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish()
            {
                textViewCardTimer.setText("-");
                isRunning = false;
                gameEnd();
            }
        }.start();
    }

    public void checkClick(View view)
    {
        if (isRunning)
        {
            switch (view.getId())
            {
                case R.id.buttonYes:
                    if (firstId == secondId)
                    {
                        score++;
                        tries++;
                        new CountDownTimer(100, 50)
                        {
                            @Override
                            public void onTick(long millisUntilFinished)
                            {
                                cardsLayout.setBackgroundColor(Color.rgb(0, 200, 0));
                            }

                            @Override
                            public void onFinish()
                            {
                                cardsLayout.setBackgroundColor(Color.WHITE);
                            }

                        }.start();
                    }
                    else
                    {
                        tries++;

                        new CountDownTimer(100, 50)
                        {
                            @Override
                            public void onTick(long millisUntilFinished)
                            {
                                cardsLayout.setBackgroundColor(Color.rgb(255, 132, 132));
                            }

                            @Override
                            public void onFinish()
                            {
                                cardsLayout.setBackgroundColor(Color.WHITE);
                            }

                        }.start();
                    }
                    break;
                case R.id.buttonNo:
                    if (firstId != secondId)
                    {
                        score++;
                        tries++;

                        new CountDownTimer(100, 50)
                        {
                            @Override
                            public void onTick(long millisUntilFinished)
                            {
                                cardsLayout.setBackgroundColor(Color.rgb(0, 200, 0));
                            }

                            @Override
                            public void onFinish()
                            {
                                cardsLayout.setBackgroundColor(Color.WHITE);
                            }

                        }.start();
                    }
                    else
                    {
                        tries++;

                        new CountDownTimer(100, 50)
                        {
                            @Override
                            public void onTick(long millisUntilFinished)
                            {
                                cardsLayout.setBackgroundColor(Color.rgb(255, 132, 132));
                            }

                            @Override
                            public void onFinish()
                            {
                                cardsLayout.setBackgroundColor(Color.WHITE);
                            }

                        }.start();
                    }
                    break;
            }
            changeColor();
        }
    }

    public void changeColor()
    {
        firstId = (int) (Math.random() * 6 + 1);
        secondId = (int) (Math.random() * 6 + 1);

        int textColor = colorList.get(firstId);
        String text = colorNamesList.get(secondId);

        textViewColor.setText(text);
        textViewColor.setTextColor(textColor);
    }

    public void gameEnd()
    {
        Toast.makeText(this, "Your score: " + score + " out of " + tries, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy()
    {
        countDownTimer.cancel();
        super.onDestroy();
    }
}
