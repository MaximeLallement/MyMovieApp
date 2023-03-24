package com.example.mymusicapplication.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymusicapplication.R;
import com.example.mymusicapplication.controller.Control;

public class GradeActivity extends AppCompatActivity {

    // Properties
    private Button buttonSetGrade;
    private EditText textInputComment;
    private int localGrade;
    private Control control;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        init();

        eventListenerBtnSetGrade();
        eventListenerBtnHome();
    }



    private void init(){

        this.control = Control.getInstance(this);

        buttonSetGrade = (Button) findViewById(R.id.btnSetGrade);
        textInputComment = (EditText) findViewById(R.id.textInputComment);


        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                localGrade = (int)v;

                //TextView textView = (TextView) findViewById(R.id.textView3);
                //textView.setText(String.format(" Rating is %d",Control.currentFilm.getGrade()));
            }
        });


    }

    private void eventListenerBtnHome() {
        // Event onClick
        Button btnHome = (Button) findViewById(R.id.btnHome2);
        btnHome.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(GradeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }




    private void eventListenerBtnSetGrade(){
        // Event onClick
        buttonSetGrade.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                if ( textInputComment.getText().length() <= 10 || localGrade < 1 ){
                    //Force Completion of Text and Grade
                    if ( localGrade < 1 ){
                        Toast.makeText(GradeActivity.this, "Please rate this movie", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(GradeActivity.this, "Please write a comment", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Log.d("chang rating", "onRatingChanged: " + localGrade);

                    Control.currentFilm.setComment(TextUtils.htmlEncode(textInputComment.getText().toString()));
                    Control.currentFilm.setGrade(localGrade);
                    control.update(Control.currentFilm);

                    Control.createNewFilm();
                    Intent intent = new Intent(GradeActivity.this , MainActivity.class );
                    startActivity(intent);
                }

            }
        });

    }
}