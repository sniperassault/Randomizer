package com.example.randomizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LettersActivity extends AppCompatActivity {

    private TextView letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        letter=findViewById(R.id.txtRndLetterShow);
        setContentView(R.layout.activity_letters);

    }



    public void randomLetter (View view)
    {char letter='a';
        boolean isChecked = ((CheckBox) findViewById(R.id.chkOnlyVocals)).isChecked();
        TextView tv=findViewById(R.id.txtRndLetterShow);
        if (!isChecked)
        {
            Random rd=new Random();
            String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            letter = abc.charAt(rd.nextInt(abc.length()));}
        else
            {Random rd=new Random();
                String abc = "AEIOU";
                 letter = abc.charAt(rd.nextInt(abc.length()));}
        Toast.makeText(LettersActivity.this,"Letra aleatoria generada!",Toast.LENGTH_SHORT).show();
        tv.setText(Character.toString(letter));

    }


}
