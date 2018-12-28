package com.example.neo.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.view.*;
import android.view.View.*;
import android.content.Intent;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		ImageButton bi = (ImageButton) findViewById(R.id.imageButton1);
		ImageButton b1 = (ImageButton) findViewById(R.id.button1);
		Button b2 = (Button) findViewById(R.id.button2);
		Button b3 = (Button) findViewById(R.id.button3);
		Button b4 = (Button) findViewById(R.id.button4);

		b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, Lyz.class);
				startActivity(intent);
			}
		});
		b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, Tzy.class);
				startActivity(intent);
			}
		});
		b3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, Plan.class);
				startActivity(intent);
			}
		});
		b4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, Progress.class);
				startActivity(intent);
			}
		});
		
		bi.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, Farm.class);
				startActivity(intent);
			}
		});
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.menu, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
