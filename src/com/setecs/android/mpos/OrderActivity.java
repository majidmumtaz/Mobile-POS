package com.setecs.android.mpos;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import com.setecs.android.mpos.fragments.CategoryFragment;
import com.setecs.android.mpos.fragments.MenuItemsFragment;
import com.setecs.android.mpos.fragments.OrderFragment;

public class OrderActivity extends Activity implements
CategoryFragment.OnCategorySelectedListener,
MenuItemsFragment.OnMenuItemSelectedListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.order_layout);
		setTitle("Mobile POS Station" + "\t\t\t\t\t\t\t\t\t Orders Station ");
		
		/*if (customTitleSupported) {
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, com.setecs.android.mpos.R.layout.title_bar);
		}
		final TextView myTitleText = (TextView) findViewById(com.setecs.android.mpos.R.id.titlet_bar);
		if (myTitleText != null) {
			myTitleText.setText(" Mobile POS Station \t\t\t Orders Station");

		}     */ 
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.clear();
		MenuInflater inflater = getMenuInflater();				
		inflater.inflate(com.setecs.android.mpos.R.menu.main_activity, menu);
		MenuItem item = menu.findItem(com.setecs.android.mpos.R.id.menuid_exit);
		item.setTitle("Home");
		item = menu.findItem(com.setecs.android.mpos.R.id.menu_login);
		item.setVisible(false);
		return true;
	}
	public void onInventoryMenuClick(MenuItem item) {
		Intent i = new Intent(this, InventoryActivity.class);
		startActivity(i);
	}
	public void onAdministrationMenuClick(MenuItem item){
		Intent i = new Intent(this, AdministrationActivity.class);
		startActivity(i);
	}
	
	public void onOrderMenuClick(MenuItem item){
		//Intent i = new Intent(this, OrderActivity.class);
		//startActivity(i);
	}
	public void onExitMenuClick(MenuItem item){
		Intent i = new Intent(this, HomeActivity.class);
		startActivity(i);
		//finish();
	}
	public void onLoginMenuClick(MenuItem item){	
	}
	
	@Override
	public void onCategorySelected(int position) {
		// The user selected a menu category from the CategoryFragment
		// Do something here to display the list of menus in that category

		MenuItemsFragment menuFrag = (MenuItemsFragment) getFragmentManager()
				.findFragmentById(R.id.menu_pane);

		if (menuFrag != null) {
			// If menu frag is available, we're in two-pane layout...

			// Call a method in the MenuItemsFragment to update its content
			menuFrag.updateMenuView(position);
		} else {
			// Otherwise, we're in the one-pane layout and must swap frags...

			// Create fragment and give it an argument for the selected article
			MenuItemsFragment newFragment = new MenuItemsFragment();
			Bundle args = new Bundle();
			args.putInt(MenuItemsFragment.ARG_POSITION, position);
			newFragment.setArguments(args);

			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();

			// Replace whatever is in the fragment_container view with this
			// fragment,
			// and add the transaction to the back stack so the user can
			// navigate back
			transaction.replace(R.id.main_container, newFragment);
			transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();
		}
	}

	@Override
	public void onMenuItemSelected(int position) {
		OrderFragment orderFrag = (OrderFragment) getFragmentManager()
				.findFragmentById(R.id.order_pane);
		if (orderFrag != null) {
			orderFrag.updateOrderView(position);
		} else {
			OrderFragment newFragment = new OrderFragment();
			Bundle args = new Bundle();
			args.putInt(OrderFragment.ARG_POSITION, position);
			newFragment.setArguments(args);

			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();

			transaction.replace(R.id.main_container, newFragment);
			transaction.addToBackStack(null);

			transaction.commit();
		}
	}
	

}
