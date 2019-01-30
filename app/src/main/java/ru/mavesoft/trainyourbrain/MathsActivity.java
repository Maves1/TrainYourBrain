package ru.mavesoft.trainyourbrain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MathsActivity extends AppCompatActivity
{

    Button buttonAddition;
    Button buttonSubtraction;
    Button buttonMultiplication;

    public void gameChoiceClick(View view)
    {
        Intent gameStartIntent = new Intent(this, GameActivity.class);

        switch(view.getId())
        {
            case R.id.buttonAddition:
                gameStartIntent.putExtra("GameType", 0);
                startActivity(gameStartIntent);
                break;
            case R.id.buttonSubtraction:
                gameStartIntent.putExtra("GameType", 1);
                startActivity(gameStartIntent);
                break;
            case R.id.buttonMultiplication:
                gameStartIntent.putExtra("GameType", 2);
                startActivity(gameStartIntent);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maths);

        buttonAddition = (Button) findViewById(R.id.buttonAddition);
        buttonSubtraction = (Button) findViewById(R.id.buttonSubtraction);
        buttonMultiplication = (Button) findViewById(R.id.buttonMultiplication);
    }
}
