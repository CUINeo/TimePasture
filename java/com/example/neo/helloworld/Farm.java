package com.example.neo.helloworld;

import android.annotation.SuppressLint;
import android.support.annotation.RequiresApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Farm extends Activity {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    private void initView() {
        // Number of gold
        TextView tv_gold = findViewById(R.id.textView2);
        // Number of sheep
        TextView tv_sheep = findViewById(R.id.textView3);

        // Get the sharedpreferences
        SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);

        // Get current gold number
        String gold_number = sp.getString("gold_number", "0");
        // Get current sheep number
        String sheep_number = sp.getString("sheep_number", "0");
        // Get current sheep level
        String sheep_level = sp.getString("sheep_level", "1");

        assert sheep_level != null;
        Drawable drawable;
        switch (sheep_level) {
            case "1":
                drawable = getDrawable(R.mipmap.sheep1);
                break;
            case "2":
                drawable = getDrawable(R.mipmap.sheep2);
                break;
            case "3":
                drawable = getDrawable(R.mipmap.sheep3);
                break;
            default:
                drawable = getDrawable(R.mipmap.sheep1);
        }

        // Set sheep picture
        ImageView iv1 = findViewById(R.id.imageView1);
        iv1.setImageDrawable(drawable);
        ImageView iv2 = findViewById(R.id.imageView2);
        iv2.setImageDrawable(drawable);
        ImageView iv3 = findViewById(R.id.imageView3);
        iv3.setImageDrawable(drawable);
        ImageView iv4 = findViewById(R.id.imageView4);
        iv4.setImageDrawable(drawable);
        ImageView iv5 = findViewById(R.id.imageView5);
        iv5.setImageDrawable(drawable);
        ImageView iv6 = findViewById(R.id.imageView6);
        iv6.setImageDrawable(drawable);
        ImageView iv7 = findViewById(R.id.imageView7);
        iv7.setImageDrawable(drawable);

        Drawable blankDrawable = getDrawable(R.mipmap.transparent);

        assert sheep_number != null;
        switch (sheep_number) {
            case "0":
                iv1.setImageDrawable(blankDrawable);
                iv2.setImageDrawable(blankDrawable);
                iv3.setImageDrawable(blankDrawable);
                iv4.setImageDrawable(blankDrawable);
                iv5.setImageDrawable(blankDrawable);
                iv6.setImageDrawable(blankDrawable);
                iv7.setImageDrawable(blankDrawable);
                break;
            case "1":
                iv2.setImageDrawable(blankDrawable);
                iv3.setImageDrawable(blankDrawable);
                iv4.setImageDrawable(blankDrawable);
                iv5.setImageDrawable(blankDrawable);
                iv6.setImageDrawable(blankDrawable);
                iv7.setImageDrawable(blankDrawable);
                break;
            case "2":
                iv3.setImageDrawable(blankDrawable);
                iv4.setImageDrawable(blankDrawable);
                iv5.setImageDrawable(blankDrawable);
                iv6.setImageDrawable(blankDrawable);
                iv7.setImageDrawable(blankDrawable);
                break;
            case "3":
                iv4.setImageDrawable(blankDrawable);
                iv5.setImageDrawable(blankDrawable);
                iv6.setImageDrawable(blankDrawable);
                iv7.setImageDrawable(blankDrawable);
                break;
            case "4":
                iv5.setImageDrawable(blankDrawable);
                iv6.setImageDrawable(blankDrawable);
                iv7.setImageDrawable(blankDrawable);
                break;
            case "5":
                iv6.setImageDrawable(blankDrawable);
                iv7.setImageDrawable(blankDrawable);
                break;
            case "6":
                iv7.setImageDrawable(blankDrawable);
                break;
            case "7":
                break;
            default:
                break;
        }

        // Set bold
        tv_gold.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tv_sheep.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        // Set the text of textviews
        tv_gold.setText(gold_number);
        tv_sheep.setText(sheep_number);
    }

    private void initReturn() {
        ImageButton IBtn = findViewById(R.id.imageButton2);
        IBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Farm.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initFriend() {
        // Get the friend button
        ImageButton IBtn = findViewById(R.id.imageButton3);
        IBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Farm.this, FriendList.class);
                startActivity(intent);
            }
        });
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
        initView();
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
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setSheepLevel(String sheep_level) {
        // Get the sharedpreferences
        SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);

        // Edit the xml file
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("sheep_level", sheep_level);
        editor.apply();

        // Show the change
        initView();
    }

    private void initShop() {
        // Get the image button
        ImageButton IBtn = findViewById(R.id.imageButton);
        IBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the sharedpreferences
                SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);
                // Get current sheep level
                String sheep_level = sp.getString("sheep_level", "1");

                assert sheep_level != null;
                if (sheep_level.equals("3")) {
                    Intent intent = new Intent(Farm.this, FinalShop.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(Farm.this, Shop.class);
                    startActivity(intent);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        // Get the sharedpreferences
        SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);
        // Get current sheep level
        String sheep_level = sp.getString("sheep_level", "0");

        assert sheep_level != null;
        if (sheep_level.equals("0")) {
            setGoldNumber("30");
            setSheepNumber("1");
            setSheepLevel("1");
        }

        initFriend();
        initView();
        initShop();
        initReturn();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);
    }
}
