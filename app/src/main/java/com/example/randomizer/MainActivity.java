package com.example.randomizer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void  goRandomNumber(View view)
    {
        Intent intent= new Intent(this,NumbersActivity.class);
        startActivity(intent);
    }
    public void goRandomLetter(View view)
    {
        Intent intent= new Intent(this,LettersActivity.class);
        startActivity(intent);

    }
    public void goRandomString(View view)
    {

        Intent intent=new Intent(this,StringsActivity.class);
        startActivity(intent);
    }
    public void goDice(View view)
    {
        Intent intent=new Intent(this,DiceActivity.class);
        startActivity(intent);

    }
}
