package ru.mavesoft.trainyourbrain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    public void gameChoiceClick(View view)
    {

        Intent gameStartIntent;
        switch(view.getId())
        {
            case R.id.buttonMaths:
                gameStartIntent = new Intent(this, MathsActivity.class);
                startActivity(gameStartIntent);
                break;
            case R.id.buttonCCards:
                gameStartIntent = new Intent(this, CCardsActivity.class);
                startActivity(gameStartIntent);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
