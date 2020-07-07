package com.example.datasiswabimbel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsStudent extends AppCompatActivity {
    DbAdapter db;
    String id,nama,kelas,tanggallahir,alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_student);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        nama = intent.getStringExtra("NAMA");
        kelas = intent.getStringExtra("KELAS");
        tanggallahir = intent.getStringExtra("TANGGALLAHIR");
        alamat = intent.getStringExtra("ALAMAT");
        ((TextView) findViewById(R.id.nama)).setText(nama);
        ((TextView) findViewById(R.id.kelas)).setText(kelas);
        ((TextView) findViewById(R.id.tanggallahir)).setText(tanggallahir);
        ((TextView) findViewById(R.id.alamat)).setText(alamat);
        //calling DbAdapter
        db = new DbAdapter(this);
        db.open();
    }
    public void Edit(View v){
        //go to EditStudent page
        Intent edit = new Intent(DetailsStudent.this, EditStudent.class);
        edit.putExtra("ID", id);
        edit.putExtra("NAMA", nama);
        edit.putExtra("KELAS", kelas);
        edit.putExtra("TANGGALLAHIR", tanggallahir);
        edit.putExtra("ALAMAT",alamat);
        startActivity(edit);
    }
    public void Delete(View v){
        db.delete(Integer.parseInt(id));
        Toast.makeText(getApplicationContext(),"Data Sukses Dihapus", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}