package com.example.berksavac.myapplication.IlkYer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.berksavac.myapplication.Main2Activity;
import com.example.berksavac.myapplication.R;

public class İletisim extends AppCompatActivity {
private Button btnSendd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iletisim);
        btnSendd=findViewById(R.id.btnSend);
        btnSendd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(İletisim.this, "Gönderme Başarılı", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(İletisim.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
