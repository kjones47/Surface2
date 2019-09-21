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

    public MySurface(Context context) {
        super(context);
        holder = getHolder();
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder){
        drawPoint();
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

        holder.unlockCanvasAndPost(c);

        paint.setColor(Color.BLUE);
        c.drawCircle(x, y, 10, paint);

    }

    public void drawRect()
    {
        Canvas canvas = holder.lockCanvas();


        // Draw the rectangle.
        canvas.drawRect(x, y, x + 200, y + 200, paint);

       holder.unlockCanvasAndPost(canvas);
    }
    public void drawGraph()
    {
        Canvas canvas = holder.lockCanvas();

        Paint surfaceBackground = new Paint();
        // Set the surfaceview background color.
        surfaceBackground.setColor(Color.WHITE);
        // Draw the surfaceview background color.
        canvas.drawRect(0, 0, this.getWidth(), this.getHeight(), surfaceBackground);

        // Draw the rectangle.
        paint.setColor(Color.BLACK);
        canvas.drawRect(20, 20, 40, this.getHeight()/2 - 40, paint);
        canvas.drawRect(20, this.getHeight()/2 - 40, this.getWidth()-20, this.getHeight()/2 -20, paint);

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