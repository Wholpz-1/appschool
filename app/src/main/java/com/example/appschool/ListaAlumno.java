package com.example.appschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.appschool.Entidades.EntAlu;
import com.example.appschool.Utilidades.Utilidades;

import java.util.ArrayList;

public class ListaAlumno extends AppCompatActivity {
    ArrayList<EntAlu> listAlumno;
    RecyclerView recycler;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alumno);

        conn=new ConexionSQLiteHelper(getApplicationContext(), "bd_alumnos", null,1 );
        listAlumno=new ArrayList<>();

        recycler= (RecyclerView) findViewById(R.id.recyclerAlumnos);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        consultarLista();

        ListaAlumnoAdapter adapter = new ListaAlumnoAdapter(listAlumno);
        recycler.setAdapter(adapter);


    }
    private  void consultarLista() {


        SQLiteDatabase db = conn.getReadableDatabase();

        EntAlu alumno = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_ALUMNO, null);

        while (cursor.moveToNext()) {

            alumno = new EntAlu();

            alumno.setId(cursor.getString(0));
            alumno.setNombre(cursor.getString(1));
            alumno.setApellido(cursor.getString(2));
            alumno.setTelefono(cursor.getString(3));
            alumno.setDireccion(cursor.getString(4));

            listAlumno.add((alumno));

        }

    }
}