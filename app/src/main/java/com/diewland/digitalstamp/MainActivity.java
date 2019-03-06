package com.diewland.digitalstamp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String TAG = "DIEWLAND";
    double threshold = 10;

    TextView tv_msg;
    TextView tv_debug;

    // (Sample) templates
    ArrayList<Integer[]> rect = new ArrayList<Integer[]>(){{
        add(new Integer[]{ 0, 0 });
        add(new Integer[]{ 0, 500 });
        add(new Integer[]{ 500, 0 });
        add(new Integer[]{ 500, 500 });
    }};
    ArrayList<Integer[]> tri = new ArrayList<Integer[]>(){{
        add(new Integer[]{ 0, 0 });
        add(new Integer[]{ 0, 500 });
        add(new Integer[]{ 500, 0 });
    }};

    private void detect_patterns(ArrayList<Integer[]> xys){
        if(Tool.calc_diff2(xys, tri) < threshold){ // check tri
            tv_msg.setText("Detected => Triangle");
        }
        else if(Tool.calc_diff2(xys, rect) < threshold){ // check rect
            tv_msg.setText("Detected => Rectangle");
        }
        else { // set blank as default
            tv_msg.setText("");
        }
    }

    // events

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_msg = (TextView)findViewById(R.id.tv_msg);
        tv_debug = (TextView)findViewById(R.id.tv_debug);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // reset debug text
        tv_debug.setText("");

        // collect list of xy
        ArrayList<Integer[]> xys = new ArrayList<Integer[]>();
        int pointCount = event.getPointerCount();
        for (int i = 0; i < pointCount; i++) {
            int x = (int) event.getX(i);
            int y = (int) event.getY(i);
            xys.add(new Integer[]{ x, y });
            tv_debug.append("[" + i + "] " + x + ", " + y + "\n");
        }

        // detect patterns
        detect_patterns(xys);

        return false;
    }
}
