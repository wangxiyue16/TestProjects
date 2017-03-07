package com.example.gongyingying.mutilsahke;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by gongyingying on 2016/3/31.
 */
public class ShakeView extends View {

    private final static long KPeriod =  1000 / 60; // 每帧所用的时间


    private Handler handler;
    int changeNum, radndomNum;
    private boolean isPlus;
    private Random random;
    private Bitmap bitmap;
    private boolean isShake;


    private TimerTask zoomTask;
    private TimerTask shakeTask;
    private Timer zoomTimer;
    private Timer shakeTimer;

    private  long startTime;

    private float zoomNum;

    public ShakeView(Context context) {
        super(context);
        init();
    }


    public ShakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        random = new Random();

        zoomNum = 1.0f;

        radndomNum = random.nextInt(4) + 4;
        changeNum = (-1) * radndomNum;
        isShake = true;

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (isShake) {
                    shakeStart();
                } else {
                    zoomView();
                }
            }
        };


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Matrix matrix = new Matrix();
        if (isShake) {

            matrix.postRotate(changeNum, 50, 50);
        } else {
            matrix.postScale(zoomNum, zoomNum, 50, 50);
        }
        canvas.drawBitmap(bitmap, matrix, null);

        canvas.restore();

    }

    private void shakeStart() {

        if (changeNum == (-1) * radndomNum) {
            changeNum++;
            isPlus = true;
        } else if (changeNum == radndomNum) {
            changeNum--;
            isPlus = false;
        } else {
            if (isPlus) {
                changeNum++;
            } else {
                changeNum--;
            }
        }
        invalidate();
    }


    private void zoomView() {

        if (zoomNum != 0 && zoomNum > 0) {
            zoomNum -= 0.1f;
            invalidate();
        } else {
            stopZoom();
        }
    }



    public void shakeShake() {
        startTime = System.currentTimeMillis();
        isShake = true;
        if (null == shakeTask && null == shakeTimer) {
            shakeTimer = new Timer();
            shakeTask = new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            };
            shakeTimer.schedule(shakeTask, 0, KPeriod);
        } else {
            stopShake();
        }
    }

    public void startZoom() {
        isShake = false;
    }

    public void stopShake() {
        if (null != shakeTimer){

            shakeTimer.cancel();
            shakeTimer = null;
            shakeTask = null;
        }
    }

    public void stopZoom() {
        if (null != zoomTimer)
            zoomTimer.cancel();
    }

}
