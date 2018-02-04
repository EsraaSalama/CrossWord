package com.example.esraa.crossword;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;


public class toend extends AppCompatActivity {
    private Chronometer counter;
    private long lastPause;
    Button x ,c ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finished);
        this.setFinishOnTouchOutside(false);
        lastPause = SystemClock.elapsedRealtime();
        counter = (Chronometer) findViewById(R.id.chronometer);
        Intent data = this.getIntent();
        counter.setBase(data.getExtras().getLong("base"));
        x=(Button) findViewById(R.id.close);
        c=(Button) findViewById(R.id.retry);
        x.setOnClickListener(close);
        c.setOnClickListener(retry);

    }

    View.OnClickListener  close = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
    View.OnClickListener  retry = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(toend.this,MainActivity.class);
            startActivity(i);
            finish();
        }
    };



    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong("long1", counter.getBase());
        savedInstanceState.putLong("long2", lastPause);


    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.getLong("long2")
                == 0)
            counter.setBase(counter.getBase() + SystemClock.elapsedRealtime() - savedInstanceState.getLong("long2"));
           else counter.setBase(savedInstanceState.getLong("long1") + SystemClock.elapsedRealtime() - savedInstanceState.getLong("long2"));
        }}






