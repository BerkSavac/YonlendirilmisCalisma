package com.example.berksavac.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button activity_update_arac_btsave, activity_update_arac_btback;
    EditText activity_update_arac_etıd;
    EditText activity_update_arac_etmarka;
    EditText activity_update_arac_etyil;
    EditText activity_update_arac_etplaka;
    EditText activity_update_arac_etvergi;
    EditText activity_update_arac_etverildi;
    EditText activity_update_arac_etzimmet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        dbHelper = new DataHelper(this);
        activity_update_arac_etıd = (EditText)findViewById(R.id.activity_update_arac_etıd);
        activity_update_arac_etmarka = (EditText)findViewById(R.id.activity_update_arac_etmarka);
        activity_update_arac_etyil = (EditText)findViewById(R.id.activity_update_arac_etyil);
        activity_update_arac_etplaka = (EditText)findViewById(R.id.activity_update_arac_etplaka);
        activity_update_arac_etvergi = (EditText)findViewById(R.id.activity_update_arac_etvergi);
        activity_update_arac_etverildi=(EditText)findViewById(R.id.activity_update_arac_etverildi);
        activity_update_arac_etzimmet=(EditText)findViewById(R.id.activity_update_arac_etzimmet);
        activity_update_arac_btsave = (Button)findViewById(R.id.activity_update_arac_btsave);
        activity_update_arac_btback = (Button)findViewById(R.id.activity_update_arac_btback);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM arac WHERE nama = '" +
                getIntent().getStringExtra("nama")+"'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            activity_update_arac_etıd.setText(cursor.getString(0));
            activity_update_arac_etmarka.setText(cursor.getString(1));
            activity_update_arac_etyil.setText(cursor.getString(2));
            activity_update_arac_etplaka.setText(cursor.getString(3));
            activity_update_arac_etvergi.setText(cursor.getString(4));
            activity_update_arac_etverildi.setText(cursor.getString(5));
            activity_update_arac_etzimmet.setText(cursor.getString(6));

        }

        activity_update_arac_btsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE arac set nama='" +
                        activity_update_arac_etıd.getText().toString()+"', yil='" +
                        activity_update_arac_etmarka.getText().toString()+"',plaka='" +
                        activity_update_arac_etyil.getText().toString()+"',vergi='" +
                        activity_update_arac_etplaka.getText().toString()+"',verildi='" +
                        activity_update_arac_etvergi.getText().toString()+"',zimmet='" +

                        activity_update_arac_etverildi.getText().toString()+"' WHERE no='" +
                        activity_update_arac_etzimmet.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Eklendi", Toast.LENGTH_LONG).show();

                Main2Activity.activity_main.RefreshList();
                finish();
            }
        });

        activity_update_arac_btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
