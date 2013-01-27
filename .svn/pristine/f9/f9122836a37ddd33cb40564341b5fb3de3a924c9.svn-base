package com.setecs.android.mpos.provider;

import java.util.HashMap;

import static com.setecs.android.mpos.provider.DatabaseHelper.PERSONNEL_TABLE_NAME;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;


public class RegistrationProvider extends ContentProvider {
	
	private DatabaseHelper dbHelper;
	private static final UriMatcher sUriMatcher;
	
	private static final int ALL_PERSONNEL = 1;
	private static final int SPECIFIC_PERSONNEL_ID = 2;
	
	private static HashMap<String, String> sPersonnelMap;
	
	static {
		
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(PersonnelRegister.AUTHORITY,
				PersonnelRegister.PRegistration.PREGISTRATION, ALL_PERSONNEL);
		sUriMatcher.addURI(PersonnelRegister.AUTHORITY,
				PersonnelRegister.PRegistration.PREGISTRATION + "/#", SPECIFIC_PERSONNEL_ID);

		sPersonnelMap = new HashMap<String, String>();
		sPersonnelMap.put(PersonnelRegister.PRegistration.REGISTRATION_ID,
				PersonnelRegister.PRegistration.REGISTRATION_ID);
		sPersonnelMap.put(PersonnelRegister.PRegistration.P_FNAME,
				PersonnelRegister.PRegistration.P_FNAME);
		sPersonnelMap.put(PersonnelRegister.PRegistration.P_GENDER,
				PersonnelRegister.PRegistration.P_GENDER);
		sPersonnelMap.put(PersonnelRegister.PRegistration.P_ROLE,
				PersonnelRegister.PRegistration.P_ROLE);
		sPersonnelMap.put(PersonnelRegister.PRegistration.P_PIN,
				PersonnelRegister.PRegistration.P_PIN);
		sPersonnelMap.put(PersonnelRegister.PRegistration.P_MNO,
				PersonnelRegister.PRegistration.P_MNO);
		sPersonnelMap.put(PersonnelRegister.PRegistration.P_EMAIL,
				PersonnelRegister.PRegistration.P_EMAIL);		
	}
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		dbHelper = new DatabaseHelper(getContext());
		return true;
	}


	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int match = sUriMatcher.match(uri);		
		int affected;

		switch (match) {
		case ALL_PERSONNEL:
			affected = db.delete(DatabaseHelper.PERSONNEL_TABLE_NAME,
					  (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""),
					   whereArgs);
		break;
		case SPECIFIC_PERSONNEL_ID:
			long perID = ContentUris.parseId(uri);
			affected = db.delete(DatabaseHelper.PERSONNEL_TABLE_NAME,
					PersonnelRegister.PRegistration.REGISTRATION_ID
					+ "="
					+ perID
					+ (!TextUtils.isEmpty(where) ? " AND (" + where
							+ ')' : ""), whereArgs);
	// the call to notify the uri after deletion is explicit
			getContext().getContentResolver().notifyChange(uri, null);
		break;
		default:
			throw new IllegalArgumentException("unknown Personnel ID: "
				+ uri);
		}
	 return affected;	
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (sUriMatcher.match(uri)) {
		case ALL_PERSONNEL:
			return PersonnelRegister.PRegistration.CONTENT_TYPE;
		case SPECIFIC_PERSONNEL_ID:
			return PersonnelRegister.PRegistration.CONTENT_CATEGORY_TYPE;
		default:
			throw new IllegalArgumentException("Unknown personnel info type: "
					+ uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		// TODO Auto-generated method stub
		if (sUriMatcher.match(uri) != ALL_PERSONNEL) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		ContentValues values;
		if (initialValues != null) {
			values = new ContentValues(initialValues);
		} else {
			values = new ContentValues();
		}
		// insert the initialValues into a new database row
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long rowId = db.insert(DatabaseHelper.PERSONNEL_TABLE_NAME,
				PersonnelRegister.PRegistration.REGISTRATION_ID, values);
		if (rowId > 0) {
			Uri perURi = ContentUris.withAppendedId(
					PersonnelRegister.PRegistration.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(perURi, null);
			return perURi;
			
		}
		throw new SQLException("Failed to insert row into " + uri);
	}

	
	@Override
	public Cursor query(Uri uri, String[] projection, String where,
			String[] whereArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteDatabase mDb = dbHelper.getWritableDatabase();
		// If no sort order is specified use the default
		String orderBy;
		if (TextUtils.isEmpty(sortOrder)) {
			orderBy = PersonnelRegister.PRegistration.DEFAULT_SORT_ORDER;
		} else {
			orderBy = sortOrder;
		}

		int match = sUriMatcher.match(uri);
		Cursor c;

		switch (match) {
		case ALL_PERSONNEL:
			// query the database for all personnel
			c = mDb.query(DatabaseHelper.PERSONNEL_TABLE_NAME, projection,
					where, whereArgs, null, null, sortOrder);
			c.setNotificationUri(getContext().getContentResolver(),
					PersonnelRegister.PRegistration.CONTENT_URI);
			break;
		case SPECIFIC_PERSONNEL_ID:
			// query the database for a specific record
			long personnelID = ContentUris.parseId(uri);
			c = mDb.query(DatabaseHelper.PERSONNEL_TABLE_NAME, projection,
					PersonnelRegister.PRegistration.REGISTRATION_ID
							+ " = "
							+ personnelID
							+ (!TextUtils.isEmpty(where) ? " AND (" + where
									+ ')' : ""), whereArgs, null, null,
					sortOrder);
			c.setNotificationUri(getContext().getContentResolver(),
					PersonnelRegister.PRegistration.CONTENT_URI);
			break;
		default:
			throw new IllegalArgumentException("Unsupported uri: " + uri);
		}

		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String where,
			String[] whereArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int affected=0;
		switch (sUriMatcher.match(uri)) {
		case ALL_PERSONNEL:
			affected = db.update(DatabaseHelper.PERSONNEL_TABLE_NAME, values,
					where, whereArgs);
			break;
			
		case SPECIFIC_PERSONNEL_ID:
			String perId = uri.getPathSegments().get(1);
			affected = db.update(DatabaseHelper.PERSONNEL_TABLE_NAME, values,
					PersonnelRegister.PRegistration.REGISTRATION_ID
							+ "="
							+ perId
							+ (!TextUtils.isEmpty(where) ? " AND (" + where
									+ ')' : ""), whereArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		if(affected > 0)
			getContext().getContentResolver().notifyChange(uri, null);
		
		return affected;	
	}

	
}
