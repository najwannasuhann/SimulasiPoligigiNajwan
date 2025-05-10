package com.example.simulasipoligiginajwan;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText u;
    EditText p;
    Button l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        u = (EditText) findViewById(R.id.username);
        p = (EditText) findViewById(R.id.password);
        l = (Button) findViewById(R.id.button);
        loginadmin();
    }
    public void loginadmin(){
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (u.getText().toString().equals("admin")&&p.getText().toString().equals("admin")){
                    Toast.makeText(LoginActivity.this,"Login anda Sukses",Toast.LENGTH_LONG).show();
                    Intent i= new Intent(LoginActivity.this,DashboardActivity.class);
                    startActivity(i);
                }else {
                    Toast.makeText(LoginActivity.this,"Login anda gagal, periksa kembali username dan password anda",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

}