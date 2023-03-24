package com.example.mymusicapplication.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mymusicapplication.R;

public class HistoryActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        init();
    }

    private void init(){
        eventListenerBtn( (Button) findViewById(R.id.btn1star),1);
        eventListenerBtn( (Button) findViewById(R.id.btn2star),2);
        eventListenerBtn( (Button) findViewById(R.id.btn3star),3);
        eventListenerBtn( (Button) findViewById(R.id.btn4star),4);
        eventListenerBtn( (Button) findViewById(R.id.btn5star),5);

    }

    private void eventListenerBtn(Button btn, int starValue){
        // Event onClick
        btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(HistoryActivity.this , ListActivity.class );
                intent.putExtra("star", starValue);

                startActivity(intent);
            }
        });
    }
}