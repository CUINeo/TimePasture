package com.example.neo.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class FocusActivity  extends Activity {
    public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView wt_time = (TextView) findViewById(R.id.time);
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_focus);
        Intent i = this.getIntent();
        Bundle bl = i.getExtras();
        int delay_time = bl.getInt("delay_time");
        timer.schedule(task, delay_time*1000);
        //startService(new Intent(this, ListenService.class));
    }

    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Intent intent = new Intent();
            intent.setClass(FocusActivity.this, PedometerActivity.class);
            startActivityForResult(intent, 0);
            System.exit(0);
        }
    };

    /* 禁用返回键 */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            return true;
        else if(keyCode == KeyEvent.KEYCODE_HOME)
            return true;
        return false;
    }

}
