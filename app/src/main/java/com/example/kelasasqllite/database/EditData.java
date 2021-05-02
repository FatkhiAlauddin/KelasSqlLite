package com.example.kelasasqllite.database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kelasasqllite.MainActivity;
import com.example.kelasasqllite.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class EditData extends AppCompatActivity {
    private TextInputEditText tNama, tTelpon;
    private Button simpanBtn;
    String nm,id,tlp;
    DBController controller = new DBController(this);

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdata);
        tNama = findViewById(R.id.edNama);
        tTelpon = findViewById(R.id.edTelpon);
        simpanBtn = findViewById(R.id.simpanBtn);

        id = getIntent().getStringExtra("id");
        nm = getIntent().getStringExtra("nama");
        tlp = getIntent().getStringExtra("telp");


        setTitle("Edit Data");
        tNama.setText(nm);
        tTelpon.setText(tlp);
        simpanBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (tNama.getText().toString().equals("") || tTelpon.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Data belum lengkap!", Toast.LENGTH_LONG).show();
                } else {
                    nm = tNama.getText().toString();
                    tlp = tTelpon.getText().toString();
                    HashMap<String, String> val = new HashMap<>();
                    val.put("id", id);
                    val.put("nama", nm);
                    val.put("telp", tlp);
                    controller.editData(val);
                    callHome();
                }
            }
        });
    }
    public void callHome(){
        Intent i = new Intent(EditData.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}