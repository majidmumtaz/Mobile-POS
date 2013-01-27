package com.setecs.android.mpos.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.setecs.android.mpos.R;

public class PayDialogFragment extends DialogFragment {

	public static PayDialogFragment newInstance() {
		PayDialogFragment frag = new PayDialogFragment();
		return frag;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		final View v = inflater.inflate(R.layout.pay_dialog, null);

		return new AlertDialog.Builder(getActivity())
				.setTitle("Payment Total: $ 100.00").setView(v)
				.setCancelable(true).setNegativeButton("Cancel", null).create();
	}

}
