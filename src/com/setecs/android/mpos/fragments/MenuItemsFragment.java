package com.setecs.android.mpos.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;

import com.setecs.android.mpos.R;
import com.setecs.android.mpos.adapters.MenuAdapter;
import com.setecs.android.mpos.adapters.MenuListAdapter;
import com.setecs.android.mpos.objects.MenuItem;

public class MenuItemsFragment extends Fragment {

	public static String ARG_POSITION = "category_position";

	private int beerIcons[] = { R.drawable.beer1, R.drawable.beer2,
			R.drawable.beer3 };
	private int soupIcons[] = { R.drawable.soup1, R.drawable.soup2,
			R.drawable.soup3 };
	private int chosenIcons[] = beerIcons;
	//private MenuAdapter adapter;
	private final String[] from = new String[]{
			 MenuItem.MenuColumns.MENU_ITEM_NAME,			  
			 MenuItem.MenuColumns.MENU_ITEM_PRICE,
			 MenuItem.MenuColumns.MENU_ITEM_DESCRIPTION
			};
	int [] to = new int[] {	com.setecs.android.mpos.R.id.menu_title,							
			com.setecs.android.mpos.R.id.menuitem_price,
			com.setecs.android.mpos.R.id.menu_description};
//private MenuAdapter adapter;
	private MenuListAdapter cadapter;

	OnMenuItemSelectedListener mCallback;

	public void onCreate(Bundle state) {
		super.onCreate(state);
	}

	// Container Activity must implement this interface
	public interface OnMenuItemSelectedListener {
		public void onMenuItemSelected(int position);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle b) {
		View view = inflater.inflate(R.layout.menu_list, container, false);
		
		Cursor cursor = getActivity().getContentResolver().query(
				 MenuItem.MenuColumns.CONTENT_URI, new String[] {MenuItem.MenuColumns.MENU_ITEM_ID, 
						 MenuItem.MenuColumns.MENU_ITEM_NAME, MenuItem.MenuColumns.MENU_ITEM_PRICE,
						 MenuItem.MenuColumns.MENU_ITEM_DESCRIPTION}, null, null, null);
		   getActivity().startManagingCursor(cursor);
		//GridView gridview = (GridView) view.findViewById(R.id.menu_gridview);
		ListView lv = (ListView) view.findViewById(R.id.menu_gridview);

		//adapter = new MenuAdapter(getActivity(), chosenIcons);
		//lv.setAdapter(adapter);
		cadapter = new MenuListAdapter(getActivity(),
				com.setecs.android.mpos.R.layout.inventory_menu_listrow, cursor, from, to);
		
		lv.setAdapter(cadapter);
		cadapter.notifyDataSetChanged();

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				mCallback.onMenuItemSelected(position);
			}
		});

		return view;
	}

	public void updateMenuView(int position) {
		switch (position) {
		case 0:
			cadapter.setIconList(beerIcons);
			break;
		case 1:
			cadapter.setIconList(soupIcons);
			break;
		}
		cadapter.notifyDataSetChanged();
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		try {
			mCallback = (OnMenuItemSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnCategorySelectedListener");
		}
	}

}
