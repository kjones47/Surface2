package com.example.android.surface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private Button redButton = null;

    private Button greenButton = null;

    private boolean drawBall = true;

    private LinearLayout canvasLayout = null;

    //variables
    private String title;
    private String xAxis;
    private String yAxis;
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    private int color = Color.GREEN;
    private float[] valX = new float[100];
    private float[] valY = new float[100];
    private int count = 0;

    MySurface customSurfaceView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

      /*  TextView titleText = findViewById(R.id.title);
        EditText one = findViewById(R.id.xAxis);
        EditText two = findViewById(R.id.yAxis);

        Button apply = (Button) findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String first = one.getEditableText().toString();
                //String second = two.getEditableText().toString();
                // titleText.setText( "" + first + " vs. " + second, TextView.BufferType.NORMAL);
            }
        }); */

        Button red = (Button) findViewById(R.id.red);
        Button blue = findViewById(R.id.blue);
        Button green = findViewById(R.id.green);
        Button black = findViewById(R.id.black);
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = Color.RED;
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = Color.BLUE;
            }
        });
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = Color.GREEN;
            }
        });
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = Color.BLACK;
            }
        });

        setTitle("SurfaceView");

        initControls();

        getSupportActionBar().hide();

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        customSurfaceView = new MySurface(getApplicationContext());

        customSurfaceView.setOnTouchListener(this);

        canvasLayout.addView(customSurfaceView);

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawBall = true;
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawBall = false;
            }
        });

    }


    private void initControls()
    {

        if(greenButton == null)
        {
            greenButton = (Button)findViewById(R.id.greenButton);
        }

        // This layout is used to contain custom surfaceview object.
        if(canvasLayout == null)
        {
            canvasLayout = (LinearLayout)findViewById(R.id.customViewLayout);
        }
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


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        // If user touch the custom SurfaceView object.
        if(view instanceof SurfaceView) {
            view.invalidate();

            float x = motionEvent.getX();
            valX[count] = x;

            float y = motionEvent.getY();
            valY[count] = y;

            count++;

            customSurfaceView.setCircleX(x);

            customSurfaceView.setCircleY(y);

            if (drawBall) {
                // Create and set a red paint to custom surfaceview.
                customSurfaceView.drawGraph();
                Paint paint = new Paint();
                paint.setColor(color);

                customSurfaceView.setPaint(paint);

                customSurfaceView.drawDot(count,valX, valY);
            } else {
                // Create and set a green paint to custom surfaceview.
                customSurfaceView.drawGraph();
                Paint paint = new Paint();
                paint.setColor(color);

                customSurfaceView.setPaint(paint);

                customSurfaceView.drawDot(count, valX, valY);
            }

            // Tell android os the onTouch event has been processed.
            return true;
        }else
        {
            // Tell android os the onTouch event has not been processed.
            return false;
        }
    }
}
