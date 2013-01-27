package com.setecs.android.mpos.adapters;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.setecs.android.mpos.R;
import com.setecs.android.mpos.objects.MenuItem;

public class MenuAdapter extends BaseAdapter implements
LoaderManager.LoaderCallbacks<Cursor> {

	private Context mContext;
	private final LayoutInflater mLayoutInflater;
	private String menu[] = { "Menu 1", "Menu 2", "Menu 3" };
	
	private int icons[];
	
	final String[] PROJECTION = new String[]{
											 MenuItem.MenuColumns.MENU_ITEM_NAME,
											 MenuItem.MenuColumns.MENU_ITEM_DESCRIPTION, 
											 MenuItem.MenuColumns.MENU_ITEM_PRICE	
											};
	private String menu_item = "";

	public MenuAdapter(Context c, int icons[]) {
		mContext = c;
		
		this.icons = icons;
		
		this.mLayoutInflater = LayoutInflater.from(mContext);
		
	}

	static class ViewHolder {
		public ImageView menuImage;
		public TextView menuText;
		public TextView menuTitle;
		public TextView menuItemDescription;
		public TextView menuItemPrice;
	}

	public int getCount() {
		return menu.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void setIconList(int icons[]) {
		this.icons = icons;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.inventory_menu_listrow, null);//menu_item
			holder = new ViewHolder();
			holder.menuImage = (ImageView) convertView
					.findViewById(R.id.list_image);
			//holder.menuText = (TextView) convertView
				//	.findViewById(R.id.menuItemText);
			holder.menuTitle = (TextView) convertView
					.findViewById(R.id.menu_title);
			holder.menuItemDescription = (TextView) convertView
					.findViewById(R.id.menu_description);
			holder.menuItemPrice = (TextView) convertView
					.findViewById(R.id.menuitem_price);
			convertView.setTag(holder);
		}
		
		menu_item = (String) menu[position];
		holder = (ViewHolder) convertView.getTag();
		holder.menuImage.setImageResource(icons[position]);
		holder.menuTitle.setText(MenuItem.MenuColumns.MENU_ITEM_NAME);
		holder.menuItemDescription.setText(MenuItem.MenuColumns.MENU_ITEM_DESCRIPTION);
		holder.menuItemPrice.setText(MenuItem.MenuColumns.MENU_ITEM_PRICE);
		//holder.menuTitle
		//holder.menuText.setText(menu_item);

		return convertView;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		return new CursorLoader(mContext, MenuItem.MenuColumns.CONTENT_URI,
	    		PROJECTION, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
