package com.spnj.trender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button1;
    private Toolbar tb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });

        //inisialisasi
        button1 = findViewById(R.id.button1);
        //onclicknya
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, ContohBackToolbar.class);
                startActivity(a);
//                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });

        //inisialisasi toolbar
        tb = findViewById(R.id.tb);
        setSupportActionBar(tb);


    }
}
