package com.example.neo.helloworld;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PedometerActivity extends Activity {
    private TextView tv_step;
    private Button btn_reset;
    private StepService mService;
    private boolean mIsRunning;
    private SharedPreferences mySharedPreferences;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                tv_step.setText(mySharedPreferences.getString("steps","0"));
            }
        }
    };
    static boolean isBegin = false;
    static boolean isEnd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);
        tv_step = (TextView) findViewById(R.id.step_tv);
        btn_reset = (Button) findViewById(R.id.reset_btn);
        mySharedPreferences = getSharedPreferences("relevant_data",Activity.MODE_PRIVATE);
        tv_step.setText(mySharedPreferences.getString("steps", "0"));
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEnd){
                    tv_step.setVisibility(View.VISIBLE);
                    btn_reset.setVisibility(View.INVISIBLE);
                    isBegin = true;
                    mService.resetValues();
                    tv_step.setText(mySharedPreferences.getString("steps", "0"));
                }else{
                    // Get the sharedpreferences
                    SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);

                    // Edit the xml file
                    SharedPreferences.Editor editor = sp.edit();
                    String gold_number = sp.getString("gold_number", "0");
                    assert gold_number != null;
                    editor.putString("gold_number", String.valueOf(Integer.valueOf(gold_number) + 1));
                    editor.apply();

                    Intent intent = new Intent();
                    intent.setClass(PedometerActivity.this, MenuActivity.class);
                    startActivityForResult(intent, 0);
                    System.exit(0);
                }
            }

        });

        startStepService();

        tv_step.addTextChangedListener(textWatcher);

    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(Integer.parseInt(tv_step.getText().toString())==20) {
                isEnd = true;
                btn_reset.setVisibility(View.VISIBLE);
                AlertDialog.Builder builder = new AlertDialog.Builder(PedometerActivity.this);

                builder.setTitle("任务完成");
                builder.setMessage("太强了吧");
                builder.setPositiveButton("我要解锁", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        // Get the sharedpreferences
                        SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);

                        // Edit the xml file
                        SharedPreferences.Editor editor = sp.edit();
                        String gold_number = sp.getString("gold_number", "0");
                        assert gold_number != null;
                        editor.putString("gold_number", String.valueOf(Integer.valueOf(gold_number) + 1));
                        editor.apply();

                        Intent intent = new Intent();
                        intent.setClass(PedometerActivity.this, MenuActivity.class);
                        startActivityForResult(intent, 0);
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("继续走走", null);
                builder.show();
                btn_reset.setText("STOP");
            }



        }

        @Override
        public void afterTextChanged(Editable editable) {
        }

    };

    protected void onDestroy() {
        super.onDestroy();
//        stopStepService();
    }

    protected void onPause() {
        unbindStepService();
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        tv_step.setText(mySharedPreferences.getString("steps", "0"));
        if (this.mIsRunning){
            bindStepService();
        }
    }


    private UpdateUiCallBack mUiCallback = new UpdateUiCallBack() {
        @Override
        public void updateUi() {
            Message message = mHandler.obtainMessage();
            message.what = 1;
            mHandler.sendMessage(message);
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StepService.StepBinder binder = (StepService.StepBinder) service;
            mService = binder.getService();
            mService.registerCallback(mUiCallback);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void bindStepService() {
        bindService(new Intent(this, StepService.class), this.mConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbindStepService() {
        unbindService(this.mConnection);
    }

    private void startStepService() {
        this.mIsRunning = true;
        startService(new Intent(this, StepService.class));
    }

    private void stopStepService() {
        this.mIsRunning = false;
        if (this.mService != null)
            stopService(new Intent(this, StepService.class));
    }

    /* 禁用返回键 */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            return true;
        else if(keyCode == KeyEvent.KEYCODE_HOME)
            return true;
        return false;
    }

}
