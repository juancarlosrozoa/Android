package com.example.juan_rozo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Cargando extends AppCompatActivity {

    private static int TIMEOUT=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargando);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Cargando.this,MainActivity.class);
                startActivity(intent);
            }
        },TIMEOUT);

    }
}