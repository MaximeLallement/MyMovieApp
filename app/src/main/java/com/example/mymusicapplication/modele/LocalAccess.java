package com.example.mymusicapplication.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.example.mymusicapplication.outils.MySQLiteOpenHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LocalAccess {

    // Properties
    private String nameDatabase = "databaseFilm.sqlite";
    private int versionDatabase = 1;
    private MySQLiteOpenHelper accessDatabase;
    private SQLiteDatabase dataBase;

    /**
     * Constructor
     * @param context instantiate a context to provide
     */
    public LocalAccess(Context context){
        accessDatabase = new MySQLiteOpenHelper(context,nameDatabase,null,versionDatabase);
    }

    /**
     * Add a film to Database
     * @param film The added film
     */
    public void add(Film film){
        dataBase = accessDatabase.getWritableDatabase();
        String req = " INSERT INTO film ( filmName , comment , grade ) VALUES";

        req += "( '"+ TextUtils.htmlEncode( film.getFilmName() )+"','"+ film.getComment() +"','"+ film.getGrade() +"')";
        dataBase.execSQL(req);
    }

    public void update(Film film){
        Log.d("OnUpdating", "update: Start updating");
        dataBase = accessDatabase.getWritableDatabase();
        String req ="UPDATE film SET " +
                "comment =  '" + film.getComment() +
                "', grade = '" + film.getGrade()+
                "' WHERE filmName = '" + film.getFilmName() + "';";
        dataBase.execSQL(req);
        Log.d("OnUpdating", "update: End updating");

    }

    /**
     * Select the last film entered in the database
     * @return
     */
    public Film lastEntry(){
        dataBase = accessDatabase.getReadableDatabase();
        Film currentFilm = null;
        String req = "SELECT * FROM film";
        Cursor cursor = dataBase.rawQuery(req,null);
        cursor.moveToLast();
        if(!cursor.isAfterLast()){
            String filmName = cursor.getString( 1);
            String comment = cursor.getString(2);
            int grade = cursor.getInt(3);
            currentFilm = new Film( filmName,comment,grade);
        }
        cursor.close();
        return currentFilm;
    }

    /**
     *
     * @param filmName
     * @return Return true if there is a movie with the same title
     */
    public boolean doesEntryExist(String filmName){
        dataBase = accessDatabase.getReadableDatabase();
        String req = "SELECT * FROM film WHERE filmname = '" + filmName +"';" ;

        Cursor cursor = dataBase.rawQuery(req,null);
        cursor.moveToLast();
        if(!cursor.isAfterLast()){
            cursor.close();
            return true;
        }else{
            cursor.close();
            return false;
        }
    }

    public ArrayList<String> filmsByRating(int starValue){
        ArrayList<String> queryFilmName = new ArrayList<String>();
        dataBase = accessDatabase.getReadableDatabase();
        String req = "SELECT * FROM film WHERE grade = '" + starValue + "';";

        Cursor cursor = dataBase.rawQuery(req,null);
        if (cursor.getCount() != 0){

            cursor.moveToFirst();
            for(int i = 0; i < cursor.getCount(); i++){
                queryFilmName.add(cursor.getString(1));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return queryFilmName;
    }

}
