package com.example.datasiswabimbel;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    //calling variables
    DbAdapter db;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //calling DbAdapter
        db = new DbAdapter(this);
        db.open();
        //initially insert some data
        //db.insert("Color Picker", "+840885654", "info@icyarena.com", "US");
        //db.insert("Mike Helen", "+808853437", "halen@icyarena.com", "UK");
        //db.insert("Robart Pink", "+808851234", "robart@icyarena.com", "AUS");
        //display data
        ListView lv = (ListView) findViewById(R.id.listView1);
        int layoutstyle=R.layout.liststyle;
        int[] xml_id = new int[] {
                R.id.txtnama,
                R.id.txtkelas
        };
        String[] column = new String[] {
                "nama",
                "kelas"
        };
        Cursor row = db.fetchAllData();
        adapter = new SimpleCursorAdapter(this, layoutstyle,row,column, xml_id, 0);
        lv.setAdapter(adapter);
        //onClick function
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterview, View view, int position, long id) {
                Cursor row = (Cursor) adapterview.getItemAtPosition(position);
                String _id = row.getString(row.getColumnIndexOrThrow("_id"));
                String nama = row.getString(row.getColumnIndexOrThrow("nama"));
                String kelas = row.getString(row.getColumnIndexOrThrow("kelas"));
                String tanggallahir = row.getString(row.getColumnIndexOrThrow("tanggallahir"));
                String alamat = row.getString(row.getColumnIndexOrThrow("alamat"));
                //go to detailsContact page
                Intent todetais = new Intent(MainActivity.this, DetailsStudent.class);
                todetais.putExtra("ID",_id);
                todetais.putExtra("NAMA", nama);
                todetais.putExtra("KELAS",kelas);
                todetais.putExtra("TANGGALLAHIR",tanggallahir);
                todetais.putExtra("ALAMAT",alamat);
                startActivity(todetais);
            }
        });
        //display data by filter
        EditText et = (EditText) findViewById(R.id.myFilter);
        et.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }
        });
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return db.fetchdatabyfilter(constraint.toString(),"nama");
            }
        });
    }
    public void addStudent(View v){
        Intent addNewStudent = new Intent(MainActivity.this, addNewStudent.class);
        startActivity(addNewStudent);


    }

}