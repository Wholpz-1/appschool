package com.example.appschool.Utilidades;

public class Utilidades {
    public static final String TABLA_ALUMNO = " alumno";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_APELLIDO = "apellido";
    public static final String CAMPO_TELFONO = "telefono";
    public static final String CAMPO_DIRECCION = "direccion";

    public static final String CREAR_TABLA_ALUMNO = " CREATE TABLE " + TABLA_ALUMNO + " ( " + CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CAMPO_NOMBRE + " TEXT, " + CAMPO_APELLIDO + " TEXT, " + CAMPO_TELFONO + " TEXT, " + CAMPO_DIRECCION + " TEXT ) ";



    public static final String TABLA_GRADO="grado";
    public static final String CGRADO_ID="id";
    public static final String CGRADO_DESCRIPCION="descripcion";

    public static final String CREAR_TABLA_GRADOS =" CREATE TABLE " + TABLA_GRADO + " (" + CGRADO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CGRADO_DESCRIPCION + " TEXT)";



}
