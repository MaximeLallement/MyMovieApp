package com.example.mymusicapplication.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mymusicapplication.R;
import com.example.mymusicapplication.controller.Control;
import com.example.mymusicapplication.modele.Film;

public class DiscoverActivity extends AppCompatActivity {

    //Properties
    private Control control;
    private Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        init();
        eventListenerBtn( (Button) findViewById(R.id.btnHome), MainActivity.class);
        eventListenerBtn( (Button) findViewById(R.id.btnGrade), GradeActivity.class);
    }

    /**
     * Initialisation between GUI elements and local variable
     */
    private void init(){
        // Properties
        this.control = Control.getInstance(this);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(control.getCurrentFilm().getFilmName());
    }

    /**
     * Listen events on GUI element : btnDiscover
     */
    private void eventListenerBtn(Button btn, final Class activity){
        // Event onClick
        btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(DiscoverActivity.this , activity );
                startActivity(intent);
            }
        });
    }


}