package com.example.appschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.appschool.Entidades.EntIns;
import com.example.appschool.Utilidades.Utilidades;

import java.util.ArrayList;

public class ListaInscripcion extends AppCompatActivity {
    ArrayList<EntIns> listInscripcion;
    RecyclerView recycler;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_inscripcion);

        conn=new ConexionSQLiteHelper(getApplicationContext(), "bd_alumnos", null, 1);
        listInscripcion=new ArrayList<>();

        recycler=(RecyclerView) findViewById(R.id.recyclerIncripcion);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        consultarlista();

        ListaInscripcionAdapter adapter = new ListaInscripcionAdapter(listInscripcion);
        recycler.setAdapter(adapter);

    }

    private void consultarlista(){
        SQLiteDatabase db = conn.getWritableDatabase();
        EntIns alumno = null;

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_INSCRIPCION, null);

        while (cursor.moveToNext()){
            alumno = new EntIns();

            alumno.setId(cursor.getString(0));
            alumno.setNombrealu(cursor.getString(1));
            alumno.setGradoalu(cursor.getString(2));
            alumno.setSecalu(cursor.getString(3));
            alumno.setFehca_ins(cursor.getString(4));
            byte[] imgBytes = alumno.setInsimg(cursor.getBlob(5));
            BitmapFactory.decodeByteArray(imgBytes,0,imgBytes.length);

            listInscripcion.add(alumno);
        }
    }
}