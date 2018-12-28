package com.example.neo.helloworld;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class FinalShop extends Activity {
    private void showFailDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FinalShop.this);
        builder.setTitle("提示");
        builder.setMessage("金币不足！");
        builder.setPositiveButton("确认", null);
        builder.show();
    }

    private void showBuySuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FinalShop.this);
        builder.setTitle("提示");
        builder.setMessage("购买成功！");
        builder.setPositiveButton("确认", null);
        builder.show();
    }

    private void initBuy() {
        Button Btn = findViewById(R.id.button2);
        Btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                // Get the sharedpreferences
                SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);

                // Get current gold number
                String gold_number = sp.getString("gold_number", "0");
                // Get current sheep number
                String sheep_number = sp.getString("sheep_number", "0");
                // Get current sheep cost
                String sheep_cost = sp.getString("sheep_cost", "0");

                // In case String equals null
                assert gold_number != null;
                assert sheep_cost != null;
                assert sheep_number != null;

                // Get the values after buying
                int gn = Integer.valueOf(gold_number) - Integer.valueOf(sheep_cost);
                int sn = Integer.valueOf(sheep_number) + 1;

                if (gn < 0) {
                    showFailDialog();
                    return;
                }
                else {
                    showBuySuccessDialog();
                }

                // Set new values
                setGoldNumber(String.valueOf(gn));
                setSheepNumber(String.valueOf(sn));
            }
        });
    }

    private void initReturn() {
        // Get the image button
        ImageButton IBtn = findViewById(R.id.imageButton1);
        IBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalShop.this, Farm.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    private void initNumber() {
        // Number of gold
        TextView tv_gold = findViewById(R.id.textView4);
        // Number of sheep
        TextView tv_sheep = findViewById(R.id.textView5);
        // Sheep cost
        TextView tv_sheep_cost = findViewById(R.id.textView);

        // Get the sharedpreferences
        SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);

        // Get current gold number
        String gold_number = sp.getString("gold_number", "0");
        // Get current sheep number
        String sheep_number = sp.getString("sheep_number", "0");
        // Get current sheep cost
        String sheep_cost = sp.getString("sheep_cost", "0");

        // Set bold
        tv_gold.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tv_sheep.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tv_sheep_cost.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        // Set the text of textviews
        tv_gold.setText(gold_number);
        tv_sheep.setText(sheep_number);
        tv_sheep_cost.setText(sheep_cost);
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
    private void setSheepCost(String sheep_cost) {
        // Get the sharedpreferences
        SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);

        // Edit the xml file
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("sheep_cost", sheep_cost);
        editor.apply();

        // Show the change
        initNumber();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initBuy();
        initNumber();
        initReturn();

//        setSheepCost("5");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_shop);
    }
}
