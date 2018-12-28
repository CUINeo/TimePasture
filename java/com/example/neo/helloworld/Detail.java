package com.example.neo.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class Detail extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		Intent intent = getIntent();
		//int p = (int)getIntent().getIntExtra("VALUE", 0);
		String title = (String)intent.getStringExtra("KEY");
		EditText text = (EditText) findViewById(R.id.name);
		String data= (String)intent.getStringExtra("VALUE");
		EditText text2 = (EditText) findViewById(R.id.sched);
		text.setText(title);
		text2.setText(data);
		
		
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		        EditText t = (EditText) findViewById(R.id.name);
		        String r = t.getText().toString();
		        EditText text = (EditText) findViewById(R.id.sched);
		        String s = text.getText().toString();
		        Intent intent = new Intent();
		        intent.putExtra("KEY", r);
		        intent.putExtra("VALUE", s);
		        setResult(RESULT_OK, intent);
		        Detail.this.finish();
		    }
	    });
		
		Button d = (Button) findViewById(R.id.button2);
		d.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		        Intent intent = new Intent();
		        intent.putExtra("KEY", "");
		        intent.putExtra("VALUE", "");
		        setResult(RESULT_OK, intent);
		        Detail.this.finish();
		    }
	    });
	    
	}

}
