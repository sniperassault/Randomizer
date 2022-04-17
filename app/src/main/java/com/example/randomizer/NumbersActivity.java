package com.example.randomizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class NumbersActivity extends AppCompatActivity {
private EditText minvalue;
private EditText maxvalue;
private TextView num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        minvalue = findViewById(R.id.numBegin);
        maxvalue = findViewById(R.id.numEnd);
        num=findViewById(R.id.txtRndNumShow);

    }

    public void randomNumber(View view) {


        if(minvalue.getText().length()==0 || maxvalue.getText().length()==0)  Toast.makeText(NumbersActivity.this,"Faltan ingresar datos!", Toast.LENGTH_LONG).show();
        else{
        int min = Integer.parseInt(minvalue.getText().toString());
        int max = Integer.parseInt(maxvalue.getText().toString());
            if(min>max)  Toast.makeText(NumbersActivity.this,"El numero de inicio debe ser menor o igual al numero de final", Toast.LENGTH_LONG).show();
            else {
            int random_int = (int) (Math.random() * (max - min + 1) + min);
            Toast.makeText(NumbersActivity.this,"Numero aleatorio generado!",Toast.LENGTH_SHORT).show();
            num.setText(Integer.toString(random_int));
            }}
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("begin",minvalue.getText().toString());
        outState.putString("end",maxvalue.getText().toString());
        outState.putString("num",num.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        minvalue.setText(savedInstanceState.getString("begin"));
        maxvalue.setText(savedInstanceState.getString("end"));
        num.setText(savedInstanceState.getString("num"));

    }
}
