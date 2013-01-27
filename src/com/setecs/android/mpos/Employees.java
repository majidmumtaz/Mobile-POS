package com.setecs.android.mpos;

import com.setecs.android.mpos.provider.DatabaseHelper;

import android.content.Context;

public class Employees {
		
		private int _id;
		private String fullName;
		String PIN;
		int empRole;
		String mobileNo;
		String emailAddress;
		
		public Employees(String fName, int empRole, String PIN,  String mNo, String eAdd)
		{
			
			this.fullName = fName;			
			this.empRole = empRole;
			this.PIN= PIN;
			this.mobileNo = mNo;
			this.emailAddress = eAdd;
		}
		
		public Employees(String fName,int empRole)
		{
			this.fullName = fName;
			this.empRole = empRole;
		}
		
		public int getID()
		{
			return this._id;
		}
		public void SetID(int ID)
		{
			this._id = ID;
		}
		
		public String getName()
		{
			return this.fullName;
		}
		
		public int getRole()
		{
			return this.empRole;
		}
		
		public void setName(String fName)
		{
			this.fullName = fName;
		}
		
		public void setRole(int empRole)
		{
			this.empRole = empRole;
		}	
		
		public void setEmpRole(int empRole)
		{
			this.empRole = empRole;
		}
		
		public String getRoleName(Context con, int empRole)
		{
			return new DatabaseHelper(con).GetEmpRole(empRole);
		}
		
		/*public long getDept()
		{
			return this.empRole;
		}*/
		public String getPIN()
		{
			return this.PIN;
		}
		public void setPIN (String pin)
		{
			this.PIN = pin;
		}
		public String getMobileNo()
		{
			return this.mobileNo;
		}
		public void setMobileNo(String mno)
		{
			this.mobileNo = mno;
		}
		public String getEmail()
		{
			return this.emailAddress;
		}
		public void setEmail(String email)
		{
			this.emailAddress= email;
		}
	}

