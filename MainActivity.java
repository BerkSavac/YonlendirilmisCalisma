package com.example.berksavac.myapplication.IlkYer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.berksavac.myapplication.Main2Activity;
import com.example.berksavac.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private Button Hakkımızdaa;
    private Button btSignIn;
    private Button btSignUp;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button iletisimm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btSignIn=findViewById(R.id.btSignIn);
        btSignUp=findViewById(R.id.btSignUp);
        Hakkımızdaa=findViewById(R.id.Hakkımızda);
        iletisimm=findViewById(R.id.iletisim);
        edtEmail=findViewById(R.id.emailinput);
        edtPassword=findViewById(R.id.passwordinput);
        final DatabaseHelper dbHelper=new DatabaseHelper(this);

        iletisimm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,İletisim.class);
                startActivity(intent);
            }
        });
        Hakkımızdaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Hakkimizda.class);
                startActivity(intent);
            }
        });
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                    dbHelper.addUser(new User(edtEmail.getText().toString(), edtPassword.getText().toString()));
                    Toast.makeText(MainActivity.this, "Kullanıcı eklendi", Toast.LENGTH_SHORT).show();
                    edtEmail.setText("");
                    edtPassword.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "Kullanıcı eklenmedi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                    User user = dbHelper.queryUser(edtEmail.getText().toString(), edtPassword.getText().toString());
                    if (user != null) {
                        Bundle mBundle = new Bundle();
                        mBundle.putString("user", user.getEmail());
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        intent.putExtras(mBundle);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Hoşgeldin " + user.getEmail(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Kullanıcı bulunamadı", Toast.LENGTH_SHORT).show();
                        edtPassword.setText("");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Hata", Toast.LENGTH_SHORT).show();
                }}

        });

}
    private boolean emptyValidation() {
        if (TextUtils.isEmpty(edtEmail.getText().toString()) || TextUtils.isEmpty(edtPassword.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }}
