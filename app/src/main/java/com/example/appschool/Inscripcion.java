package com.example.appschool;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.app.Dialog;

import com.example.appschool.Entidades.EntAlu;
import com.example.appschool.Entidades.EntGrado;
import com.example.appschool.Utilidades.Utilidades;



public class Inscripcion extends AppCompatActivity {


    ArrayList<String> listins;
    ArrayList<EntGrado> listagra;
    ConexionSQLiteHelper conn;
    ArrayList<EntAlu> inslista;
    ArrayList<String> insalu;
    AutoCompleteTextView complealu;
    AutoCompleteTextView complegra;

    Button btnfoto;
    ImageView  preimg;


    EditText  campoinssec, busID;

    EditText campoinsfecha;
    private static final int CAMERA_REQUEST = 200;
    private int mYearIni, mMonthIni, mDayIni, sYearIni, sMonthIni, sDayIni;
    static final int DATE_ID = 0;
    Calendar C = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscripcion);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_alumnos", null, 1);




        campoinssec = (EditText) findViewById(R.id.campoinssec);
        campoinsfecha = (EditText) findViewById(R.id.campoinsfecha);
        busID = (EditText) findViewById(R.id.busID);
        complealu = (AutoCompleteTextView) findViewById(R.id.complealu);
        complegra = (AutoCompleteTextView) findViewById(R.id.complegra);
        btnfoto = (Button) findViewById(R.id.btnfoto);
        preimg = (ImageView) findViewById(R.id.preimg);

        consultarlistagra();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, listins);
        complegra.setAdapter(adaptador);

        consultlistalu();
        ArrayAdapter<CharSequence> adapta = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, insalu);
        complealu.setAdapter(adapta);


        sMonthIni = C.get(Calendar.MONTH);
        sDayIni = C.get(Calendar.DAY_OF_MONTH);
        sYearIni = C.get(Calendar.YEAR);
        campoinsfecha = (EditText) findViewById(R.id.campoinsfecha);

        campoinsfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(DATE_ID);
            }
        });


    }

    public void onClick(View view) {

        Intent miIntent = null;

        switch (view.getId()) {

            case R.id.listins:
                miIntent = new Intent(Inscripcion.this, ListaInscripcion.class);
                break;

        }
        startActivity(miIntent);

    }


    public void regIns() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_alumnos", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();
        Bitmap image = ((BitmapDrawable)preimg.getDrawable()).getBitmap();
        byte[] data = getBitmapAsByteArray(image);
        ContentValues values = new ContentValues();


        values.put(Utilidades.INS_ALU, complealu.getText().toString());
        values.put(Utilidades.INS_GRA, complegra.getText().toString());
        values.put(Utilidades.INS_SEC, campoinssec.getText().toString());
        values.put(Utilidades.INS_FECHA, campoinsfecha.getText().toString());
        values.put(Utilidades.INS_IMG, data);



        Long idResultant = db.insert(Utilidades.TABLA_INSCRIPCION, Utilidades.INS_ID, values);

        Toast.makeText(getApplicationContext(), "REGISTRO DE INSCRIPCION:" + idResultant, Toast.LENGTH_LONG).show();

    }


    public void onClickw(View view) {
        final String alerta21 = busID.getText().toString();
        if (alerta21.length() == 1) {
            busID.requestFocus();
            busID.setError("Este campo debe estar vacio");
        } else {
            final String alerta = complealu.getText().toString();
            if (alerta.length() == 0) {
                complealu.requestFocus();
                complealu.setError("Ingrese Nombre");
            } else {
                final String alerta1 = complegra.getText().toString();
                if (alerta1.length() == 0) {
                    complegra.requestFocus();
                    complegra.setError("Ingrese Grado");
                } else {
                    final String alerta2 = campoinssec.getText().toString();
                    if (alerta2.length() == 0) {
                        campoinssec.requestFocus();
                        campoinssec.setError("Ingrese Seccion");
                    } else {
                        final String alerta3 = campoinsfecha.getText().toString();
                        if (alerta3.length() == 0) {
                            campoinsfecha.requestFocus();
                            campoinsfecha.setError("Ingrese Fecha");
                        } else {

                            regIns();

                            complealu.getText().clear();
                            complealu.getText().clear();
                            campoinssec.getText().clear();
                            campoinsfecha.getText().clear();

                            Intent miIntent = new Intent(Inscripcion.this, ListaInscripcion.class);
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
            busID.setError("Debe insgresar un codigo");
        } else {


            BusInscrip();


        }
    }

    public void onClickEditar(View view) {
        final String alerta21 = busID.getText().toString();
        if (alerta21.length() == 0) {
            busID.requestFocus();
            busID.setError("Debe insgresar un codigo");
        } else {
            final String alerta = complealu.getText().toString();
            if (alerta.length() == 0) {
                complealu.requestFocus();
                complealu.setError("Ingrese Nombre");
            } else {
                final String alerta1 = complegra.getText().toString();
                if (alerta1.length() == 0) {
                    complegra.requestFocus();
                    complegra.setError("Ingrese Grado");
                } else {
                    final String alerta2 = campoinssec.getText().toString();
                    if (alerta2.length() == 0) {
                        campoinssec.requestFocus();
                        campoinssec.setError("Ingrese Seccion");
                    } else {
                        final String alerta3 = campoinsfecha.getText().toString();
                        if (alerta3.length() == 0) {
                            campoinsfecha.requestFocus();
                            campoinsfecha.setError("Ingrese Fecha");
                        } else {

                            EditarIns();
                            busID.getText().clear();
                            complealu.getText().clear();
                            complegra.getText().clear();
                            campoinssec.getText().clear();
                            campoinsfecha.getText().clear();
                            Intent miIntent = new Intent(Inscripcion.this, ListaInscripcion.class);
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
            busID.setError("Debe insgresar un codigo");
        } else {
            final String alerta = complealu.getText().toString();
            if (alerta.length() == 0) {
                complealu.requestFocus();
                complealu.setError("Ingrese Nombre");
            } else {
                final String alerta1 = complegra.getText().toString();
                if (alerta1.length() == 0) {
                    complegra.requestFocus();
                    complegra.setError("Ingrese Grado");
                } else {
                    final String alerta2 = campoinssec.getText().toString();
                    if (alerta2.length() == 0) {
                        campoinssec.requestFocus();
                        campoinssec.setError("Ingrese Seccion");
                    } else {
                        final String alerta3 = campoinsfecha.getText().toString();
                        if (alerta3.length() == 0) {
                            campoinsfecha.requestFocus();
                            campoinsfecha.setError("Ingrese Fecha");
                        } else {

                            EliminarIns();
                            busID.getText().clear();
                            complealu.getText().clear();
                            complegra.getText().clear();
                            campoinssec.getText().clear();
                            campoinsfecha.getText().clear();
                            Intent miIntent = new Intent(Inscripcion.this, ListaInscripcion.class);
                            startActivity(miIntent);

                        }
                    }
                }
            }
        }
    }


    private void colocar_fecha() {
        campoinsfecha.setText(mDayIni + "-" + (mMonthIni + 1) + "-" + mYearIni + " ");
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYearIni = year;
                    mMonthIni = monthOfYear;
                    mDayIni = dayOfMonth;
                    colocar_fecha();

                }

            };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(this, mDateSetListener, sYearIni, sMonthIni, sDayIni);


        }


        return null;
    }

    public void BusInscrip() {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_alumnos", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {busID.getText().toString()};

        try {
            Cursor cursor = db.rawQuery("SELECT " + Utilidades.INS_ALU + "," + Utilidades.INS_GRA + "," + Utilidades.INS_SEC + "," + Utilidades.INS_FECHA + " FROM " + Utilidades.TABLA_INSCRIPCION + " WHERE " + Utilidades.INS_ID + "=? ", parametros);


            cursor.moveToFirst();


            complealu.setText(cursor.getString(0));
            complegra.setText(cursor.getString(1));
            campoinssec.setText(cursor.getString(2));
            campoinsfecha.setText(cursor.getString(3));


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Alumno no Inscrito", Toast.LENGTH_LONG).show();
        }
    }

    public void EditarIns() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_alumnos", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {busID.getText().toString()};
        Bitmap image = ((BitmapDrawable)preimg.getDrawable()).getBitmap();
        byte[] data = getBitmapAsByteArray(image);
        ContentValues values = new ContentValues();

        values.put(Utilidades.INS_ALU, complealu.getText().toString());
        values.put(Utilidades.INS_GRA, complegra.getText().toString());
        values.put(Utilidades.INS_SEC, campoinssec.getText().toString());
        values.put(Utilidades.INS_FECHA, campoinsfecha.getText().toString());
        values.put(Utilidades.INS_IMG, data);



        db.update(Utilidades.TABLA_INSCRIPCION, values, Utilidades.INS_ID + "=?", parametros);

        Toast.makeText(getApplicationContext(), " Inscripcion Actualizada", Toast.LENGTH_LONG).show();
        db.close();


    }

    public void EliminarIns() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_alumnos", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {busID.getText().toString()};
        db.delete(Utilidades.TABLA_INSCRIPCION, Utilidades.INS_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), " Se ha Eliminado la Inscripcion", Toast.LENGTH_LONG).show();
        db.close();


    }

    private void consultarlistagra() {
        SQLiteDatabase db = conn.getReadableDatabase();

        EntGrado alumno = null;
        listagra = new ArrayList<EntGrado>();

        Cursor cursor = db.rawQuery("SELECT " + Utilidades.CGRADO_DESCRIPCION + " FROM " + Utilidades.TABLA_GRADO, null);


        while (cursor.moveToNext()) {
            alumno = new EntGrado();
            alumno.setDescripcion(cursor.getString(0));




            listagra.add(alumno);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listins = new ArrayList<String>();

        for (int i = 0; i < listagra.size(); i++) {
            listins.add(listagra.get(i).getDescripcion());
        }
    }
    private void consultlistalu() {
        SQLiteDatabase db = conn.getReadableDatabase();

        EntAlu alumno = null;
        inslista = new ArrayList<EntAlu>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_ALUMNO, null);

        while (cursor.moveToNext()) {
            alumno = new EntAlu();
            alumno.setNombre(cursor.getString(1));
            alumno.setApellido(cursor.getString(2));





            inslista.add(alumno);
        }

        obtenerListalu();


    }

    private void obtenerListalu() {
        insalu = new ArrayList<String>();

        for (int i = 0; i < inslista.size(); i++) {
            insalu.add(inslista.get(i).getNombre() + " " + inslista.get(i).getApellido());

        }

    }
    public void abrircamara(View view){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, CAMERA_REQUEST);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap image = (Bitmap)extras.get("data");
            preimg.setImageBitmap(image);

        }

    }
    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }






}




