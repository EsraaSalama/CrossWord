package com.example.esraa.crossword;

import android.content.ClipData;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;


public class tocolor extends AppCompatActivity {
    private TextView green, yellow, blue, red, txtred, txtgreen, txtyellow, txtblue;
    private MediaPlayer bl, gr, ye, re, clic;
    private ImageButton next;
    private Chronometer time2;
    private Long lastPause;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tocolor);
        time2 = (Chronometer) findViewById(R.id.counter2);
        Intent data = this.getIntent();
        time2.setBase(data.getExtras().getLong("base"));
        time2.start();


        next = (ImageButton) findViewById(R.id.imageButton2);
        next.setOnClickListener(startActivity4);
        txtblue = (TextView) findViewById(R.id.txt_blue);
        txtred = (TextView) findViewById(R.id.txt_red);
        txtgreen = (TextView) findViewById(R.id.txt_green);
        txtyellow = (TextView) findViewById(R.id.txt_yellow);
        green = (TextView) findViewById(R.id.green);
        blue = (TextView) findViewById(R.id.blue);
        yellow = (TextView) findViewById(R.id.yellow);
        red = (TextView) findViewById(R.id.red);
        txtyellow.setOnTouchListener(touchListener);
        txtred.setOnTouchListener(touchListener);
        txtgreen.setOnTouchListener(touchListener);
        txtblue.setOnTouchListener(touchListener);
        bl = MediaPlayer.create(getApplicationContext(), R.raw.blue);
        gr = MediaPlayer.create(getApplicationContext(), R.raw.green);
        re = MediaPlayer.create(getApplicationContext(), R.raw.red);
        ye = MediaPlayer.create(getApplicationContext(), R.raw.yellow);
        clic = MediaPlayer.create(getApplicationContext(), R.raw.clic);
        yellow.setOnDragListener(dragListener1);
        red.setOnDragListener(dragListener2);
        blue.setOnDragListener(dragListener3);
        green.setOnDragListener(dragListener4);
    }
    View.OnClickListener startActivity4 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(tocolor.this,toshape.class);
            i.putExtra("baseoftime3",time2.getBase());
            startActivity(i);
            finish();
        }
    };

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("stringy", yellow.getText().toString());
        savedInstanceState.putString("stringr", red.getText().toString());
        savedInstanceState.putString("stringb", blue.getText().toString());
        savedInstanceState.putString("stringg", green.getText().toString());
        savedInstanceState.putLong("long1", time2.getBase());
        savedInstanceState.putLong("long2", lastPause);


    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        yellow.setText(savedInstanceState.getString("stringy"));
        green.setText(savedInstanceState.getString("stringg"));
        red.setText(savedInstanceState.getString("stringr"));
        blue.setText(savedInstanceState.getString("stringb"));
        if (savedInstanceState.getLong("long2")
                == 0) {
            time2.setBase(time2.getBase() + SystemClock.elapsedRealtime() - savedInstanceState.getLong("long2"));
             }
        {
            time2.setBase(savedInstanceState.getLong("long1") + SystemClock.elapsedRealtime() - savedInstanceState.getLong("long2"));
        }


        if (!"".equals(yellow.getText()) && !"".equals(green.getText()) && !"".equals(blue.getText()) && !"".equals(red.getText()))
            next.setVisibility(View.VISIBLE);


    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onPause()
    { super.onPause();
        lastPause = SystemClock.elapsedRealtime();
        time2.stop();
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        time2.setBase(time2.getBase() + SystemClock.elapsedRealtime() - lastPause);
        time2.start();
    }







    View.OnTouchListener touchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                clic.start();
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                ClipData data = ClipData.newPlainText("", "");
                v.startDrag(data, shadowBuilder, v, 0);
                return true;
            } else {
                return false;
            }
        }

    };
    View.OnDragListener dragListener1 = new View.OnDragListener(){
        @Override
        public boolean onDrag (View v , DragEvent event){
            int drageEvent = event.getAction();
            switch (drageEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();


                    if (view.getId()==R.id.txt_yellow  ){
                        ye.start();
                        yellow.setText("yellow");
                    }
                    if (!"".equals(yellow.getText())&&!"".equals(green.getText())&&!"".equals(blue.getText())&&!"".equals(red.getText()))
                        next.setVisibility(View.VISIBLE);

                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;

            }
            return true ;
        }
    };
    View.OnDragListener dragListener2 = new View.OnDragListener(){
        @Override
        public boolean onDrag (View v , DragEvent event){
            int drageEvent = event.getAction();
            switch (drageEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();


                    if (view.getId()==R.id.txt_red ){
                        re.start();
                        red.setText("red");
                    }
                    if (!"".equals(yellow.getText())&&!"".equals(green.getText())&&!"".equals(blue.getText())&&!"".equals(red.getText()))
                        next.setVisibility(View.VISIBLE);

                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;

            }
            return true ;
        }
    };
    View.OnDragListener dragListener3 = new View.OnDragListener(){
        @Override
        public boolean onDrag (View v , DragEvent event){
            int drageEvent = event.getAction();
            switch (drageEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();


                    if (view.getId()==R.id.txt_blue ){
                        bl.start();
                        blue.setText("blue");
                    }
                    if (!"".equals(yellow.getText())&&!"".equals(green.getText())&&!"".equals(blue.getText())&&!"".equals(red.getText()))
                        next.setVisibility(View.VISIBLE);

                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;

            }
            return true ;
        }
    };
    View.OnDragListener dragListener4 = new View.OnDragListener(){
        @Override
        public boolean onDrag (View v , DragEvent event){
            int drageEvent = event.getAction();
            switch (drageEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();


                    if (view.getId()==R.id.txt_green  ){
                        gr.start();
                        green.setText("green");
                    }
                    if (!"".equals(yellow.getText())&&!"".equals(green.getText())&&!"".equals(blue.getText())&&!"".equals(red.getText()))
                        next.setVisibility(View.VISIBLE);

                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;

            }
            return true ;
        }
    };


}