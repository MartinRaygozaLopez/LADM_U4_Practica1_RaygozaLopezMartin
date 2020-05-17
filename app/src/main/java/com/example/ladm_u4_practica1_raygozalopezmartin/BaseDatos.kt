package com.example.ladm_u4_practica1_raygozalopezmartin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(context : Context,
                nombreBaseDatos : String,
                cursorFactory: SQLiteDatabase.CursorFactory?,
                numeroVersion : Int) : SQLiteOpenHelper(context, nombreBaseDatos, cursorFactory, numeroVersion) {

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            /*
                ID = 1 -> MENSAJE PARA PERSONAS AGRADABLES
                ID = 2 -> MENSAJE PARA PERSONAS DESAGRADABLES

                TIPO = 1 -> TELEFONOS DE PERSONAS AGRADABLES
                TIPO = 2 -> TELEFONOS DE PERSONAS DESAGRADABLES
             */
            db!!.execSQL("CREATE TABLE MENSAJES(ID INTEGER PRIMARY KEY AUTOINCREMENT, MENSAJE VARCHAR(500))")
            db.execSQL("CREATE TABLE TELEFONOS(ID INTEGER PRIMARY KEY AUTOINCREMENT, TELEFONO VARCHAR(20), TIPO VARCHAR(5))")
            db.execSQL("CREATE TABLE LLAMADASPERDIDAS(ID INTEGER PRIMARY KEY AUTOINCREMENT, TELEFONO VARCHAR(20), STATUS BOOLEAN)")


        } catch (error : SQLiteException){ }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}