package com.example.mymusicapplication.modele;

import android.util.Log;

public class Film {
    // Properties

    private String filmName;
    private String comment;
    private int grade;

    public Film(String filmName,String comment, int grade){
        this.setFilmName(filmName);
        this.setComment(comment);
        this.setGrade(grade);
    }
    // Setters

    public void setFilmName(String film) {
        this.filmName = film;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setGrade(int grade) {

        this.grade = grade;
        Log.d("OnGradeIsSet", "setGrade: Grade has been set " + this.grade);
    }

    // Getters

    public String getFilmName() {
        return filmName;
    }

    public String getComment() {
        return comment;
    }

    public int getGrade() {
        return grade;
    }
}
