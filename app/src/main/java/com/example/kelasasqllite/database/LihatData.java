package com.example.kelasasqllite.database;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kelasasqllite.R;

public class LihatData extends AppCompatActivity {
    static TextView nama,telpon;
    String nm,id,tlp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihatdata);
        nama = findViewById(R.id.tvNamaKontak);
        telpon = findViewById(R.id.tvNoTel);

        id = getIntent().getStringExtra("id");
        nm = getIntent().getStringExtra("nama");
        tlp = getIntent().getStringExtra("telp");

        setTitle("Detail Data");
        nama.setText(nm);
        telpon.setText(tlp);
    }
}