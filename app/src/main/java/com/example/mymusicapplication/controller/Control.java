package com.example.mymusicapplication.controller;

import android.content.Context;
import android.util.Log;

import com.example.mymusicapplication.modele.Film;
import com.example.mymusicapplication.modele.LocalAccess;
import com.example.mymusicapplication.outils.API;

import java.util.concurrent.ExecutionException;


public final class Control {

    private static Control instance = null;
    private static LocalAccess localAccess;
    public static Film currentFilm = null;
    public static String type = "";

    // Constructor
    private Control(){
        // Call parent constructor as there is no parent call nothing
        // Placed there so class constructor isn't empty
        super();
    }

    public static final Control getInstance(Context context){
        if( Control.instance == null ){
            Control.instance = new Control();
            localAccess = new LocalAccess(context);
            currentFilm = instance.getCurrentFilm();
        }
            return Control.instance;
    }

    public static void createNewFilm(){
        Log.d("2", "getCurrentFilm: Start get a new CurrentFilm");

        currentFilm = new Film("NotTheFilmName","null",-1);

        int min = 1000000;
        int max = 9000000;


        while( currentFilm.getFilmName().equals("NotTheFilmName") || !type.equals("movie")){
            int id = (int)(Math.random()*(max-min+1)+min);
            Log.d("TAG", "createNewFilm: " + localAccess.doesEntryExist(currentFilm.getFilmName()));
            if( !localAccess.doesEntryExist(currentFilm.getFilmName())){
                try {
                    new API().execute("https://www.omdbapi.com/?apikey=96033f3b&i=tt"+id).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.d("WhileLoop", "createNewFilm: type = " + type);
            Log.d("WhileLoop", "createNewFilm: filmName = " + currentFilm.getFilmName());

        }

        localAccess.add(currentFilm);
        Log.d("ControlGetCurrentFilm", "getCurrentFilm: CurrentFilm is new");
    }

    public Film getCurrentFilm() {

        Log.d("1", "getCurrentFilm: Start get CurrentFilm");
        if( currentFilm == null){
            if ( localAccess.lastEntry() != null ){
                createNewFilm();
            }else{
                createNewFilm();

                //currentFilm = localAccess.lastEntry();
                Log.d("ControlGetCurrentFilm", "getCurrentFilm: CurrentFilm is last entry");
            }
        }
        return currentFilm;
    }

    public void update(Film film){
        localAccess.update(film);
    }
}
