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

                case R.id.Line_ins:
                    miIntent = new Intent(MainActivity.this, Inscripcion.class);
                    break;
                case R.id.listains:
                    miIntent = new Intent(MainActivity.this, ListaInscripcion.class);
                    break;
                case R.id.listagra:
                    miIntent = new Intent(MainActivity.this, ListaGrado.class);
                    break;
                case R.id.listalu:
                    miIntent = new Intent(MainActivity.this, ListaAlumno.class);
                    break;

            }
            startActivity(miIntent);
        }

    }
