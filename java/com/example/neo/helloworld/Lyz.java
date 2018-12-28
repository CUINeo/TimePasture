package com.example.neo.helloworld;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Lyz extends Activity {
    private static EditText wt_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyz);
        wt_time = (EditText) findViewById(R.id.time);
        Button btn_start = (Button) findViewById(R.id.start_btn);
    }

    public void beginFocus(View view) {
        Intent intent = new Intent();
        intent.setClass(this, FocusActivity.class);
        Bundle bl = new Bundle();
        bl.putInt("delay_time", Integer.parseInt(wt_time.getText().toString()));
        intent.putExtras(bl);
        startActivityForResult(intent, 0);
        //System.exit(0);
    }
}
