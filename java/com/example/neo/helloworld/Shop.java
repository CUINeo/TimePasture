package com.example.neo.helloworld;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Shop extends Activity {
    private void showFailDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Shop.this);
        builder.setTitle("提示");
        builder.setMessage("金币不足！");
        builder.setPositiveButton("确认", null);
        builder.show();
    }

    private void showBuySuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Shop.this);
        builder.setTitle("提示");
        builder.setMessage("购买成功！");
        builder.setPositiveButton("确认", null);
        builder.show();
    }

    private void showUpgradeSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Shop.this);
        builder.setTitle("提示");
        builder.setMessage("升级成功！");
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

    private void initUpgrade() {
        Button Btn = findViewById(R.id.button);
        Btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                // Get the sharedpreferences
                SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);

                // Get current gold number
                String gold_number = sp.getString("gold_number", "0");
                // Get current sheep level
                String sheep_level = sp.getString("sheep_level", "0");
                // Get current upgrade cost
                String upgrade_cost = sp.getString("upgrade_cost", "0");

                // In case String equals null
                assert gold_number != null;
                assert upgrade_cost != null;
                assert sheep_level != null;

                // Get the values after buying
                int gn = Integer.valueOf(gold_number) - Integer.valueOf(upgrade_cost);
                int sn = Integer.valueOf(sheep_level) + 1;

                // Set new values
                setGoldNumber(String.valueOf(gn));
                setSheepLevel(String.valueOf(sn));

                if (sn == 3) {
                    Intent intent = new Intent(Shop.this, FinalShop.class);
                    startActivity(intent);
                    return;
                }

                if (gn < 0) {
                    showFailDialog();
                }
                else  {
                    showUpgradeSuccessDialog();
                }
            }
        });
    }

    private void initReturn() {
        // Get the image button
        ImageButton IBtn = findViewById(R.id.imageButton1);
        IBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Shop.this, Farm.class);
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
        // Upgrade cost
        TextView tv_upgrade_cost = findViewById(R.id.textView2);

        // Get the sharedpreferences
        SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);

        // Get current gold number
        String gold_number = sp.getString("gold_number", "0");
        // Get current sheep number
        String sheep_number = sp.getString("sheep_number", "0");
        // Get current sheep cost
        String sheep_cost = sp.getString("sheep_cost", "0");
        // Get current upgrade cost
        String upgrade_cost = sp.getString("upgrade_cost", "0");
        // Get current sheep level
        String sheep_level = sp.getString("sheep_level", "1");

        assert sheep_level != null;
        Drawable drawable1, drawable2;
        switch (sheep_level) {
            case "1":
                drawable1 = getDrawable(R.mipmap.sheep1);
                drawable2 = getDrawable(R.mipmap.sheep2);
                break;
            case "2":
                drawable1 = getDrawable(R.mipmap.sheep2);
                drawable2 = getDrawable(R.mipmap.sheep3);
                break;
            default:
                drawable1 = getDrawable(R.mipmap.sheep1);
                drawable2 = getDrawable(R.mipmap.sheep2);
        }

        // Set sheep picture
        ImageView iv1 = findViewById(R.id.imageView13);
        iv1.setImageDrawable(drawable1);
        ImageView iv2 = findViewById(R.id.imageView15);
        iv2.setImageDrawable(drawable2);

        // Set bold
        tv_gold.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tv_sheep.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tv_sheep_cost.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tv_upgrade_cost.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        // Set the text of textviews
        tv_gold.setText(gold_number);
        tv_sheep.setText(sheep_number);
        tv_sheep_cost.setText(sheep_cost);
        tv_upgrade_cost.setText(upgrade_cost);
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
    private void setSheepLevel(String sheep_level) {
        // Get the sharedpreferences
        SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);

        // Edit the xml file
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("sheep_level", sheep_level);
        editor.apply();

        // Show the change
        initNumber();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setUpgradeCost(String upgrade_cost) {
        // Get the sharedpreferences
        SharedPreferences sp = getSharedPreferences("Variables", MODE_PRIVATE);

        // Edit the xml file
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("upgrade_cost", upgrade_cost);
        editor.apply();

        // Show the change
        initNumber();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initBuy();
        initUpgrade();
        initNumber();
        initReturn();

        setSheepCost("5");
        setUpgradeCost("10");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
    }
}
