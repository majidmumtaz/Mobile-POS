package com.setecs.android.mpos.adapters;

import com.setecs.android.mpos.R;
import com.setecs.android.mpos.adapters.MenuAdapter.ViewHolder;
import com.setecs.android.mpos.objects.MenuItem;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MenuListAdapter extends SimpleCursorAdapter {

	
	//private LayoutInflater mLayoutInflater;
	private int layout;
	private int icons[];
	public MenuListAdapter(Context context, int layout, 
			Cursor c, String[] from, int[] to) {
		super(context, layout, c, from, to);
		// TODO Auto-generated constructor stub
		this.layout=layout;
	}
	public void setIconList(int icons[]) {
		this.icons = icons;
	}
	
	static class ViewHolder{
		public ImageView menuImage;
		public TextView menuText;
		public TextView menuTitle;
		public TextView menuItemDescription;
		public TextView menuItemPrice;
	}
	// create a new ImageView for each item referenced by the Adapter
	/*public View getView(int position, View convertView, ViewGroup parent) {

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
		//menu_item = (String) menu[position];
		holder = (ViewHolder) convertView.getTag();
		holder.menuImage.setImageResource(icons[position]);
		//holder.menuTitle.setText(MenuItem.MenuColumns.MENU_ITEM_NAME);
		//holder.menuItemDescription.setText(MenuItem.MenuColumns.MENU_ITEM_DESCRIPTION);
		//holder.menuItemPrice.setText(MenuItem.MenuColumns.MENU_ITEM_PRICE);
		//holder.menuTitle
		//holder.menuText.setText(menu_item);

		return convertView;
	}*/
	
	@Override
	public View newView(Context context, Cursor cursor, 
	ViewGroup parent) {
		View v = LayoutInflater.from(context).inflate(R.layout.inventory_menu_listrow, null);
	
		return v;
	}
	
	@Override
	public void bindView(View v, Context context, Cursor c) {
		ViewHolder holder;
		if (v== null){
			v = LayoutInflater.from(context).inflate(R.layout.inventory_menu_listrow, null);
			holder = new ViewHolder();
			holder.menuImage = (ImageView) v
					.findViewById(R.id.list_image);
			
			holder.menuTitle = (TextView) v
					.findViewById(R.id.menu_title);
			
			holder.menuItemPrice = (TextView) v
					.findViewById(R.id.menuitem_price);
			
			holder.menuItemDescription = (TextView) v
					.findViewById(R.id.menu_description);
			v.setTag(holder);
		}
		
		int menuTitleCol = c.getColumnIndex(MenuItem.MenuColumns.MENU_ITEM_NAME);		
		int menuItemPriceCol = c.getColumnIndex(MenuItem.MenuColumns.MENU_ITEM_PRICE);
		int menuItemDesCol = c.getColumnIndex(MenuItem.MenuColumns.MENU_ITEM_DESCRIPTION);
		
		
		String strmenuTitle = c.getString(menuTitleCol);		
		String strmenuItemPrice= c.getString(menuItemPriceCol);
		String strmenuItemDes = c.getString(menuItemDesCol);
		
		TextView tvTitle = (TextView) v
			.findViewById(R.id.menu_title);
		tvTitle.setText(strmenuTitle);		
		
		
		TextView tvPrice = (TextView) v
			.findViewById(R.id.menuitem_price);
		tvPrice.setText(strmenuItemPrice);
		
		TextView tvDes = (TextView) v
			.findViewById(R.id.menu_description);
		tvDes.setText(strmenuItemDes);
	}
}
