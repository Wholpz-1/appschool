package com.example.appschool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void onClick(View view){

            Intent miIntent = null;

            switch (view.getId()) {
                case R.id.img_alumno:
                    miIntent = new Intent(MainActivity.this, Alumno.class);
                    break;

                case R.id.Line_grado:
                    miIntent = new Intent(MainActivity.this, Grado.class);
                    break;


            }
            startActivity(miIntent);
        }

    }
