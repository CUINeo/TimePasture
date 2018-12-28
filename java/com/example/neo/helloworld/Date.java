package com.example.neo.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.*;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;



public class Date extends Activity implements OnClickListener{
	
	public TextView start;
	public TextView end;
	public EditText t, p;
	public String content, topic;
	public int year1,month1,day1;
	public int year2,month2,day2;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date);
		start=(TextView)findViewById(R.id.from);
		end=(TextView)findViewById(R.id.to);
		t = (EditText) findViewById (R.id.editText2);
		p = (EditText) findViewById (R.id.editText1);
		Intent intent = getIntent();
		SinglePlan sp = (SinglePlan)intent.getSerializableExtra("KEY");
		year1 = sp.year1;
		year2 = sp.year2;
		month1 = sp.month1;
		month2 = sp.month2;
		day1 = sp.day1;
		day2 = sp.day2;
		content = sp.plan;
		topic = sp.topic;
		t.setText(topic);
		p.setText(content);
		start.setText(year1+"-"+(1+month1)+"-"+day1);
		end.setText(year2+"-"+(1+month2)+"-"+day2);
		
		Button b1=(Button) findViewById(R.id.button1);
		b1.setOnClickListener(this);
		Button b2=(Button) findViewById(R.id.button2);
		b2.setOnClickListener(this);
		Button o = (Button) findViewById(R.id.ok);
		o.setOnClickListener(this);
		Button d = (Button) findViewById(R.id.delete);
		d.setOnClickListener(this);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.date, menu);
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
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button1:
			OnDateSetListener listener1=new OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker arg0, int year, int month, int day) {
					start.setText(year+"-"+(++month)+"-"+day);			}
			};
			DatePickerDialog dialog1=new DatePickerDialog(Date.this, 0,listener1,year1,month1,day1);
			dialog1.show();
			break;
		
		case R.id.button2:
			OnDateSetListener listener2=new OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker arg0, int year, int month, int day) {
					end.setText(year+"-"+(++month)+"-"+day);			}
			};
			DatePickerDialog dialog2=new DatePickerDialog(Date.this, 0,listener2,year1,month1,day1);
			dialog2.show();
			break;
		case R.id.ok:
			topic = t.getText().toString();
			content = p.getText().toString();
			SinglePlan sp2 = new SinglePlan();
			sp2.day1 = day1;
			sp2.day2 = day2;
			sp2.month1 = month1;
			sp2.month2 = month2;
			sp2.year1 = year1;
			sp2.year2 = year2;
			sp2.plan = content;
			sp2.topic = topic;
			sp2.plan = content;
			Intent intent = new Intent();
			intent.putExtra("KEY", sp2);
			setResult(RESULT_OK, intent);
			this.finish();
		case R.id.delete:
			Intent intent2 = new Intent();
			setResult(RESULT_CANCELED, intent2);
			this.finish();
		default:
			break;
		}
	}
}
