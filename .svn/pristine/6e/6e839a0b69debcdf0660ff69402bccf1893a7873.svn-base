package com.setecs.android.mpos.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.setecs.android.mpos.Employees;
import com.setecs.android.mpos.R;
import com.setecs.android.mpos.provider.*;
public class AlertBox {

	
	public static AlertDialog ShowEditDialog(final Context con,final Employees emp)
	{
		AlertDialog.Builder b=new AlertDialog.Builder(con);
		b.setTitle("Personnel Details");
		LayoutInflater li=LayoutInflater.from(con);
		View v=li.inflate(R.layout.personnel_detail, null);
		
		//b.setIcon(android.R.drawable.ic_input_get);
		
		b.setView(v);
		final EditText txtFullName = (EditText)v.findViewById(R.id.personnel_txtbfname);
		final Spinner spinRole = (Spinner)v.findViewById(R.id.personnelRoleSpinner);
		final EditText txtPIN = (EditText)v.findViewById(R.id.personnel_txtbpin);
		final EditText txtMobileNo = (EditText)v.findViewById(R.id.personnel_txtbmobileno);
		final EditText txtEmailAdd = (EditText)v.findViewById(R.id.personnel_txtbemailaddress);
		
		//ManageDeptSpinner(con, spinRole);
		
		for(int i=0;i<spinRole.getCount();i++)
		{
			long id=spinRole.getItemIdAtPosition(i);
			if(id==emp.getRole())
			{
				spinRole.setSelection(i, true);
				break;
			}
		}
		
		
		txtFullName.setText(emp.getName());
		txtPIN.setText(emp.getPIN());
		txtMobileNo.setText(emp.getMobileNo());
		txtEmailAdd.setText(emp.getEmail());
		
		b.setPositiveButton("Modify", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				emp.setName(txtFullName.getText().toString());				
				emp.setEmpRole((int)spinRole.getItemIdAtPosition(spinRole.getSelectedItemPosition()));
				emp.setPIN(txtPIN.getText().toString());
				emp.setMobileNo(txtMobileNo.getText().toString());
				emp.setEmail(txtEmailAdd.getText().toString());
								
				try
				{
				DatabaseHelper db=new DatabaseHelper(con);
				db.UpdateEmp(emp);
				
				}
				catch(Exception ex)
				{
					CatchError(con, ex.toString());
				}
			}
		});
		
		b.setNeutralButton("Delete", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				DatabaseHelper db=new DatabaseHelper(con);
				db.DeleteEmp(emp);
			}
		});
		b.setNegativeButton("Cancel", null);
		
		return b.create();
		//diag.show();
		
	}

	static public void CatchError(Context con, String Exception)
	{
		Dialog diag=new Dialog(con);
		diag.setTitle("Error");
		TextView txt=new TextView(con);
		txt.setText(Exception);
		diag.setContentView(txt);
		diag.show();
	}
	
	/*static public void ManagePersonnelRoleSpinner(Context context,Spinner view)
	{
		DatabaseHelper dbHelper=new DatabaseHelper(context);
		Cursor c=dbHelper.getAllempRole();
		//context.startManagingCursor(c);
		
		
		//SimpleCursorAdapter ca=new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item, c, new String [] {DatabaseHelper.colDeptName}, new int []{android.R.id.text1});
		SimpleCursorAdapter ca=new SimpleCursorAdapter(context,R.layout.personnel_detail, c, new String [] {DatabaseHelper.colempRoleName,"_id"}, new int []{R.id.personnelRoleSpinner});
		view.setAdapter(ca);
		
	}*/
}
