package com.setecs.android.mpos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ActivityInfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends Activity  {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//final boolean customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.main);
		
		// For Custom titel bar
		/*if (customTitleSupported) {
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, com.setecs.android.mpos.R.layout.title_bar);
            //setFeatureDrawableResource(Window.FEATURE_RIGHT_ICON, R.menu.main_activity);
        }
       final TextView myTitleText = (TextView) findViewById(com.setecs.android.mpos.R.id.titlet_bar);
       	if (myTitleText != null) {
            myTitleText.setText(" Mobile POS Station");

       }*/
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity, menu);
		//MenuItem item= menu.findItem(com.setecs.android.mpos.R.id.menuid_exit);
		//item.setVisible(false);
				
		return true;
	}

	public void onInventoryMenuClick(MenuItem item) {
		//Intent i = new Intent(this, InventoryActivity.class);
		//startActivity(i);
		Toast.makeText(this, "You are not Loged in! Please Login", Toast.LENGTH_SHORT).show();
	}
	public void onAdministrationMenuClick(MenuItem item){
		//Intent i = new Intent(this, AdministrationActivity.class);
		//startActivity(i);
		Toast.makeText(this, "You are not Loged in! Please Login", Toast.LENGTH_SHORT).show();
	}
	
	public void onOrderMenuClick(MenuItem item){
		//Intent i = new Intent(this, OrderActivity.class);
		//startActivity(i);
		Toast.makeText(this, "You are not Loged in! Please Login", Toast.LENGTH_SHORT).show();
	}
	public void onExitMenuClick(MenuItem item){
				 doExit();   
	}
	public void onLoginMenuClick(MenuItem item){
		
		Toast.makeText(this, "You are Loged in!", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(this, AdministrationActivity.class);
		startActivity(i);
	}
	
	@Override
	public void onBackPressed() {

	    doExit();
	    
	}
	private void doExit() {

	    AlertDialog.Builder alertDialog = new AlertDialog.Builder(
	            HomeActivity.this);

	    alertDialog.setNegativeButton("Yes", new OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
	        	
	        	android.os.Process.killProcess(android.os.Process.myPid());
	        	finish();
	        }
	    });

	    alertDialog.setPositiveButton("No", null);
	    alertDialog.setMessage("Do you want to exit?");
	    alertDialog.setTitle("Mobile POS Station");
	    alertDialog.show();
	}	

}