package com.example.expandablelistviewdemo;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyExpandableListAdapter extends BaseExpandableListAdapter
{
	private Context mContext;

	private List<String> expandableListTitle;
	private HashMap<String, List<String>> expandableListDetail;

	
	public MyExpandableListAdapter(List<String> expandableListTitle,HashMap<String,List<String>> expandableListDetail,Context mContext)
	{
		this.expandableListDetail = expandableListDetail;
		this.expandableListTitle = expandableListTitle;
		this.mContext = mContext;
	}
	
	@Override
	public int getGroupCount()//分组数
	{
		return this.expandableListTitle.size();
	}

	@Override
	public int getChildrenCount(int groupPosition)//分组内的item数
	{
		return this.expandableListDetail.get(expandableListTitle.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition)//获取分组数据
	{
		return this.expandableListTitle.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition)//获取第几分组第几个item的数据
	{
		return this.expandableListDetail.get(this.expandableListTitle.get(groupPosition)).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition)
	{
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition)
	{
		return childPosition;
	}

	@Override
	public boolean hasStableIds()
	{
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent)
	{
		String data = this.expandableListTitle.get(groupPosition);
		if(convertView == null)
		{
			convertView = View.inflate(mContext,R.layout.list_group, null);
		}
		TextView tv = (TextView) convertView.findViewById(R.id.listTitle);
		tv.setText(data);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent)
	{
//		String data = this.expandableListDetail.get(this.expandableListTitle.get(groupPosition)).get(childPosition);
		String data = (String) this.getChild(groupPosition, childPosition);
		if(convertView == null)
		{
			convertView = View.inflate(mContext, R.layout.list_item, null);
		}
		TextView tv = (TextView) convertView.findViewById(R.id.expandedListItem);
		tv.setText(data);
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition)
	{
		return true;
	}

}
