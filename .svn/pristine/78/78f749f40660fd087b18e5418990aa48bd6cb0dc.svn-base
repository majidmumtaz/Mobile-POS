package com.setecs.android.mpos.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.setecs.android.mpos.R;
import com.setecs.android.mpos.adapters.OrderAdapter;

public class OrderFragment extends Fragment {

	public static String ARG_POSITION = "order_position";
	private OrderAdapter adapter;

	public void onCreate(Bundle state) {
		super.onCreate(state);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle b) {
		View view = inflater.inflate(R.layout.order_list, container, false);

		ListView listview = (ListView) view.findViewById(R.id.order_list);
		adapter = new OrderAdapter(getActivity());

		listview.setAdapter(adapter);

		// attach the add button
		Button addCategory = (Button) view.findViewById(R.id.pay);
		addCategory.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				pay();
			}
		});

		return view;
	}

	private void pay() {
		FragmentManager fm = getFragmentManager();
		if (fm.findFragmentByTag("dialog") == null) {

			PayDialogFragment frag = PayDialogFragment.newInstance();
			frag.show(fm, "dialog");
		}
	}

	public void updateOrderView(int position) {
		adapter.addMenuItem("100");
		adapter.notifyDataSetChanged();
	}

}
