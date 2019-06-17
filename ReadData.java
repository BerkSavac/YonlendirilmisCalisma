package com.example.berksavac.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReadData extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button activity_read_arac_buttonback;
    TextView tvnumber,tvnama,tvbirthday,tvgender,tvaddress,tvVerildi,tvZimmet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);
        dbHelper = new DataHelper(this);
        tvnumber = (TextView) findViewById(R.id.activity_read_arac_tv1);
        tvnama = (TextView) findViewById(R.id.activity_read_arac_tv2);
        tvbirthday = (TextView) findViewById(R.id.activity_read_arac_tv3);
        tvgender = (TextView) findViewById(R.id.activity_read_arac_tv4);
        tvaddress = (TextView) findViewById(R.id.activity_read_arac_tv5);
        tvVerildi = (TextView) findViewById(R.id.activity_read_arac_tv6);
        tvZimmet = (TextView) findViewById(R.id.activity_read_arac_tv7);
        activity_read_arac_buttonback = (Button) findViewById(R.id.activity_read_arac_buttonback);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM arac WHERE nama ='" +
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            tvnumber.setText(cursor.getString(0));
            tvnama.setText(cursor.getString(1));
            tvbirthday.setText(cursor.getString(2));
            tvgender.setText(cursor.getString(3));
            tvaddress.setText(cursor.getString(4));
            tvVerildi.setText(cursor.getString(5));
            tvZimmet.setText(cursor.getString(6));
            activity_read_arac_buttonback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}