package com.diewland.digitalstamp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String TAG = "DIEWLAND";

    // tools

    private double calc_distance(int x1, int y1, int x2, int y2){
        double dx = Math.pow(x2 - x1, 2);
        double dy = Math.pow(y2 - y1, 2);
        return Math.sqrt(dx + dy);
    }

    // events

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        ArrayList<Integer[]> xys = new ArrayList<Integer[]>();

        int pointCount = event.getPointerCount();
        // Log.d(TAG, "found " + pointCount + " touches");

        for (int i = 0; i < pointCount; i++) {
            int x = (int) event.getX(i);
            int y = (int) event.getY(i);

            // Log.d(TAG, "[" + i + "] " + x + ", " + y);
            xys.add(new Integer[]{ x, y });
        }

        for(Integer[] xy : xys){
            Log.d(TAG, xy[0] + ", " + xy[1]);
        }

        // TODO full scan distance
        // TODO sort distance

        return false;
    }
}
