package com.example.neo.helloworld;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;

public class Progress extends Activity {
	public static ArrayList<SingleProgress> top = new ArrayList<SingleProgress>();
	
	public ArrayAdapter<SingleProgress> adapter = null;
	
	public int present=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress);
		ListView lv=(ListView) findViewById(R.id.view1);
		
		top.add(new SingleProgress());
		
		adapter=new ArrayAdapter<SingleProgress>(this, android.R.layout.simple_expandable_list_item_1, top);
		
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
			Intent intent = new Intent(Progress.this, Todo.class);
			present = pos;
			intent.putExtra("KEY", top.get(pos));
			//intent.putExtra("VALUE", pos);
			startActivityForResult(intent, 10);
		}
		});
		
		Button c = (Button) findViewById(R.id.button1);
		c.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Progress.this, Todo.class);
			intent.putExtra("KEY", new SingleProgress());
			startActivityForResult(intent, 20);
		}
		});
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.progress, menu);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==10){
            	
            	if (resultCode==RESULT_CANCELED) {
            		top.remove(present);
            	}
            	else {
            		SingleProgress s = (SingleProgress)data.getSerializableExtra("KEY");
            		top.set(present, s);
            	}
                adapter.notifyDataSetChanged();
            }
            if(requestCode==20&&resultCode==RESULT_OK) {
            	SingleProgress s = (SingleProgress)data.getSerializableExtra("KEY");
        		top.add(s);
            	adapter.notifyDataSetChanged();
            }
    }
}
