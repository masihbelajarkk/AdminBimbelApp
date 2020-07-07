package com.example.datasiswabimbel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditStudent extends AppCompatActivity {
    DbAdapter db;
    String id,nama,kelas,tanggallahir,alamat;
    EditText etnama, etkelas,ettanggallahir,etalamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_student);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        nama = intent.getStringExtra("NAMA");
        kelas = intent.getStringExtra("KELAS");
        tanggallahir = intent.getStringExtra("TANGGALLAHIR");
        alamat = intent.getStringExtra("ALAMAT");
        ((EditText) findViewById(R.id.nama)).setText(nama);
        ((EditText) findViewById(R.id.kelas)).setText(kelas);
        ((EditText) findViewById(R.id.tanggallahir)).setText(tanggallahir);
        ((EditText) findViewById(R.id.alamat)).setText(alamat);
        //calling DbAdapter
        db = new DbAdapter(this);
        db.open();
        //get data from text feld
        etnama =(EditText)findViewById(R.id.nama);
        etkelas =(EditText)findViewById(R.id.kelas);
        ettanggallahir =(EditText)findViewById(R.id.tanggallahir);
        etalamat = (EditText)findViewById(R.id.alamat);
    }
    public void Save(View v){
        nama=etnama.getText().toString();
        kelas=etkelas.getText().toString();
        tanggallahir =ettanggallahir.getText().toString();
        alamat =etalamat.getText().toString();
        db.update(Integer.parseInt(id),nama, kelas, tanggallahir, alamat);
        Toast.makeText(getApplicationContext(),"Data Sukses Diperbarui", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}