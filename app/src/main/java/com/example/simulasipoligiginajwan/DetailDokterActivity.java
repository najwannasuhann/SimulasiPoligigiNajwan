package com.example.simulasipoligiginajwan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DetailDokterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextIdDokter;
    private EditText editTextnoSip;
    private EditText editTextnamadokter;
    private EditText editTextjkdokter;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dokter);
        Intent intent = getIntent();

        id = intent.getStringExtra(Konfigurasi.EMP_ID);

        editTextIdDokter = (EditText) findViewById(R.id.editTextIdDokter);
        editTextnoSip = (EditText) findViewById(R.id.editTextnoSip);
        editTextnamadokter = (EditText) findViewById(R.id.editTextnamadokter);
        editTextjkdokter = (EditText) findViewById(R.id.editTextjkDokter);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        editTextIdDokter.setText(id);

        getEmployee();
    }
    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailDokterActivity.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.DOKTER_DETAILS,id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String no_sip = c.getString(Konfigurasi.TAG_NOSIP);
            String nama_dokter = c.getString(Konfigurasi.TAG_NAMADOKTER);
            String jk_dokter = c.getString(Konfigurasi.TAG_JKDOKTER);

            editTextnoSip.setText(no_sip);
            editTextnamadokter.setText(nama_dokter);
            editTextjkdokter.setText(jk_dokter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateEmployee(){
        final String nosip = editTextnoSip.getText().toString().trim();
        final String namadokter = editTextnamadokter.getText().toString().trim();
        final String jk_dokter = editTextjkdokter.getText().toString().trim();


        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailDokterActivity.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(DetailDokterActivity.this,s,Toast.LENGTH_LONG).show();
                startActivity(new Intent(DetailDokterActivity.this,DataDokterActivity.class));
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_DOKTER_IDDOKTER,id);
                hashMap.put(Konfigurasi.KEY_DOKTER_NOSIP,nosip);
                hashMap.put(Konfigurasi.KEY_DOKTER_NAMADOKTER,namadokter);
                hashMap.put(Konfigurasi.KEY_DOKTER_JKDOKTER,jk_dokter);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Konfigurasi.DOKTER_UPDATE,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailDokterActivity.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(DetailDokterActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.DOKTER_HAPUS, id);
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Pasien ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(DetailDokterActivity.this,DataDokterActivity.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateEmployee();
        }

        if(v == buttonDelete){
            confirmDeleteEmployee();
        }
    }

}