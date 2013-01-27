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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.setecs.android.mpos.R;
import com.setecs.android.mpos.provider.MenuCategory;

public class CategoryFragment extends Fragment {

	private SimpleCursorAdapter mAdapter;
	OnCategorySelectedListener mCallback;

	public void onCreate(Bundle state) {
		super.onCreate(state);
	}

	// Container Activity must implement this interface
	public interface OnCategorySelectedListener {
		public void onCategorySelected(int position);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle b) {
		View view = inflater.inflate(R.layout.category_list, container, false);
		ListView listView = (ListView) view.findViewById(R.id.cat_list);

		Cursor categoryCursor = getActivity().managedQuery(
				MenuCategory.Categories.CONTENT_URI, null, null, null, null);

		mAdapter = new SimpleCursorAdapter(getActivity(),
				R.layout.category_row, categoryCursor,
				new String[] { MenuCategory.Categories.CATEGORY_NAME },
				new int[] { R.id.category_name });

		listView.setAdapter(mAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, send event to the host activity
				mCallback.onCategorySelected(position);
			}
		});

		return view;
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		try {
			mCallback = (OnCategorySelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnCategorySelectedListener");
		}
	}

}
