package com.example.simulasipoligiginajwan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {
   private View pasienButton, dokterButton , kasirButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        pasienButton = findViewById(R.id.data_pasien);
        dokterButton =findViewById(R.id.data_dokter);
        kasirButton =findViewById(R.id.data_kasir);

        pasienButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,InputPasienActivity.class);
                startActivity(intent);
            }
        });
        dokterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,InputDokterActivity.class);
                startActivity(intent);
            }
        });
        kasirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}