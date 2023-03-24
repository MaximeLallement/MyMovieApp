package com.example.mymusicapplication.vue;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.example.mymusicapplication.R;
import com.example.mymusicapplication.controller.Control;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        eventListenerBtn((Button) findViewById(R.id.btnDiscover), DiscoverActivity.class);
        eventListenerBtn((Button) findViewById(R.id.btnHistory), HistoryActivity.class);

    }

    /**
     * Initialisation between GUI elements and local variable
     */
    private void init(){
        // Properties
        Control control = Control.getInstance(this);

    }

    /**
     * Listen events on GUI element : btnDiscover
     */
    private void eventListenerBtn(Button btn, final Class activity){
        // Event onClick
        btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                //Log.d("message","click ok sur le button discover ***************");
                Intent intent = new Intent(MainActivity.this , activity );
                startActivity(intent);
            }
        });
    }

}