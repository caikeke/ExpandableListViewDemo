package com.example.expandablelistviewdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class MainActivity extends Activity
{
	private ExpandableListView elv = null;
	private MyExpandableListAdapter adapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		elv = (ExpandableListView) findViewById(R.id.elv);
		
		final HashMap<String,List<String>> itemData = ExpandableListDataPump.getData();
		final List<String> title = new ArrayList<String>(itemData.keySet());
		adapter = new MyExpandableListAdapter(title,itemData,this);
		
		elv.setAdapter(adapter);
		
		//È¥µô¼ýÍ·
//		elv.setGroupIndicator(null);
		
		
		elv.setOnGroupCollapseListener(new OnGroupCollapseListener()
		{
			
			@Override
			public void onGroupCollapse(int groupPosition)
			{
				Toast.makeText(MainActivity.this,title.get(groupPosition)+" group collapse..... ", 0).show();
			}
		});
		
		elv.setOnGroupExpandListener(new OnGroupExpandListener()
		{
			@Override
			public void onGroupExpand(int groupPosition)
			{
				Toast.makeText(MainActivity.this,title.get(groupPosition)+" group expand... ", 0).show();
			}
		});
		
		elv.setOnChildClickListener(new OnChildClickListener()
		{
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id)
			{
				Toast.makeText(MainActivity.this,itemData.get(title.get(groupPosition)).get(childPosition)+" click", 0).show();
				return false;
			}
		});
		
	}

}
