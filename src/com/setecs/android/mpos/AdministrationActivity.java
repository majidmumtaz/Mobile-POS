package com.setecs.android.mpos;

import com.setecs.android.mpos.fragments.InventoryCategoryFragment;
import com.setecs.android.mpos.fragments.InventoryMenuItemsFragment;
import com.setecs.android.mpos.fragments.MenuItemsFragment;
import com.setecs.android.mpos.fragments.PersonnelListFragment;
import com.setecs.android.mpos.fragments.PersonnelRegisteredFragments;

import android.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.setecs.android.mpos.fragments.ModifyPersonnelDetail;
public class AdministrationActivity extends Activity implements
InventoryCategoryFragment.OnCategorySelectedListener,
PersonnelListFragment.OnPersonnelSelectedListener{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(com.setecs.android.mpos.R.layout.administration_layout);  
		setTitle("Mobile POS Station" + "\t\t\t\t\t\t\t\t\t Manager Station ");
		//setTitle("Mobile POS Station" + getString(com.setecs.android.mpos.R.string.testing_string));
		/*if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, com.setecs.android.mpos.R.layout.title_bar);
        }
       final TextView myTitleText = (TextView) findViewById(com.setecs.android.mpos.R.id.titlet_bar);
       	if (myTitleText != null) {
            myTitleText.setText(" Manager Station");

       }      */ 
		
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
		//Intent i = new Intent(this, AdministrationActivity.class);
		//startActivity(i);
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
	public void onLoginMenuClick(MenuItem item){}
		
	@Override
	public void onCategorySelected(int position) {
		// The user selected a menu category from the CategoryFragment
		// Do something here to display the list of menus in that category

		InventoryMenuItemsFragment menuFrag = (InventoryMenuItemsFragment) getFragmentManager()
		.findFragmentById(com.setecs.android.mpos.R.id.menu);

		if (menuFrag != null) {
			menuFrag.setCategoryId(position);
			menuFrag.btnAddMenuVisibility();
			//Layout layout = (Layout) findViewById(com.setecs.android.mpos.R.id.btn_catlayout);
		} else {
			// Otherwise, we're in the one-pane layout and must swap frags...

			// Create fragment and give it an argument for the selected category
			
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
			transaction.replace(com.setecs.android.mpos.R.id.admin_main_container, newFragment);
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
			Intent intent = new Intent(this, OrderActivity.class);///HomeActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public void onPersonnelSelected(int position) {
		// TODO Auto-generated method stub
		PersonnelRegisteredFragments menuFrag = (PersonnelRegisteredFragments) getFragmentManager()
												.findFragmentById(com.setecs.android.mpos.R.id.menu);
		
		if (menuFrag != null) {
			menuFrag.setPersonnelId(position);
		}else {
			// Otherwise, we're in the one-pane layout and must swap frags...

			// Create fragment and give it an argument for the selected personnel
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
			transaction.replace(com.setecs.android.mpos.R.id.admin_main_container, newFragment);
			transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();
		}
	}

	@Override
	public void enableDisableViewGroup() {
		// TODO Auto-generated method stub
		InventoryCategoryFragment catFrag = (InventoryCategoryFragment) getFragmentManager()
							.findFragmentById(com.setecs.android.mpos.R.id.category);
		PersonnelListFragment pListFrag = (PersonnelListFragment) getFragmentManager()
							.findFragmentById(com.setecs.android.mpos.R.id.personel);
		
		if (pListFrag !=null){
			
			catFrag.btnAddCategoryVisibility();
			//catFrag.getView().setVisibility(View.VISIBLE);
			
		}
		
		else{	
			//catFrag.LinearViewVisible();
			catFrag.btnAddCategoryGone();
			Fragment newf = new InventoryCategoryFragment();
			FragmentTransaction transaction = getFragmentManager().beginTransaction();
			transaction.replace(com.setecs.android.mpos.R.id.admin_main_container, newf);
			transaction.addToBackStack(null);
			transaction.commit();
		}
		
	}

	@Override
	public void enableDisableAddMenuButton() {
		
		InventoryCategoryFragment catFrag = (InventoryCategoryFragment) getFragmentManager()
								.findFragmentById(com.setecs.android.mpos.R.id.category);
		InventoryMenuItemsFragment inventoryFrag = (InventoryMenuItemsFragment)getFragmentManager()
								.findFragmentById(com.setecs.android.mpos.R.id.menuitems);
		
		if (catFrag != null){
			inventoryFrag.btnAddMenuVisibility();
		}
		else{	
			
			//inventoryFrag.btnAddMenuGone();
			InventoryMenuItemsFragment newf = new InventoryMenuItemsFragment();
			FragmentTransaction transaction = getFragmentManager().beginTransaction();
			transaction.replace(com.setecs.android.mpos.R.id.admin_main_container, newf);
			transaction.addToBackStack(null);
			transaction.commit();
		}
	}

	@Override
	public void onDisablingAddCat() {
		
		InventoryCategoryFragment catFrag = (InventoryCategoryFragment) getFragmentManager()
		.findFragmentById(com.setecs.android.mpos.R.id.category);
		
		if(catFrag != null){
			
			catFrag.btnAddCategoryGone();
		}
	}

	@Override
	public void enabledAddCat() {
		// TODO Auto-generated method stub
		InventoryCategoryFragment catFrag = (InventoryCategoryFragment) getFragmentManager()
				.findFragmentById(com.setecs.android.mpos.R.id.category);
		PersonnelListFragment pListFrag = (PersonnelListFragment) getFragmentManager()
				.findFragmentById(com.setecs.android.mpos.R.id.personel);
		InventoryMenuItemsFragment inventoryFrag = (InventoryMenuItemsFragment)getFragmentManager()
				.findFragmentById(com.setecs.android.mpos.R.id.menuitems);
		if (pListFrag !=null){

			catFrag.btnAddCatEnabled();
			inventoryFrag.disableAddMenuButton();
			//catFrag.getView().setVisibility(View.VISIBLE);

		}

		else{	
			//catFrag.LinearViewVisible();
				catFrag.btnAddCategoryGone();
				Fragment newf = new InventoryCategoryFragment();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(com.setecs.android.mpos.R.id.admin_main_container, newf);
				transaction.addToBackStack(null);
				transaction.commit();
		}
	}

	@Override
	public void disableAddCat() {
		// TODO Auto-generated method stub
		InventoryCategoryFragment catFrag = (InventoryCategoryFragment) getFragmentManager()
									.findFragmentById(com.setecs.android.mpos.R.id.category);
		PersonnelListFragment pListFrag = (PersonnelListFragment) getFragmentManager()
									.findFragmentById(com.setecs.android.mpos.R.id.personel);
		InventoryMenuItemsFragment inventoryFrag = (InventoryMenuItemsFragment)getFragmentManager()
									.findFragmentById(com.setecs.android.mpos.R.id.menuitems);

		if (pListFrag !=null){

			catFrag.btnAddCatDisabled();			
			inventoryFrag.disableAddMenuButton();
		}

		else{	
				//catFrag.LinearViewVisible();
				catFrag.btnAddCategoryGone();
				Fragment newf = new InventoryCategoryFragment();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(com.setecs.android.mpos.R.id.admin_main_container, newf);
				transaction.addToBackStack(null);
				transaction.commit();
		}
		
	}

	@Override
	public void enableRegisterPersonnel() {
		// TODO Auto-generated method stub
		InventoryCategoryFragment catFrag = (InventoryCategoryFragment) getFragmentManager()
						.findFragmentById(com.setecs.android.mpos.R.id.category);
		PersonnelListFragment pListFrag = (PersonnelListFragment) getFragmentManager()
						.findFragmentById(com.setecs.android.mpos.R.id.personel);
		InventoryMenuItemsFragment inventoryFrag = (InventoryMenuItemsFragment)getFragmentManager()
						.findFragmentById(com.setecs.android.mpos.R.id.menuitems);

		if (catFrag !=null){

			pListFrag.btnenableRegisterPersonnel();			
			inventoryFrag.disableAddMenuButton();
		}

		else{	
				//catFrag.LinearViewVisible();
				//catFrag.btnAddCategoryGone();
				Fragment newf = new PersonnelListFragment();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(com.setecs.android.mpos.R.id.admin_main_container, newf);
				transaction.addToBackStack(null);
				transaction.commit();
		}
		
	}

	@Override
	public void disableRegisterPersonnel() {
		// TODO Auto-generated method stub
	
		InventoryCategoryFragment catFrag = (InventoryCategoryFragment) getFragmentManager()
					.findFragmentById(com.setecs.android.mpos.R.id.category);
		PersonnelListFragment pListFrag = (PersonnelListFragment) getFragmentManager()
					.findFragmentById(com.setecs.android.mpos.R.id.personel);
		InventoryMenuItemsFragment inventoryFrag = (InventoryMenuItemsFragment)getFragmentManager()
					.findFragmentById(com.setecs.android.mpos.R.id.menuitems);

		if (catFrag !=null){

			pListFrag.btndisableRegisterPersonnel();			
			inventoryFrag.enableAddMenuButton();
		}

		else{	
				//catFrag.LinearViewVisible();
				//catFrag.btnAddCategoryGone();
				Fragment newf = new PersonnelListFragment();
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(com.setecs.android.mpos.R.id.admin_main_container, newf);
				transaction.addToBackStack(null);
				transaction.commit();
		}
	}
	
}
