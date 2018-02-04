package com.example.esraa.crossword;

import android.content.ClipData;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;


public class words extends AppCompatActivity {
    private TextView r, n, u, e, txtR, txtN, txtE, txtU;
    private MediaPlayer clic, drag;
    private ImageButton next;
    private Chronometer timeCounter;
    private long lastPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oneword);
        timeCounter = (Chronometer) findViewById(R.id.counter1);
        timeCounter.start();







        next = (ImageButton) findViewById(R.id.imageButton1);
        next.setOnClickListener(startActivity3);
        r = (TextView) findViewById(R.id.r);
        n = (TextView) findViewById(R.id.n);
        u = (TextView) findViewById(R.id.u);
        e = (TextView) findViewById(R.id.e);
        r.setOnTouchListener(touchListener);
        n.setOnTouchListener(touchListener);
        u.setOnTouchListener(touchListener);
        e.setOnTouchListener(touchListener);
        txtR = (TextView) findViewById(R.id.txt_R);
        txtE = (TextView) findViewById(R.id.txt_E);
        txtN = (TextView) findViewById(R.id.txt_N);
        txtU = (TextView) findViewById(R.id.txt_U);
        txtR.setOnDragListener(dragListener1);
        txtN.setOnDragListener(dragListener2);
        txtU.setOnDragListener(dragListener3);
        txtE.setOnDragListener(dragListener4);

        clic = MediaPlayer.create(getApplicationContext(), R.raw.clic);
        drag = MediaPlayer.create(getApplicationContext(), R.raw.drag);


    }

    View.OnClickListener startActivity3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(words.this,tocolor.class);
            i.putExtra("base",timeCounter.getBase());
            startActivity(i);
            finish();
        }
    };

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("MyString_txtU", txtU.getText().toString());
        savedInstanceState.putString("MyString_txtN", txtN.getText().toString());
        savedInstanceState.putString("MyString_txtR", txtR.getText().toString());
        savedInstanceState.putString("MyString_txtE", txtE.getText().toString());
        savedInstanceState.putLong("long1",timeCounter.getBase());
        savedInstanceState.putLong("long2",lastPause);


    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        txtU.setText(savedInstanceState.getString("MyString_txtU"));
        txtN.setText(savedInstanceState.getString("MyString_txtN"));
        txtR.setText(savedInstanceState.getString("MyString_txtR"));
        txtE.setText(savedInstanceState.getString("MyString_txtE"));
        if (savedInstanceState.getLong("long2")
                == 0) {
            timeCounter.setBase(timeCounter.getBase() + SystemClock.elapsedRealtime() - savedInstanceState.getLong("long2"));
        }
        {
            timeCounter.setBase(savedInstanceState.getLong("long1") + SystemClock.elapsedRealtime() - savedInstanceState.getLong("long2"));
        }

        if (!"".equals(txtE.getText())&&!"".equals(txtN.getText())&&!"".equals(txtU.getText())&&!"".equals(txtR.getText()))
            next.setVisibility(View.VISIBLE);


    }
    @Override
    protected void onPause(){
        super.onPause();
        lastPause = SystemClock.elapsedRealtime();
        timeCounter.stop();
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        timeCounter.setBase(timeCounter.getBase() + SystemClock.elapsedRealtime() - lastPause);
        timeCounter.start();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


    View.OnTouchListener touchListener = new View.OnTouchListener(){

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                clic.start();
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                ClipData data = ClipData.newPlainText("","");
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


                    if (view.getId()==R.id.r  ){
                        drag.start();
                        txtR.setText("r");
                    }
                    if (!"".equals(txtE.getText())&&!"".equals(txtN.getText())&&!"".equals(txtU.getText())&&!"".equals(txtR.getText()))
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


                    if (view.getId()==R.id.n  ){
                        drag.start();
                        txtN.setText("n");
                    }
                    if (!"".equals(txtE.getText())&&!"".equals(txtN.getText())&&!"".equals(txtU.getText())&&!"".equals(txtR.getText()))
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


                    if (view.getId()==R.id.u  ){
                        drag.start();
                        txtU.setText("u");
                    }
                    if (!"".equals(txtE.getText())&&!"".equals(txtN.getText())&&!"".equals(txtU.getText())&&!"".equals(txtR.getText()))
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


                    if (view.getId()==R.id.e  ){
                        drag.start();
                        txtE.setText("e");
                    }
                    if (!"".equals(txtE.getText())&&!"".equals(txtN.getText())&&!"".equals(txtU.getText())&&!"".equals(txtR.getText()))
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
