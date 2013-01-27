package com.setecs.android.mpos.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.setecs.android.mpos.R;
import com.setecs.android.mpos.provider.MenuCategory;

public class CategoryAdapter extends CursorAdapter {

	public CategoryAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
	}

	private static class ViewHolder {
		int nameIndex;
		public TextView name;
	}

	public void bindView(View view, Context context, Cursor cursor) {
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.name.setText(cursor.getString(holder.nameIndex));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View view = LayoutInflater.from(context).inflate(R.layout.category_row,
				null);
		ViewHolder holder = new ViewHolder();
		holder.name = (TextView) view.findViewById(R.id.category_name);
		holder.nameIndex = cursor
				.getColumnIndexOrThrow(MenuCategory.Categories.CATEGORY_NAME);
		view.setTag(holder);
		return view;
	}

}
