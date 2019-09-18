package com.example.android.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurface extends SurfaceView {

        Thread t;
        SurfaceHolder holder;
        boolean isRunning = false;

        public MySurface(Context context){
            super(context);
            holder = getHolder();

            Canvas c = holder.lockCanvas();
        }

        public void run(){
            while(isRunning){
                if(!holder.getSurface().isValid()){
                    continue;
                }
                x +=1;
                Canvas c = holder.lockCanvas();
                c.drawARGB(255, 150, 150, 10);
                c.drawBitmap(ball, x, y, null);
                holder.unlockCanvasAndPost(c);
            }
        }

        public void pause() {
            isRunning = false;
            while(true){
                try{
                    t.join();
                }catch(InterruptedException e){
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

}
