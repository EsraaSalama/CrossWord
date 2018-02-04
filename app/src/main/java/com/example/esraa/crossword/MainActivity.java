package com.example.esraa.crossword;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   private Button start;
   public EditText nome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start_btn);
        start.setOnClickListener(startActivity2);
        nome=(EditText)findViewById(R.id.your_name);


    }
    View.OnClickListener startActivity2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(MainActivity.this,words.class);
            startActivity(i);
            finish();
        }
    };
















}


