package com.setecs.android.mpos.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.setecs.android.mpos.R;

public class MenuItemsDescriptionFragment extends Fragment {

	
	public void onCreate(Bundle state) {
		super.onCreate(state);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle b) {
		View view = inflater.inflate(R.layout.menu_description, container, false);
	
		return view;
	}
}
