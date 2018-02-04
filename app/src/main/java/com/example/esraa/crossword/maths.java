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

import org.w3c.dom.Text;


public class maths extends AppCompatActivity {
    private Chronometer time2;
    private TextView big, small, equal, txtequal, txtsmall, txtbig;
    private ImageButton next;
    private long lastPause;
    MediaPlayer clic, drag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ma);
        time2 = (Chronometer) findViewById(R.id.counter44);
        Intent data = this.getIntent();
        time2.setBase(data.getExtras().getLong("base"));
        time2.start();

        clic = MediaPlayer.create(getApplicationContext(), R.raw.clic);
        drag = MediaPlayer.create(getApplicationContext(), R.raw.drag);

        big = (TextView) findViewById(R.id.big);
        small = (TextView) findViewById(R.id.small);
        equal = (TextView) findViewById(R.id.equal);
        txtbig = (TextView) findViewById(R.id.txtbig);
        txtsmall = (TextView) findViewById(R.id.txtsmall);
        txtequal = (TextView) findViewById(R.id.txtequal);

        big.setOnTouchListener(touchListener);
        small.setOnTouchListener(touchListener);
        equal.setOnTouchListener(touchListener);

        txtequal.setOnDragListener(dragListenerequal);
        txtbig.setOnDragListener(dragListenerbig);
        txtsmall.setOnDragListener(dragListenersmall);

        next = (ImageButton) findViewById(R.id.imageButton44);
        next.setOnClickListener(startfinish);

    }
    View.OnClickListener startfinish = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
      Intent i = new Intent (maths.this,toend.class);
      i.putExtra("base",time2.getBase());
            startActivity(i);
            finish();

        }
    };

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("stringy", txtbig.getText().toString());
        savedInstanceState.putString("stringr", txtsmall.getText().toString());
        savedInstanceState.putString("stringb", txtequal.getText().toString());
        savedInstanceState.putLong("long1", time2.getBase());
        savedInstanceState.putLong("long2", lastPause);


    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        txtbig.setText(savedInstanceState.getString("stringy"));
        txtsmall.setText(savedInstanceState.getString("stringr"));
        txtequal.setText(savedInstanceState.getString("stringb"));

        if (savedInstanceState.getLong("long2")
                == 0) {
            time2.setBase(time2.getBase() + SystemClock.elapsedRealtime() - savedInstanceState.getLong("long2"));
        }
        {
            time2.setBase(savedInstanceState.getLong("long1") + SystemClock.elapsedRealtime() - savedInstanceState.getLong("long2"));
        }


        if (!"".equals(txtbig.getText()) && !"".equals(txtsmall.getText()) && !"".equals(txtequal.getText()))
            next.setVisibility(View.VISIBLE);
        if (!"".equals(txtequal.getText()))
            equal.setVisibility(View.INVISIBLE);
        if (!"".equals(txtsmall.getText()))
            small.setVisibility(View.INVISIBLE);
        if (!"".equals(txtbig.getText()))
            big.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lastPause = SystemClock.elapsedRealtime();
        time2.stop();
    }
    @Override
    protected void onStop() {
        super.onStop();
        lastPause = SystemClock.elapsedRealtime();
        time2.stop();
    }

    @Override
    protected void onRestart() {
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

    View.OnDragListener dragListenerbig = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int drageEvent = event.getAction();
            switch (drageEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();


                    if (view.getId() == R.id.big) {
                        drag.start();
                        txtbig.setText(">");
                        big.setVisibility(View.INVISIBLE);
                    }
                    if (!"".equals(txtbig.getText()) && !"".equals(txtsmall.getText()) && !"".equals(txtequal.getText()))
                        next.setVisibility(View.VISIBLE);


                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;

            }
            return true;
        }
    };
    View.OnDragListener dragListenersmall = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int drageEvent = event.getAction();
            switch (drageEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();


                    if (view.getId() == R.id.small) {
                        drag.start();
                        txtsmall.setText("<");
                        small.setVisibility(View.INVISIBLE);
                    }
                    if (!"".equals(txtbig.getText()) && !"".equals(txtsmall.getText()) && !"".equals(txtequal.getText()))
                        next.setVisibility(View.VISIBLE);


                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;

            }
            return true;
        }
    };
    View.OnDragListener dragListenerequal = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int drageEvent = event.getAction();
            switch (drageEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();


                    if (view.getId() == R.id.equal) {
                        drag.start();
                        txtequal.setText("=");
                        equal.setVisibility(View.INVISIBLE);
                    }
                    if (!"".equals(txtbig.getText()) && !"".equals(txtsmall.getText()) && !"".equals(txtequal.getText()))
                        next.setVisibility(View.VISIBLE);


                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;

            }
            return true;
        }
    };












}