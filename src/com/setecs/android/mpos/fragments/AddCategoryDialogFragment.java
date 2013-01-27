package com.setecs.android.mpos.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.setecs.android.mpos.R;
import com.setecs.android.mpos.provider.MenuCategory;

public class AddCategoryDialogFragment extends DialogFragment {

	public static AddCategoryDialogFragment newInstance() {
		AddCategoryDialogFragment frag = new AddCategoryDialogFragment();
		return frag;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		final View v = inflater.inflate(R.layout.add_category_dialog, null);
		return new AlertDialog.Builder(getActivity())
				.setTitle("Add Category")
				.setView(v)
				.setCancelable(true)
				.setNegativeButton("Add", 
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								EditText categoryNameEditText = (EditText) v
										.findViewById(R.id.categoryName);
								if (!categoryNameEditText.getText().toString()
										.equals("")) {
									addCategoryToDB(categoryNameEditText
											.getText().toString());
								}
							}
						}).setPositiveButton("Cancel", null).create();
	}

	private void addCategoryToDB(String value) {
		ContentValues values = new ContentValues();
		values.put(MenuCategory.Categories.CATEGORY_NAME, value);

		getActivity().getContentResolver().insert(
				MenuCategory.Categories.CONTENT_URI, values);
	}

}
