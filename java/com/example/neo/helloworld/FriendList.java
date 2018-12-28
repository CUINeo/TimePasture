package com.example.neo.helloworld;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class FriendList extends Activity {
    class clickListner implements View.OnClickListener {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            // Get the sharedpreferences
            SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);

            // Get current gold number
            String gold_number = sp.getString("gold_number", "0");
            // Get current sheep number
            String sheep_number = sp.getString("sheep_number", "0");

            // In case String equals null
            assert gold_number != null;
            assert sheep_number != null;

            // Get the values after buying
            int gn = Integer.valueOf(gold_number) - 10;
            int sn = Integer.valueOf(sheep_number) + 1;

            if (gn < 0) {
                showFailDialog();
                return;
            }
            else {
                showStealSuccessDialog();
            }

            // Set new values
            setGoldNumber(String.valueOf(gn));
            setSheepNumber(String.valueOf(sn));
        }
    }

    private void showFailDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FriendList.this);
        builder.setTitle("提示");
        builder.setMessage("金币不足！");
        builder.setPositiveButton("确认", null);
        builder.show();
    }

    private void showStealSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FriendList.this);
        builder.setTitle("提示");
        builder.setMessage("偷取成功！");
        builder.setPositiveButton("确认", null);
        builder.show();
    }

    private void initSteal() {
        Button Btn;
        clickListner cl = new clickListner();

        // Add listener
        Btn = findViewById(R.id.button3);
        Btn.setOnClickListener(cl);
        Btn = findViewById(R.id.button4);
        Btn.setOnClickListener(cl);
        Btn = findViewById(R.id.button5);
        Btn.setOnClickListener(cl);
        Btn = findViewById(R.id.button6);
        Btn.setOnClickListener(cl);
        Btn = findViewById(R.id.button7);
        Btn.setOnClickListener(cl);
        Btn = findViewById(R.id.button8);
        Btn.setOnClickListener(cl);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setGoldNumber(String gold_number) {
        // Get the sharedpreferences
        SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);

        // Edit the xml file
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("gold_number", gold_number);
        editor.apply();

        // Show the change
        initNumber();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setSheepNumber(String sheep_number) {
        // Get the sharedpreferences
        SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);

        // Edit the xml file
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("sheep_number", sheep_number);
        editor.apply();

        // Show the change
        initNumber();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    private void initNumber() {
        // Number of gold
        TextView tv_gold = findViewById(R.id.textView4);
        // Number of sheep
        TextView tv_sheep = findViewById(R.id.textView5);

        // Get the sharedpreferences
        SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);

        // Get current gold number
        String gold_number = sp.getString("gold_number", "0");
        // Get current sheep number
        String sheep_number = sp.getString("sheep_number", "0");

        // Set bold
        tv_gold.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tv_sheep.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        // Set the text of textviews
        tv_gold.setText(gold_number);
        tv_sheep.setText(sheep_number);
    }

    private void initReturn() {
        // Get the image button
        ImageButton IBtn = findViewById(R.id.imageButton1);
        IBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendList.this, Farm.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initReturn();
        initSteal();
        initNumber();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
    }
}
