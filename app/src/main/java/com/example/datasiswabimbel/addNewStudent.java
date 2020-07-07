package com.example.datasiswabimbel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addNewStudent extends AppCompatActivity {
    //calling variables
    DbAdapter db;
    EditText etnama,etkelas, ettanggallahir,etalamat;
    String nama, kelas, tanggallahir, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_student);
        //get data from text feld
        etnama =(EditText)findViewById(R.id.nama);
        etkelas =(EditText)findViewById(R.id.kelas);
        ettanggallahir =(EditText)findViewById(R.id.tanggallahir);
        etalamat = (EditText)findViewById(R.id.alamat);
        //calling DbAdapter
        db = new DbAdapter(this);
        db.open();
    }
    public void Save(View v){
        if(db.isExist(kelas)){
            Toast.makeText(getApplicationContext(),"Data Sudah Ada", Toast.LENGTH_SHORT).show();
        }else{
            nama = etnama.getText().toString();
            kelas = etkelas.getText().toString();
            tanggallahir = ettanggallahir.getText().toString();
            alamat = etalamat.getText().toString();
            db.insert(nama, kelas, tanggallahir, alamat);
            Toast.makeText(getApplicationContext(),"Data Sukses Ditambahkan", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
