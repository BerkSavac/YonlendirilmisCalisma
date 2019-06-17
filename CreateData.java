package com.example.berksavac.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateData extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button activity_create_arac_simpanbutton, activity_create_arac_backbutton;
    EditText activity_create_arac_numberedittext;
    EditText activity_create_arac_namaeditext;
    EditText activity_create_arac_birthdayeditext;
    EditText activity_create_arac_jkeditext;
    EditText activity_create_arac_addresseditext;
    EditText activity_create_arac_verilditext;
    EditText activity_create_arac_zimmetditext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data);
    dbHelper=new DataHelper(this);
    activity_create_arac_numberedittext=(EditText)findViewById(R.id.activity_create_arac_numberedittext);
        activity_create_arac_namaeditext= (EditText)findViewById(R.id.activity_create_arac_namaeditext);
        activity_create_arac_birthdayeditext = (EditText)findViewById(R.id.activity_create_arac_birthdayeditext);
        activity_create_arac_jkeditext = (EditText)findViewById(R.id.activity_create_arac_jkeditext);
        activity_create_arac_addresseditext = (EditText)findViewById(R.id.activity_create_arac_addresseditext);
        activity_create_arac_verilditext=(EditText)findViewById(R.id.activity_create_arac_verilditext);
        activity_create_arac_zimmetditext=(EditText)findViewById(R.id.activity_create_arac_zimmettext);
        activity_create_arac_simpanbutton =(Button) findViewById(R.id.activity_create_arac_simpanbutton);
        activity_create_arac_backbutton= (Button)findViewById(R.id.activity_create_arac_backbutton);
        activity_create_arac_simpanbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO arac(no, nama, yil, plaka, vergi,verildi,zimmet) VALUES ('"+
                activity_create_arac_numberedittext.getText().toString()+"','"+
                        activity_create_arac_namaeditext.getText().toString()+"','"+
                        activity_create_arac_birthdayeditext.getText().toString()+"','"+
                        activity_create_arac_jkeditext.getText().toString()+"','"+
                        activity_create_arac_addresseditext.getText().toString()+"','"+
                        activity_create_arac_verilditext.getText().toString()+"','"+
                        activity_create_arac_zimmetditext.getText().toString()+"')")    ;
                Toast.makeText(getApplicationContext(),"Doğru", Toast.LENGTH_LONG).show();

                Main2Activity.activity_main.RefreshList();
                finish();
            }
        });
        activity_create_arac_backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}