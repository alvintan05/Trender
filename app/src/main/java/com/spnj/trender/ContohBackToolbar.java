package com.spnj.trender;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class ContohBackToolbar extends AppCompatActivity {
    private Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contoh_back_toolbar);

        //inisialisasi toolbar
        tb = findViewById(R.id.tb);
        setSupportActionBar(tb);
        //buat ada tombol back
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
