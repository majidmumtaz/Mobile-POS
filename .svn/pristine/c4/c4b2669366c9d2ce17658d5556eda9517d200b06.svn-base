package com.setecs.android.mpos.fragments;

import com.setecs.android.mpos.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PersonnelRegisteredFragments extends Fragment {

	public static String ARG_POSITION = "personnel_position";
	private int personnelId;
	
	OnPersonnelSelectedListener mCallback, nCallback;
	public void onCreate(Bundle state) {
		super.onCreate(state);
	}
	
	// Container Activity must implement this interface
	public interface OnPersonnelSelectedListener {
		public void onPersonnelSelected(int position);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle b) {
		View view = inflater.inflate(R.layout.personnel_list, container, false);
				
		
		return view;

	}
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		try {
			mCallback = (OnPersonnelSelectedListener) activity;
			nCallback = (OnPersonnelSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnPersonnelSelectedListener");
		}
	}
	
	public void setPersonnelId(int perId) {
		personnelId = perId;
	}

	public int getPersonnelId() {
		return personnelId;
	}
}
