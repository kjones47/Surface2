package com.example.android.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurface extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder = null;
    private Paint paint = null;
    private float x, y = 0;
    private String title;
    private String xAxis;
    private String yAxis;
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;

    public MySurface(Context context) {
        super(context);
        holder = getHolder();
        paint = new Paint();
        paint.setColor(Color.RED);
        title = "Title";
        xAxis = "X-axis";
        yAxis = "Y-axis";
        minX = 0;
        minY = 0;
        maxX = 10;
        maxY = 100;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder){
        drawGraph(10,10);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int left, int top, int i){

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder){
        paint = null;
    }

    public void drawPoint() {
        holder = getHolder();

        Canvas c = holder.lockCanvas();

        paint.setColor(Color.BLUE);
        c.drawCircle(x, y, 10, paint);

        holder.unlockCanvasAndPost(c);
    }

    public void drawRect()
    {
        holder = getHolder();
        Canvas canvas = holder.lockCanvas();


        // Draw the rectangle.
        canvas.drawRect(x, y, x + 200, y + 200, paint);

       holder.unlockCanvasAndPost(canvas);
    }

    public void drawDot(int c, float[]x, float[]y)
    {
        holder = getHolder();
        Canvas canvas = holder.lockCanvas();
        for(int i=0;i<c;i++){
            canvas.drawCircle(x[i], y[i], 10, paint);
        }


        holder.unlockCanvasAndPost(canvas);
    }

    public void drawGraph(int x, int y)
    {
        Canvas canvas = holder.lockCanvas();

        Paint surfaceBackground = new Paint();
        // Set the surfaceview background color.
        surfaceBackground.setColor(Color.WHITE);
        // Draw the surfaceview background color.
        canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), surfaceBackground);

        // Draw the graph
        paint.setColor(Color.BLACK);
        canvas.drawRect(80, 20, 100, this.getHeight() - 80, paint);
        //paint.setColor(Color.RED);
        canvas.drawRect(80, this.getHeight()-100, this.getWidth()-50, this.getHeight() - 80 , paint);

        //Draw 0, max X and Y
        paint.setColor(Color.BLUE);
        paint.setTextSize(50);
        canvas.drawText("" + x,0,60,paint);
        canvas.drawText("0",0,this.getHeight()-20,paint);
        canvas.drawText("" + y, this.getWidth()-150, this.getHeight()-20, paint);
        //Draw x and y axis
        paint.setColor(Color.BLUE);
        paint.setTextSize(50);

        holder.unlockCanvasAndPost(canvas);
    }



    public float getCircleX() {
            return x;
        }

        public void setCircleX(float circleX) {
            this.x = circleX;
        }

        public float getCircleY(){
            return y;
        }

        public void setCircleY(float circleY) {
            this.y = circleY;
        }

        public Paint getPaint() {
            return paint;
        }

        public void setPaint(Paint paint) {
            this.paint = paint;
        }

    }