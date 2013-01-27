package com.setecs.android.mpos.fragments;

import com.setecs.android.mpos.R;
import com.setecs.android.mpos.objects.MenuItem;
import com.setecs.android.mpos.provider.MenuCategory;
import com.setecs.android.mpos.provider.PersonnelRegister;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

	public class ModifyMenuItemDialogFragment extends DialogFragment {

		private String menuItemID, menuItemName, menuItemPrice, menuCatID, menuItemDes;
	private int menuCatId;
	public static ModifyMenuItemDialogFragment newInstance(String mID, String mName, String mPrice, String menuCatID, String mDes) {
		
		ModifyMenuItemDialogFragment frag = new ModifyMenuItemDialogFragment();
		
		frag.menuItemID = mID;
		frag.menuItemName = mName;
		frag.menuItemPrice = mPrice;
		frag.menuCatID = menuCatID;
		frag.menuItemDes = mDes;
		
		return frag;
		
	}	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		final View v = inflater.inflate(R.layout.add_menu_item_dialog, null);

		Spinner spinner = (Spinner) v.findViewById(R.id.menuCategorySpinner);
		final EditText menutxtName = (EditText) v.findViewById(R.id.menuName);
		final EditText menutxtDescription = (EditText) v.findViewById(R.id.menuDescription);
		final EditText  menutxtPrice= (EditText) v.findViewById(R.id.menuPrice);
		
		menutxtName.setText(menuItemName);
		menutxtPrice.setText(menuItemPrice);
		menutxtDescription.setText(menuItemDes);
		// Load a Spinner and bind it to a data query.
		final String[] PROJECTION = new String[] {	MenuCategory.Categories.CATEGORY_ID, 
													MenuCategory.Categories.CATEGORY_NAME
		    									 };
		
		final Cursor categoryCursor = getActivity().managedQuery(
				MenuCategory.Categories.CONTENT_URI, PROJECTION, null, null, null);
		
		String[] columns = new String[] { MenuCategory.Categories.CATEGORY_NAME	};
				
		int[] to = new int[] { android.R.id.text1 };

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),
				android.R.layout.simple_list_item_1, categoryCursor, columns,
				to);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
				
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
	        public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
	        	menuCatId= (int) id;
	        	Toast.makeText(getActivity(), "You click on item!" + position + "menu ID:"+ menuCatId, Toast.LENGTH_SHORT).show();
	        	setMenuCatId(menuCatId);
	        }
	        public void onNothingSelected(AdapterView<?> parent) {}						
	        
		});		
		
		return new AlertDialog.Builder(getActivity())
				.setTitle("Modify Menu Item")
				.setView(v)
				.setCancelable(true)
				.setNegativeButton("Modify",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,
									int which) {													
							
							ContentValues values = new ContentValues();
							values.put(MenuItem.MenuColumns.MENU_ITEM_NAME, 
									menutxtName.getText().toString());
							values.put(MenuItem.MenuColumns.MENU_ITEM_PRICE, 
									menutxtPrice.getText().toString());
							values.put(MenuItem.MenuColumns.MENU_ITEM_DESCRIPTION, 
									menutxtDescription.getText().toString());
								/*if (!menuName.equals("")
										|| !menuPrice.equals("")
										|| !menuDescription.equals("")) {
									//addCategoryToDB(menuName, menuPrice,
										//	menuDescription);
								}*/
							
							getActivity().getContentResolver().update(
									MenuItem.MenuColumns.CONTENT_URI.buildUpon()
				    				.appendPath(String.valueOf(menuItemID)).build(), values, null, null);
							}
						}).setPositiveButton("Cancel", null).create();
	}
	
	public void setMenuCatId(int catId) {
		menuCatId = catId;

	}

}
