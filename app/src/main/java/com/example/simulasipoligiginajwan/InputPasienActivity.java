package com.example.simulasipoligiginajwan;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;


public class InputPasienActivity extends AppCompatActivity {
    EditText Editid_pasien, Editnama_pasien, Editusia_pasien, Editjk_pasien, Editalamat;
    Button buttonAdd, buttonDaftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pasien);
        Button daftarpetugas =findViewById(R.id.buttonViewPetugas);
        daftarpetugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InputPasienActivity.this, DataPasienActivity.class));
            }
        });

        Editid_pasien = findViewById(R.id.id_pasien);
        Editnama_pasien = findViewById(R.id.nama_pasien);
        Editusia_pasien = findViewById(R.id.usia_pasien);
        Editjk_pasien = findViewById(R.id.jk_pasien);
        Editalamat = findViewById(R.id.alamat);
        buttonAdd = findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }
    public void insertData(){
        String nama_pasien = Editnama_pasien.getText().toString();
        String usia_pasien = Editusia_pasien.getText().toString();
        String jk_pasien = Editjk_pasien.getText().toString();
        String alamat = Editalamat.getText().toString();
        class InsertData extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(InputPasienActivity.this, "Menambahkan data..", "Mohon tunggu", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String status = jsonObject.getString("status");
                    String result = jsonObject.getString("result");
                    Toast.makeText(InputPasienActivity.this, result, Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(InputPasienActivity.this, "Catch", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler rh = new RequestHandler();
                HashMap<String, String> map = new HashMap<>();

                map.put(Konfigurasi.KEY_PASIEN_NAMAPASIEN, nama_pasien);
                map.put(Konfigurasi.KEY_PASIEN_USIAPASIEN, usia_pasien);
                map.put(Konfigurasi.KEY_PASIEN_JKPASIEN, jk_pasien);
                map.put(Konfigurasi.KEY_PASIEN_ALAMAT, alamat);

                String s = rh.sendPostRequest(Konfigurasi.PASIEN_ADD, map);
                return s;
            }

        }
        InsertData id = new InsertData();
        id.execute();
    }

}