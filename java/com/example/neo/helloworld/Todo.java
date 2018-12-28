package com.example.neo.helloworld;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.*;
import android.widget.*;
import android.content.Intent;

public class Todo extends Activity implements OnClickListener{
	public String c, t;
	public int cap, ach;
	public EditText content, topic, num1, num2;
	public ProgressBar pb;
	public TextView percent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		Intent intent = getIntent();
		SingleProgress sp = (SingleProgress)intent.getSerializableExtra("KEY");
		topic = (EditText) findViewById(R.id.editText1);
		content = (EditText) findViewById(R.id.editText2);
		num1 = (EditText) findViewById(R.id.editText3);
		num2 = (EditText) findViewById(R.id.editText4);
		pb = (ProgressBar) findViewById(R.id.progressBar1);
		percent = (TextView) findViewById(R.id.textView5);
		topic.setText(sp.topic);
		content.setText(sp.content);
		ach = sp.achieved;
		cap = sp.capacity;
		num1.setText(Integer.toString(ach));
		num2.setText(Integer.toString(cap));
		pb.setMax(cap);
		pb.setProgress(ach);
		percent.setText((100*ach/cap) + "%");
		Button b1=(Button) findViewById(R.id.button1);
		b1.setOnClickListener(this);
		Button o = (Button) findViewById(R.id.ok);
		o.setOnClickListener(this);
		Button d = (Button) findViewById(R.id.delete);
		d.setOnClickListener(this);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.todo, menu);
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
			ach = Integer.parseInt(num1.getText().toString());
			cap = Integer.parseInt(num2.getText().toString());
			pb.setMax(cap);
			pb.setProgress(ach);
			percent.setText((100*ach/cap) + "%");
			break;
		case R.id.ok:
			t = topic.getText().toString();
			c = content.getText().toString();
			SingleProgress sp2 = new SingleProgress(t, c, cap, ach);
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
