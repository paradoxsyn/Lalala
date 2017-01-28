package com.mygdx.game;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Rabithole on 12/9/2016.
 */

public class Range extends AppCompatActivity {

    long crystals;
    public static final String crypref = "MyPrefsFile";

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(new dragStuff(this, null));

        SharedPreferences prefs = getSharedPreferences(crypref, MODE_PRIVATE);
        long idName = prefs.getLong("crystals", 0); //0 is the default value.
        System.out.println("This is cry" + idName);
        crystals = idName;

        crystalTimer();


    }

    public void crystalTimer(){
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                crystals = crystals + 1;
                                //score.setText(""+crystals);

                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();

    }

    public class dragStuff extends View {

        private Paint paint = new Paint();
        private Paint painttext = new Paint();
        private Path path = new Path();
        Bitmap bitmap;
        Bitmap mutableBitmap;
        Bitmap resized;
        float bx,by;
        Canvas canvas;
        boolean spot = false;
        int mWidth;
        int mHeight;
        Bitmap bitback;
        Bitmap mutableback;
        Bitmap resizedback;
        Bitmap trail;
        Bitmap mutatrail;
        Bitmap retrail;
        int x,y;
        TextView title;
        LinearLayout lay;



        public dragStuff(Context context, AttributeSet attrs) {
            super(context, attrs);


            paint.setStrokeWidth(3);
            paint.setColor(Color.BLACK);

            painttext.setStrokeWidth(50);
            painttext.setColor(Color.BLUE);
            painttext.setTextSize(50);

            bitback = BitmapFactory.decodeResource(context.getResources(),R.drawable.ryzerange);
            mutableback = bitback.copy(Bitmap.Config.ARGB_8888, true);
            resizedback = Bitmap.createScaledBitmap(mutableback,(int)(mutableback.getWidth()*0.5), (int)(mutableback.getHeight()*1.0), true);
            //Doesnt scale properly with screen, need to fix later

            bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.ryzeblast);
            mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            resized = Bitmap.createScaledBitmap(mutableBitmap,(int)(mutableBitmap.getWidth()*1.2), (int)(mutableBitmap.getHeight()*1.2), true);

            trail = BitmapFactory.decodeResource(context.getResources(),R.drawable.ryzetrail);
            mutatrail = trail.copy(Bitmap.Config.ARGB_8888, true);
            retrail = Bitmap.createScaledBitmap(mutatrail,(int)(mutatrail.getWidth()*1.2), (int)(mutatrail.getHeight()*1.2), true);

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);


        }
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            mWidth = MeasureSpec.getSize(widthMeasureSpec);
            mHeight = MeasureSpec.getSize(heightMeasureSpec);

            setMeasuredDimension(mWidth, mHeight);
        }



        @Override
        protected void onDraw(Canvas canvas) {

            //canvas.drawPath(path, paint);
            //canvas.drawRect(100,100,200,200,paint);
            canvas.drawBitmap(resizedback,x,y,paint);
            canvas.drawText("Crystals "+crystals,400,100,painttext);

            int width = canvas.getWidth();
            int height = canvas.getHeight();
            //bx = (width  - resized.getWidth()) * 0.5f;
            by = (height- resized.getHeight()) * 0.5f;
            canvas.drawBitmap(resized, bx, by, paint);
            if (bx >= 250) {
                canvas.drawBitmap(retrail,bx-250,by,paint);
            }
            if (bx >= 500) {
                canvas.drawBitmap(retrail,bx-500,by,paint);
            }
            if (bx >= 750) {
                canvas.drawBitmap(retrail,bx-750,by,paint);
            }
            if (bx >= 1000) {
                canvas.drawBitmap(retrail,bx-1000,by,paint);
            }
            if (bx >= 1250) {
                canvas.drawBitmap(retrail,bx-1250,by,paint);
            }
            if (bx >= 1500) {
                canvas.drawBitmap(retrail,bx-1500,by,paint);
            }
            if (bx >= 1750) {
                canvas.drawBitmap(retrail,bx-1750,by,paint);
            }
            if (bx >= 2000) {
                canvas.drawBitmap(retrail,bx-2000,by,paint);
            }
            //canvas.drawRect(100, 100, 200, 200, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();


            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //path.moveTo(eventX, eventY);
                    //x=(int)event.getX();
                    ///=y=(int)event.getY();
                    if (x >= bx && x < (bx + mutableBitmap.getWidth()) && y >= by && y < (by + mutableBitmap.getHeight())) {
                        System.out.println("itworked");
                        spot = true;
                    }

                    return true;
                case MotionEvent.ACTION_MOVE:
                    //path.lineTo(eventX, eventY);
                        if (spot == true) {
                            bx = (int) event.getX();
                            by = (int) event.getY();
                            //System.out.println(bx);
                        }

                    invalidate();
                    return true;
                case MotionEvent.ACTION_UP:

                    System.out.println(".................."+x+"......"+y); //x= 345 y=530
                    if(spot && x<= 885 && x>=800){
                        crystals=crystals+500;
                    }
                    spot = false;
                    bx = 0;

                    SharedPreferences.Editor editor = getSharedPreferences(crypref, MODE_PRIVATE).edit();

                    editor.putLong("crystals", crystals);
                    editor.commit();

                    invalidate();

                    //path.reset();
                    break;
                default:
                    return false;
            }

            // Schedules a repaint.
            invalidate();
            return true;
        }

    }




   /* public class rangeDraw extends View {

        private Paint paint = new Paint();
        private Path path = new Path();

        public rangeDraw(Context context, AttributeSet attrs) {
            super(context, attrs);

            paint.setAntiAlias(true);
            paint.setStrokeWidth(6f);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPath(path, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(eventX, eventY);
                    return true;
                case MotionEvent.ACTION_MOVE:
                    path.lineTo(eventX, eventY);
                    break;
                case MotionEvent.ACTION_UP:
                    // nothing to do
                    break;
                default:
                    return false;
            }

            // Schedules a repaint.
            invalidate();
            return true;
        }
    }*/
}