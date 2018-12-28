package com.example.neo.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

public class Tzy extends Activity {
	
	public static ArrayList<String> top = new ArrayList<String>();
	
	public static ArrayList<String> con = new ArrayList<String>();
	
	public int present = 0;
	
	public ArrayAdapter<String> adapter = null;
	
	//public ArrayAdapter<String> content_adapter = null;
	
	int count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tzy);
		ListView lv=(ListView) findViewById(R.id.view1);
		
		//Intent intent = new Intent(MainActivity.this, Progress.class);
		//startActivity(intent);
		
		adapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, top);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
			String result = parent.getItemAtPosition(pos).toString(); //��ȡѡ�����ֵ
			Toast.makeText(Tzy.this, "����� "+result, Toast.LENGTH_SHORT).show();//���ѡ������Ϣ
			Intent intent = new Intent(Tzy.this, Detail.class);
			present = pos;
			intent.putExtra("KEY", top.get(pos));
			intent.putExtra("VALUE", con.get(pos));
			//intent.putExtra("VALUE", pos);
			startActivityForResult(intent, 10);
		}
		});
		
		Button c = (Button) findViewById(R.id.button1);
		c.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Tzy.this, Detail.class);
			intent.putExtra("VALUE", new String());
			startActivityForResult(intent, 20);
		}
		});
		
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==10&&resultCode==RESULT_OK){
            	String t = data.getStringExtra("KEY");
            	String c = data.getStringExtra("VALUE");
            	if (t.equals("")&&c.equals("")) {
            		top.remove(present);
            		con.remove(present);
            	}
            	else {
            		top.add(t);
            		con.add(c);
            	}
                adapter.notifyDataSetChanged();
            }
            if(requestCode==20&&resultCode==RESULT_OK) {
            	String t = data.getStringExtra("KEY");
            	String c = data.getStringExtra("VALUE");
            	if (t.equals("")&&c.equals("")) {
            		;
            	}
            	else {
            		top.add(t);
            		con.add(c);
            	}
            	adapter.notifyDataSetChanged();
            }
    }

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

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
