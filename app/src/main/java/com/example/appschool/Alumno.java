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

import com.example.appschool.Entidades.EntAlu;
import com.example.appschool.Utilidades.Utilidades;

import java.util.ArrayList;

public class Alumno extends AppCompatActivity {
    ArrayList<EntAlu> AlumnoList;
    ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_alumnos", null, 1);
    EditText campoNombre, campoApellido, campoTelefono, campoDireccion, busID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);


        campoNombre = (EditText) findViewById(R.id.campoNombre);
        campoApellido = (EditText) findViewById(R.id.campoApellido);
        campoTelefono = (EditText) findViewById(R.id.campoTelefono);
        campoDireccion = (EditText) findViewById(R.id.campoDireccion);
        busID = (EditText) findViewById(R.id.busID);


    }

    public void onClick(View view) {

        Intent miIntent = null;

        switch (view.getId()) {

            case R.id.list_alu:
                miIntent = new Intent(Alumno.this, ListaAlumno.class);
                break;

        }
        startActivity(miIntent);

    }


    public void onClickw(View view) {
        final String alerta21 = busID.getText().toString();
        if (alerta21.length() == 1) {
            busID.requestFocus();
            busID.setError("Este Campo debe estar vacion para registrar");
        } else {
            final String alerta = campoNombre.getText().toString();
            if (alerta.length() == 0) {
                campoNombre.requestFocus();
                campoNombre.setError("Ingrese Nombre");
            } else {
                final String alerta1 = campoApellido.getText().toString();
                if (alerta1.length() == 0) {
                    campoApellido.requestFocus();
                    campoApellido.setError("Ingrese Apellido");
                } else {
                    final String alerta2 = campoTelefono.getText().toString();
                    if (alerta2.length() == 0) {
                        campoTelefono.requestFocus();
                        campoTelefono.setError("Ingrese Telefono");
                    } else {
                        final String alerta3 = campoDireccion.getText().toString();
                        if (alerta3.length() == 0) {
                            campoDireccion.requestFocus();
                            campoDireccion.setError("Ingrese Direccion");
                        } else {
                            regAlumno();
                            campoNombre.getText().clear();
                            campoApellido.getText().clear();
                            campoTelefono.getText().clear();
                            campoDireccion.getText().clear();
                            Intent miIntent = new Intent(Alumno.this, ListaAlumno.class);
                            startActivity(miIntent);
                        }
                    }
                }
            }
        }
    }

    public void onClickBuscar(View view) {

        final String alerta = busID.getText().toString();
        if (alerta.length() == 0) {
            busID.requestFocus();
            busID.setError("Debe ingresar el codigo a buscarr");
        } else

                        busAlumno();

                    }



    public void onClickEditar(View view) {
        final String alerta21 = busID.getText().toString();
        if (alerta21.length() == 0) {
            busID.requestFocus();
            busID.setError("Debe buscar primero ");
        } else {
            final String alerta = campoNombre.getText().toString();
            if (alerta.length() == 0) {
                campoNombre.requestFocus();
                campoNombre.setError("Ingrese Nombre");
            } else {
                final String alerta1 = campoApellido.getText().toString();
                if (alerta1.length() == 0) {
                    campoApellido.requestFocus();
                    campoApellido.setError("Ingrese Apellido");
                } else {
                    final String alerta2 = campoTelefono.getText().toString();
                    if (alerta2.length() == 0) {
                        campoTelefono.requestFocus();
                        campoTelefono.setError("Ingrese Telefono");
                    } else {

                        final String alerta3 = campoDireccion.getText().toString();
                        if (alerta3.length() == 0) {
                            campoDireccion.requestFocus();
                            campoDireccion.setError("Ingrese Direccion");
                        } else {

                            Editaralu();
                            campoNombre.getText().clear();
                            campoApellido.getText().clear();
                            campoTelefono.getText().clear();
                            campoDireccion.getText().clear();
                            busID.getText().clear();
                            Intent miIntent = new Intent(Alumno.this, ListaAlumno.class);
                            startActivity(miIntent);
                        }
                    }
                }
            }
        }
    }
    public void onClickEliminar(View view) {
        final String alerta21 = busID.getText().toString();
        if (alerta21.length() == 0) {
            busID.requestFocus();
            busID.setError("Debe buscar primero ");
        } else {
            final String alerta = campoNombre.getText().toString();
            if (alerta.length() == 0) {
                campoNombre.requestFocus();
                campoNombre.setError("Ingrese Nombre");
            } else {
                final String alerta1 = campoApellido.getText().toString();
                if (alerta1.length() == 0) {
                    campoApellido.requestFocus();
                    campoApellido.setError("Ingrese Apellido");
                } else {
                    final String alerta2 = campoTelefono.getText().toString();
                    if (alerta2.length() == 0) {
                        campoTelefono.requestFocus();
                        campoTelefono.setError("Ingrese Telefono");
                    } else {

                        final String alerta3 = campoDireccion.getText().toString();
                        if (alerta3.length() == 0) {
                            campoDireccion.requestFocus();
                            campoDireccion.setError("Ingrese Direccion");
                        } else {

                            eliminarAlu();
                            campoNombre.getText().clear();
                            campoApellido.getText().clear();
                            campoTelefono.getText().clear();
                            campoDireccion.getText().clear();
                            busID.getText().clear();
                            Intent miIntent = new Intent(Alumno.this, ListaAlumno.class);
                            startActivity(miIntent);
                        }
                    }
                }
            }
        }
    }

    public void regAlumno() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_alumnos", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(Utilidades.CAMPO_NOMBRE, campoNombre.getText().toString());
            values.put(Utilidades.CAMPO_APELLIDO, campoApellido.getText().toString());
            values.put(Utilidades.CAMPO_TELFONO, campoTelefono.getText().toString());
            values.put(Utilidades.CAMPO_DIRECCION, campoDireccion.getText().toString());


        Long idResultant = db.insert(Utilidades.TABLA_ALUMNO, Utilidades.CAMPO_ID, values);

        Toast.makeText(getApplicationContext(), "REGISTRO DE ESTUDIANTE:" + idResultant, Toast.LENGTH_LONG).show();
    }

    public void busAlumno () {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_alumnos", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();

        String[] parametros = {busID.getText().toString()};

        try {
            Cursor cursor = db.rawQuery("SELECT "+ Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_APELLIDO+","+ Utilidades.CAMPO_TELFONO + ","+ Utilidades.CAMPO_DIRECCION+  " FROM " + Utilidades.TABLA_ALUMNO + " WHERE " + Utilidades.CAMPO_ID + "=? ", parametros);


            cursor.moveToFirst();


            campoNombre.setText(cursor.getString(0));
            campoApellido.setText(cursor.getString(1));
            campoTelefono.setText(cursor.getString(2));
            campoDireccion.setText(cursor.getString(3));


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Alumno no Registrado", Toast.LENGTH_LONG).show();
        }
    }

    public void Editaralu() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_alumnos", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {busID.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE, campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_APELLIDO, campoApellido.getText().toString());
        values.put(Utilidades. CAMPO_TELFONO, campoTelefono.getText().toString());
        values.put(Utilidades.CAMPO_DIRECCION, campoDireccion.getText().toString());

        db.update(Utilidades.TABLA_ALUMNO, values, Utilidades.CAMPO_ID + "=?", parametros);

        Toast.makeText(getApplicationContext(), " Alumno Actualizado", Toast.LENGTH_LONG).show();
        db.close();


    }
    public void eliminarAlu(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_alumnos", null, 1);
        SQLiteDatabase db= conn.getWritableDatabase();
        String[] parametros = {busID.getText().toString()};
        db.delete(Utilidades.TABLA_ALUMNO,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(), " Se ha Eliminado el Alumno",Toast.LENGTH_LONG).show();
        db.close();



    }



}
