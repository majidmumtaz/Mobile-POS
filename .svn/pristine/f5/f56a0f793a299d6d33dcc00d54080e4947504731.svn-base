package com.setecs.android.mpos.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.setecs.android.mpos.R;

public class OrderAdapter extends BaseAdapter {

	private Context mContext;
	private final LayoutInflater mLayoutInflater;
	private ArrayList<String> orderList = new ArrayList<String>();
	// private String orderList[] = { "0" };

	private String order_name = "";

	public OrderAdapter(Context c) {
		mContext = c;
		this.mLayoutInflater = LayoutInflater.from(mContext);
	}

	static class ViewHolder {
		public TextView orderListText;
	}

	public int getCount() {
		return orderList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void addMenuItem(String menuItem) {
		this.orderList.add(menuItem);
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.order_row, null);
			holder = new ViewHolder();
			holder.orderListText = (TextView) convertView
					.findViewById(R.id.orderListName);
			convertView.setTag(holder);
		}
		order_name = (String) orderList.toArray()[position];
		holder = (ViewHolder) convertView.getTag();
		holder.orderListText.setText(order_name);

		return convertView;
	}

}
