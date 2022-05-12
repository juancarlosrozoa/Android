package com.example.juan_rozo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminDB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String SQL_CREATE = "create table usuario (correo text primary key, contraseña text)";
    public static final String SQL_CREATE_BUYTYPE = "create table tipocompra (tipoCompra text primary key, ciudad text)";


    public AdminDB( Context context,  String name, int version) {
        super(context, name,null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
        db.execSQL(SQL_CREATE_BUYTYPE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("drop table if exists usuario");
    db.execSQL(SQL_CREATE);
    db.execSQL("drop table if exists tipocompra");
    db.execSQL(SQL_CREATE_BUYTYPE);
    }
}
