package com.example.mymusicapplication.outils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    // Propriétés
    private String creation = " create table film ("
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " filmName STRING NOT NULL,"
            + " comment STRING,"
            + " grade INTEGER);";

    /**
     *
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     *  Si changement de base de données
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(creation);
    }

    /**
     *  Si changement de versions
     * @param sqLiteDatabase
     * @param i numéro de l'ancienne version
     * @param i1 numéro de la nouvelle version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
