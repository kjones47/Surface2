package com.example.android.surface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    MyView v;
    Bitmap ball;
    float x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        v = new MyView(this);
        v.setOnTouchListener((View.OnTouchListener) this);
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
        x = y = 0;
        setContentView(v);
    }

    @Override
    protected void onPause(){
        super.onPause();
        v.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        v.resume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyView extends SurfaceView implements Runnable {

        Thread t;
        SurfaceHolder holder;
        boolean isRunning = false;

        public MyView(Context context){
            super(context);
            holder = getHolder();

            Canvas c = holder.lockCanvas();
        }

        public void run(){
           while(isRunning){
               if(!holder.getSurface().isValid()){
                   continue;
               }
                Canvas c = holder.lockCanvas();
               c.drawARGB(255, 150, 150, 10);
               c.drawBitmap(ball, x, y, null);
               holder.unlockCanvasAndPost(c);
           }
        }

        public void pause(){
            isRunning = false;
            while(true){
                try{
                    t.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

        public void resume(){
            isRunning = true;
            t = new Thread(this);
            t.start();
        }
    }

    public boolean onTouch(View v, MotionEvent me){
       x = me.getX();
       y = me.getY();

       return false;
    }

}
