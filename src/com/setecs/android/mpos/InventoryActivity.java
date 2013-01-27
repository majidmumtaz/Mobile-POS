package com.setecs.android.mpos;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.setecs.android.mpos.fragments.InventoryCategoryFragment;
import com.setecs.android.mpos.fragments.InventoryMenuItemsFragment;
import com.setecs.android.mpos.fragments.MenuItemsFragment;

public class InventoryActivity extends Activity implements
		InventoryCategoryFragment.OnCategorySelectedListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inventory_layout);
		setTitle("Mobile POS Station" + "\t\t\t\t\t\t\t\t\t Services Station");
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
		//Intent i = new Intent(this, InventoryActivity.class);
		//startActivity(i);
	}
	public void onAdministrationMenuClick(MenuItem item){
		Intent i = new Intent(this, AdministrationActivity.class);
		startActivity(i);
	}
	
	public void onOrderMenuClick(MenuItem item){
		Intent i = new Intent(this, OrderActivity.class);
		startActivity(i);
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

		InventoryMenuItemsFragment menuFrag = (InventoryMenuItemsFragment) getFragmentManager()
				.findFragmentById(R.id.menu);

		if (menuFrag != null) {
			menuFrag.setCategoryId(position);
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
			transaction.replace(R.id.inventory_main_container, newFragment);
			transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			Intent intent = new Intent(this, OrderActivity.class); //HomeActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	

	@Override
	public void enableDisableAddMenuButton() {
		// TODO Auto-generated method stub
		InventoryCategoryFragment catFrag = (InventoryCategoryFragment) getFragmentManager()
								.findFragmentById(com.setecs.android.mpos.R.id.category);
		InventoryMenuItemsFragment inventoryFrag = (InventoryMenuItemsFragment)getFragmentManager()
								.findFragmentById(com.setecs.android.mpos.R.id.menu);

		if (catFrag != null){
			inventoryFrag.btnAddMenuVisibility();
		}
		else{	
			
			inventoryFrag.btnAddMenuGone();
			InventoryMenuItemsFragment newf = new InventoryMenuItemsFragment();
			FragmentTransaction transaction = getFragmentManager().beginTransaction();
			transaction.replace(com.setecs.android.mpos.R.id.inventory_main_container, newf);
			transaction.addToBackStack(null);
			transaction.commit();
		}
	}

	@Override
	public void onDisablingAddCat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableRegisterPersonnel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableRegisterPersonnel() {
		// TODO Auto-generated method stub
		
	}

	
	

}
