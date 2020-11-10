package com.example.appschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.appschool.Entidades.EntGrado;
import com.example.appschool.Utilidades.Utilidades;

import java.util.ArrayList;

public class ListaGrado extends AppCompatActivity {
    ArrayList<EntGrado> listGrado;
    RecyclerView recycler;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_grado);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_alumnos", null,1);
        listGrado=new ArrayList<>();

        recycler=(RecyclerView)findViewById(R.id.recyclerGrados);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        consultarListagrado();

        ListaGradoAdapter adapter = new ListaGradoAdapter(listGrado);
        recycler.setAdapter(adapter);
    }
    private void consultarListagrado(){
        SQLiteDatabase db = conn.getReadableDatabase();
        EntGrado grado = null;
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_GRADO, null);
        while (cursor.moveToNext()){
            grado = new EntGrado();
            grado.setId(cursor.getString(0));
            grado.setDescripcion(cursor.getString(1));
            listGrado.add((grado));
        }
    }
}