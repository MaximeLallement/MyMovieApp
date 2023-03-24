package com.example.mymusicapplication.vue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mymusicapplication.R;
import com.example.mymusicapplication.controller.Control;
import com.example.mymusicapplication.modele.LocalAccess;
import com.example.mymusicapplication.outils.RecyclerAdapter;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ArrayList<String> filmNameList;
    private RecyclerView recyclerView;
    private LocalAccess localAccess;
    private Control control;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();

        setAdapter();
    }

    public void init(){

        filmNameList = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        int star = extras.getInt("star");
        Control control = Control.getInstance(this);


        localAccess = new LocalAccess(getApplicationContext());


        Log.d("TAG", "onCreate: "+ localAccess.filmsByRating(star));
        filmNameList = localAccess.filmsByRating(star);
        if (filmNameList.isEmpty()){
            filmNameList.add("You have no movie with this rating yet");
        }
        Log.d("TAG", "onCreate: "+ filmNameList);

        recyclerView = findViewById(R.id.recyViewFilmList);

        eventListenerBtnHome();

    }

    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(filmNameList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        Log.d("TAG", "setAdapter: " + recyclerView);
        Log.d("TAG", "setAdapter: " + layoutManager);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private void eventListenerBtnHome() {
        // Event onClick
        Button btnHome = (Button) findViewById(R.id.btnHome3);
        btnHome.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}