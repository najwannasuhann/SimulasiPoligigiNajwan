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

public class DetailPasienActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextId;
    private EditText editTextnamapasien;
    private EditText editTextusiapasien;
    private EditText editTextjkpasien;
    private EditText editTextalamat;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pasien);
        Intent intent = getIntent();

        id = intent.getStringExtra(Konfigurasi.EMP_ID);

        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextnamapasien = (EditText) findViewById(R.id.editTextNamaPasien);
        editTextusiapasien = (EditText) findViewById(R.id.editTextusiapasien);
        editTextjkpasien = (EditText) findViewById(R.id.editTextJk);
        editTextalamat = (EditText) findViewById(R.id.editTextalamat);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextId.setText(id);

        getEmployee();
    }
    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailPasienActivity.this,"Fetching...","Wait...",false,false);
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
                String s = rh.sendGetRequestParam(Konfigurasi.PASIEN_DETAILS,id);
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
            String nama_pasien = c.getString(Konfigurasi.TAG_NAMAPASIEN);
            String usia_pasien = c.getString(Konfigurasi.TAG_USIAPASIEN);
            String jk_pasien = c.getString(Konfigurasi.TAG_JKPASIEN);
            String alamat = c.getString(Konfigurasi.TAG_ALAMAT);

            editTextnamapasien.setText(nama_pasien);
            editTextusiapasien.setText(usia_pasien);
            editTextjkpasien.setText(jk_pasien);
            editTextalamat.setText(alamat);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateEmployee(){
        final String namapasien = editTextnamapasien.getText().toString().trim();
        final String usiapasien = editTextusiapasien.getText().toString().trim();
        final String jkpasien = editTextjkpasien.getText().toString().trim();
        final String alamat = editTextalamat.getText().toString().trim();

        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailPasienActivity.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(DetailPasienActivity.this,s,Toast.LENGTH_LONG).show();
                startActivity(new Intent(DetailPasienActivity.this,DataPasienActivity.class));
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_PASIEN_IDPASIEN,id);
                hashMap.put(Konfigurasi.KEY_PASIEN_NAMAPASIEN,namapasien);
                hashMap.put(Konfigurasi.KEY_PASIEN_USIAPASIEN,usiapasien);
                hashMap.put(Konfigurasi.KEY_PASIEN_JKPASIEN,jkpasien);
                hashMap.put(Konfigurasi.KEY_PASIEN_ALAMAT,alamat);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Konfigurasi.PASIEN_UPDATE,hashMap);

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
                loading = ProgressDialog.show(DetailPasienActivity.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(DetailPasienActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Konfigurasi.PASIEN_HAPUS, id);
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
                        startActivity(new Intent(DetailPasienActivity.this,DataPasienActivity.class));
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