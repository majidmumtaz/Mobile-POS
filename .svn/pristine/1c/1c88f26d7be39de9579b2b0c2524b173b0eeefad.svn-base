package com.setecs.android.mpos.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class PersonnelRegister {
	
	public static final String AUTHORITY = "com.setecs.android.mpos.RegistrationProvider";
	
	public static final class PRegistration implements BaseColumns {

		public static final String DEFAULT_SORT_ORDER = "modified DESC";

		// This class cannot be instantiated
		private PRegistration() {
		}

		// uri references all personnel
		public static final Uri REGISTRATION_URI = Uri.parse("content://"
				+ AUTHORITY + "/" + PRegistration.PREGISTRATION);

		public static final String PREGISTRATION = "personnel";

		public static final Uri CONTENT_URI = REGISTRATION_URI;

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.mpos.menu.personnel";

		public static final String CONTENT_CATEGORY_TYPE = "vnd.android.cursor.item/vnd.mpos.menu.personnel";

		public static final String REGISTRATION_ID = "_id";

		public static final String P_FNAME = "fullname";
		
		public static final String P_GENDER = "gender";
		
		public static final String P_ROLE = "emproleid";
		
		public static final String P_PIN = "pin";
		
		public static final String P_MNO = "mobileno";
		
		public static final String P_EMAIL = "emailaddress";
		

	}

}
