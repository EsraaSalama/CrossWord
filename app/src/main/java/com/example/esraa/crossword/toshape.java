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


public class toshape extends AppCompatActivity {
    private Chronometer time3;
    private TextView txtrec,txtsph,txtcon,txtcyl,txtpyr,txtcub;
    private TextView rec,sph,con,cyl,pyr,cub;
    private long lastPause;
    private ImageButton next;
    MediaPlayer clic ,rectangle,cube,pyramid,sphere,cylinder,cone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toshape);

        time3 = (Chronometer) findViewById(R.id.counter3) ;
        Intent data = this.getIntent();
        time3.setBase(data.getExtras().getLong("baseoftime3"));
        time3.start();

        rectangle = MediaPlayer.create(getApplicationContext(), R.raw.cuboid);
        cube = MediaPlayer.create(getApplicationContext(), R.raw.cube);
        sphere = MediaPlayer.create(getApplicationContext(), R.raw.sphere);
        pyramid = MediaPlayer.create(getApplicationContext(), R.raw.pyramid);
        cone = MediaPlayer.create(getApplicationContext(), R.raw.cone);
        cylinder = MediaPlayer.create(getApplicationContext(), R.raw.cylinder);
        clic = MediaPlayer.create(getApplicationContext(), R.raw.clic);

        txtrec = (TextView)findViewById(R.id.txtrec);
        txtcyl = (TextView)findViewById(R.id.txtcyl);
        txtpyr = (TextView)findViewById(R.id.txtpyr);
        txtsph = (TextView)findViewById(R.id.txtsph);
        txtcon = (TextView)findViewById(R.id.txtcon);
        txtcub = (TextView)findViewById(R.id.txtcub);

        rec = (TextView) findViewById(R.id.rec);
        sph = (TextView) findViewById(R.id.sph);
        cyl = (TextView) findViewById(R.id.cyl);
        pyr = (TextView) findViewById(R.id.pyr);
        con = (TextView) findViewById(R.id.con);
        cub = (TextView) findViewById(R.id.cub);

        next = (ImageButton) findViewById(R.id.imageButton3);
        next.setOnClickListener(click);

        rec.setOnTouchListener(touchListener);
        cub.setOnTouchListener(touchListener);
        con.setOnTouchListener(touchListener);
        cyl.setOnTouchListener(touchListener);
        pyr.setOnTouchListener(touchListener);
        sph.setOnTouchListener(touchListener);

        txtrec.setOnDragListener(dragListenerrec);
        txtcub.setOnDragListener(dragListenercub);
        txtcon.setOnDragListener(dragListenercon);
        txtcyl.setOnDragListener(dragListener1cyl);
        txtpyr.setOnDragListener(dragListenerpyr);
        txtsph.setOnDragListener(dragListenersph);





    }
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(toshape.this,maths.class);
            i.putExtra("base",time3.getBase());
            startActivity(i);
            finish();

        }
    };

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("stringy", txtrec.getText().toString());
        savedInstanceState.putString("stringr", txtcon.getText().toString());
        savedInstanceState.putString("stringb", txtcub.getText().toString());
        savedInstanceState.putString("stringg", txtcyl.getText().toString());
        savedInstanceState.putString("stringg1", txtsph.getText().toString());
        savedInstanceState.putString("stringg2", txtpyr.getText().toString());
        savedInstanceState.putLong("long1", time3.getBase());
        savedInstanceState.putLong("long2", lastPause);


    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        txtrec.setText(savedInstanceState.getString("stringy"));
        txtcon.setText(savedInstanceState.getString("stringr"));
        txtcub.setText(savedInstanceState.getString("stringb"));
        txtcyl.setText(savedInstanceState.getString("stringg"));
        txtsph.setText(savedInstanceState.getString("stringg1"));
        txtpyr.setText(savedInstanceState.getString("stringg2"));
        if (savedInstanceState.getLong("long2")
                == 0) {
            time3.setBase(time3.getBase() + SystemClock.elapsedRealtime() - savedInstanceState.getLong("long2"));
        }
        {
            time3.setBase(savedInstanceState.getLong("long1") + SystemClock.elapsedRealtime() - savedInstanceState.getLong("long2"));
        }


        if (!"".equals(txtpyr.getText()) && !"".equals(txtsph.getText()) && !"".equals(txtcyl.getText()) && !"".equals(txtrec.getText())&& !"".equals(txtcon.getText())&& !"".equals(txtcub.getText())    )
            next.setVisibility(View.VISIBLE);
        if (!"".equals(txtrec.getText()))
            rec.setVisibility(View.INVISIBLE);
        if (!"".equals(txtcub.getText()))
            cub.setVisibility(View.INVISIBLE);
        if (!"".equals(txtcon.getText()))
            con.setVisibility(View.INVISIBLE);
        if (!"".equals(txtcyl.getText()))
            cyl.setVisibility(View.INVISIBLE);
        if (!"".equals(txtpyr.getText()))
            pyr.setVisibility(View.INVISIBLE);
        if (!"".equals(txtsph.getText()))
            sph.setVisibility(View.INVISIBLE);



    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onPause()
    { super.onPause();
        lastPause = SystemClock.elapsedRealtime();
        time3.stop();
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        time3.setBase(time3.getBase() + SystemClock.elapsedRealtime() - lastPause);
        time3.start();
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
    View.OnDragListener dragListenerrec = new View.OnDragListener(){
        @Override
        public boolean onDrag (View v , DragEvent event){
            int drageEvent = event.getAction();
            switch (drageEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();


                    if (view.getId()==R.id.rec ){
                        rectangle.start();
                        txtrec.setText("cuboid");
                        rec.setVisibility(View.INVISIBLE);
                    }
                    if (!"".equals(txtpyr.getText()) && !"".equals(txtsph.getText()) && !"".equals(txtcyl.getText()) && !"".equals(txtrec.getText())&& !"".equals(txtcon.getText())&& !"".equals(txtcub.getText())    )

                        next.setVisibility(View.VISIBLE);


                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;

            }
            return true ;
        }
    };
    View.OnDragListener dragListenersph = new View.OnDragListener(){
        @Override
        public boolean onDrag (View v , DragEvent event){
            int drageEvent = event.getAction();
            switch (drageEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();


                    if (view.getId()==R.id.sph ){
                        sphere.start();
                        txtsph.setText("sphere");
                        sph.setVisibility(View.INVISIBLE);
                    }
                    if (!"".equals(txtpyr.getText()) && !"".equals(txtsph.getText()) && !"".equals(txtcyl.getText()) && !"".equals(txtrec.getText())&& !"".equals(txtcon.getText())&& !"".equals(txtcub.getText())    )

                        next.setVisibility(View.VISIBLE);


                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;

            }
            return true ;
        }
    };
    View.OnDragListener dragListenercub = new View.OnDragListener(){
        @Override
        public boolean onDrag (View v , DragEvent event){
            int drageEvent = event.getAction();
            switch (drageEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();


                    if (view.getId()==R.id.cub ){
                        cube.start();
                        txtcub.setText("cube");
                        cub.setVisibility(View.INVISIBLE);
                    }
                    if (!"".equals(txtpyr.getText()) && !"".equals(txtsph.getText()) && !"".equals(txtcyl.getText()) && !"".equals(txtrec.getText())&& !"".equals(txtcon.getText())&& !"".equals(txtcub.getText())    )

                        next.setVisibility(View.VISIBLE);


                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;

            }
            return true ;
        }
    };

    View.OnDragListener dragListenercon = new View.OnDragListener(){
        @Override
        public boolean onDrag (View v , DragEvent event){
            int drageEvent = event.getAction();
            switch (drageEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();


                    if (view.getId()==R.id.con ){
                        cone.start();
                        txtcon.setText("cone");
                        con.setVisibility(View.INVISIBLE);
                    }
                    if (!"".equals(txtpyr.getText()) && !"".equals(txtsph.getText()) && !"".equals(txtcyl.getText()) && !"".equals(txtrec.getText())&& !"".equals(txtcon.getText())&& !"".equals(txtcub.getText())    )

                        next.setVisibility(View.VISIBLE);


                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;

            }
            return true ;
        }
    };
    View.OnDragListener dragListenerpyr = new View.OnDragListener(){
        @Override
        public boolean onDrag (View v , DragEvent event){
            int drageEvent = event.getAction();
            switch (drageEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();


                    if (view.getId()==R.id.pyr ){
                        pyramid.start();
                        txtpyr.setText("pyramid");
                        pyr.setVisibility(View.INVISIBLE);
                    }
                    if (!"".equals(txtpyr.getText()) && !"".equals(txtsph.getText()) && !"".equals(txtcyl.getText()) && !"".equals(txtrec.getText())&& !"".equals(txtcon.getText())&& !"".equals(txtcub.getText())    )

                        next.setVisibility(View.VISIBLE);


                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;

            }
            return true ;
        }
    };
    View.OnDragListener dragListener1cyl = new View.OnDragListener(){
        @Override
        public boolean onDrag (View v , DragEvent event){
            int drageEvent = event.getAction();
            switch (drageEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View) event.getLocalState();


                    if (view.getId()==R.id.cyl ){
                        cylinder.start();
                        txtcyl.setText("cylinder");
                        cyl.setVisibility(View.INVISIBLE);
                    }
                    if (!"".equals(txtpyr.getText()) && !"".equals(txtsph.getText()) && !"".equals(txtcyl.getText()) && !"".equals(txtrec.getText())&& !"".equals(txtcon.getText())&& !"".equals(txtcub.getText())    )

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
