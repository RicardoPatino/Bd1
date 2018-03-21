package com.example.usuario1.basedatos1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Usuario1 on 17/03/2018.
 */

public class AprendizSQLiteHelper extends SQLiteOpenHelper {


    String sqlCreate= "CREATE TABLE Aprendiz(codigo INTEGER, nombre TEXT, apellido TEXT, Direccion TEXT)";

    public AprendizSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Aprendiz");
        sqLiteDatabase.execSQL(sqlCreate);

    }
}
