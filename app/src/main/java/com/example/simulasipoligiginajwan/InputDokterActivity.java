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

public class InputDokterActivity extends AppCompatActivity {
    EditText Editid_dokter, Editsip_dokter ,Editnama_dokter, Editjk_dokter;
    Button buttonAdd, buttonDaftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_dokter);
        Button daftardokter =findViewById(R.id.buttonViewDokter);
        daftardokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InputDokterActivity.this, DataDokterActivity.class));
            }
        });

        Editsip_dokter = findViewById(R.id.no_sip);
        Editnama_dokter = findViewById(R.id.nama_dokter);
        Editjk_dokter = findViewById(R.id.jk_dokter);

        buttonAdd = findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }
    public void insertData(){
        String no_sip = Editsip_dokter.getText().toString();
        String nama_dokter= Editnama_dokter.getText().toString();
        String jk_dokter = Editjk_dokter.getText().toString();

        class InsertData extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(InputDokterActivity.this, "Menambahkan data..", "Mohon tunggu", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String status = jsonObject.getString("status");
                    String result = jsonObject.getString("result");
                    Toast.makeText(InputDokterActivity.this, result, Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(InputDokterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler rh = new RequestHandler();
                HashMap<String, String> map = new HashMap<>();

                map.put(Konfigurasi.KEY_DOKTER_NOSIP, no_sip);
                map.put(Konfigurasi.KEY_DOKTER_NAMADOKTER, nama_dokter);
                map.put(Konfigurasi.KEY_DOKTER_JKDOKTER, jk_dokter);

                String s = rh.sendPostRequest(Konfigurasi.DOKTER_ADD, map);
                return s;
            }

        }
        InsertData id = new InsertData();
        id.execute();
    }
}