package com.example.appschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appschool.Utilidades.Utilidades;

public class Grado extends AppCompatActivity {
EditText campoDescripcion , busID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grado);

        campoDescripcion=(EditText)findViewById(R.id.campoDescripcion);
        busID=(EditText) findViewById(R.id.busID);

    }
    public void onClick(View view) {

        Intent miIntent = null;

        switch (view.getId()) {
            case R.id.regrado:
                regrado();
                miIntent = new Intent(this, Grado.class);
                break;

            case R.id.listgrado:
                miIntent = new Intent(Grado.this, ListaGrado.class);
                break;


        }
        startActivity(miIntent);

    }
    public void regrado() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_grado", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utilidades.CGRADO_DESCRIPCION,campoDescripcion.getText().toString());


        Long idResultant = db.insert(Utilidades.TABLA_GRADO, Utilidades.CGRADO_ID, values);

        Toast.makeText(getApplicationContext(), "REGISTRO DE GRADO:" + idResultant, Toast.LENGTH_LONG).show();
    }
    public void busGrado (View view) {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_grado", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();

        String[] parametros = {busID.getText().toString()};

        try {
            Cursor cursor = db.rawQuery("SELECT "+ Utilidades.CGRADO_DESCRIPCION +  " FROM " + Utilidades.TABLA_GRADO + " WHERE " + Utilidades.CGRADO_ID + "=? ", parametros);


            cursor.moveToFirst();


            campoDescripcion.setText(cursor.getString(0));


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Grado no Registrado", Toast.LENGTH_LONG).show();
        }
    }

    public void Editargra(View view) {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_grado", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {busID.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CGRADO_DESCRIPCION, campoDescripcion.getText().toString());


        db.update(Utilidades.TABLA_GRADO, values, Utilidades.CGRADO_ID + "=?", parametros);

        Toast.makeText(getApplicationContext(), " Grado Actualizado", Toast.LENGTH_LONG).show();
        db.close();


    }
    public void Eliminargra(View view){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_grado", null, 1);
        SQLiteDatabase db= conn.getWritableDatabase();
        String[] parametros = {busID.getText().toString()};
        db.delete(Utilidades.TABLA_GRADO,Utilidades.CGRADO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(), " Se ha Eliminado el Grado",Toast.LENGTH_LONG).show();
        db.close();



    }




}