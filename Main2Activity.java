package com.example.berksavac.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.berksavac.myapplication.IlkYer.MainActivity;

public class Main2Activity extends AppCompatActivity {
    String[] data_list;
    ListView activity_main_listview;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static Main2Activity activity_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button activity_main_button = (Button) findViewById(R.id.activity_main_button);
        activity_main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, CreateData.class);

                startActivity(intent);
            }
        });
        activity_main = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase database = dbcenter.getReadableDatabase();
        cursor = database.rawQuery("Select * from arac", null);
        data_list = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int n = 0; n < cursor.getCount(); n++){
            cursor.moveToPosition(n);
            data_list[n] = cursor.getString(1);
        }
        activity_main_listview = (ListView)findViewById(R.id.activity_main_listview);
        activity_main_listview.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data_list));
        activity_main_listview.setSelected(true);
        activity_main_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String sellection = data_list[position];
                final CharSequence[] dialogitem = {"Göster", "Düzenle", "Arac İade Et"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);

                builder.setTitle("Ayarlar");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent i = new Intent(getApplicationContext(), ReadData.class);
                                i.putExtra("nama", sellection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), UpdateData.class);
                                in.putExtra("nama", sellection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbcenter.getReadableDatabase();
                                db.execSQL("delete from arac where nama = '"+sellection+"'");
                                Toast.makeText(Main2Activity.this, "Aracınız İade Edildi", Toast.LENGTH_SHORT).show();

                                RefreshList();
                                break;
                        }
                    }
                });

                builder.create().show();
            }
        });

        ((ArrayAdapter)activity_main_listview.getAdapter()).notifyDataSetInvalidated();
    }
}