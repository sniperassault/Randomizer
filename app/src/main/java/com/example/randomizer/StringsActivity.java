package com.example.randomizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class StringsActivity extends AppCompatActivity {

    private ListView list;
    private List<String> items;
    private ArrayAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strings);
        final EditText et=findViewById(R.id.itemAdd);
        final Button bt=findViewById(R.id.btnStringAdd);
        list=findViewById(R.id.itemList);
        items=new ArrayList<>();
        adp=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,items);
        list.setAdapter(adp);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                String text=et.getText().toString().trim();
                if(!text.isEmpty())
                {
                items.add(et.getText().toString().trim());
                adp.notifyDataSetChanged();
                et.getText().clear();}
                else{
                    Toast.makeText(StringsActivity.this,"Ingrese un texto valido",Toast.LENGTH_LONG).show();
                }
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                adp.notifyDataSetChanged();
                return true;
            }
        });

    }

    public void randomString(View view)
    {   String text = "";
        Random rnd = new Random();
        if(!items.isEmpty()){

        do {
            int i = rnd.nextInt(items.size());
            if (!items.get(i).isEmpty()){text=items.get(i).toString();break;}

        }while(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(StringsActivity.this);
        builder.setTitle("Elemento generado:");
        builder.setMessage(text);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    }

    public void saveData(View view) {
        if(!items.isEmpty()){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        editor.putString("list", json);
        editor.apply();
        Toast.makeText(StringsActivity.this,"Datos guardados",Toast.LENGTH_LONG).show();}
    }

   public void loadData(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        if(json!=null){
        items = gson.fromJson(json,type);
        adp= new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,items);
        list.setAdapter(adp);
        adp.notifyDataSetChanged();
        Toast.makeText(StringsActivity.this,"Datos cargados",Toast.LENGTH_LONG).show();}
    }

    public void randomAll(View view)
        {   List<String> items2=new ArrayList<>();
            String text = "";
            Random rnd = new Random();
            if(!items.isEmpty()) {

                items2=items;
                Collections.shuffle(items2);
                AlertDialog.Builder builder = new AlertDialog.Builder(StringsActivity.this);
                builder.setTitle("Lista generada del primero al ultimo");

                String[] items3= items2.toArray(new String[0]);
                for (int i=0 ; i < items3.length; i++) {
                    items3[i] = (i+1)+") " + items3[i];
                }

                builder.setItems(items3, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();


            }
        }


}
