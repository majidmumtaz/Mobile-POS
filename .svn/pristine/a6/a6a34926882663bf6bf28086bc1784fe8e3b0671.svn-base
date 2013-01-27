package com.setecs.android.mpos.fragments;

import com.setecs.android.mpos.R;
import com.setecs.android.mpos.provider.MenuCategory;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ModifyCategoryDialogFragment extends DialogFragment {

	String catID, catName;
	public static ModifyCategoryDialogFragment newInstance(String strID, String catName){
		
		ModifyCategoryDialogFragment frag = new ModifyCategoryDialogFragment();
		frag.catID = strID;
		frag.catName = catName;
		return frag;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		final View  v = inflater.inflate(R.layout.add_category_dialog, null);
		final EditText categoryNameEditText = (EditText) v
								.findViewById(R.id.categoryName);
		categoryNameEditText.setText(catName);
		return new AlertDialog.Builder(getActivity())
		.setTitle("Modify Category")
		.setView(v)
		.setCancelable(true)
		.setNegativeButton("Modify", 
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						
						if (!categoryNameEditText.getText().toString()
								.equals("")) {
							//addCategoryToDB(categoryNameEditText
								//	.getText().toString());
							ContentValues values = new ContentValues();
				    		values.put(MenuCategory.Categories.CATEGORY_NAME, categoryNameEditText.getText().toString());
				    		getActivity().getContentResolver().update(
				    				MenuCategory.Categories.CONTENT_URI.buildUpon()
				    				.appendPath(String.valueOf(catID)).build(), values, null, null);
						}
						dismiss();
						
					}
				}).setPositiveButton("Cancel", null).create();
}	
	
	                                                                            
}
