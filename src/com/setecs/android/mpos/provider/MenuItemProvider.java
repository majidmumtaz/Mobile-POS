package com.setecs.android.mpos.provider;

import static com.setecs.android.mpos.provider.DatabaseHelper.MENU_TABLE_NAME;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.setecs.android.mpos.objects.MenuItem;

public class MenuItemProvider extends ContentProvider {

	public static final String AUTHORITY = "com.setecs.android.mpos.MenuItemProvider";//com.setecs.android.mpos.menuprovider";
	private DatabaseHelper dbHelper;

	/**
	 * A UriMatcher instance
	 */
	private static final UriMatcher sUriMatcher;

	/**
	 * The incoming URI matches the Menu URI pattern.
	 */
	private static final int MENU = 1;

	/**
	 * The incoming URI matches the Menu ID URI pattern
	 */
	private static final int MENU_ID = 2;

	/**
	 * A projection map used to select columns from the database.
	 */
	private static HashMap<String, String> sMenuProjectionMap;

	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(AUTHORITY, "menu", MENU);
		sUriMatcher.addURI(AUTHORITY, "menu/#", MENU_ID);

		sMenuProjectionMap = new HashMap<String, String>();
		sMenuProjectionMap.put(MenuItem.MenuColumns.MENU_ITEM_ID,
				MenuItem.MenuColumns.MENU_ITEM_ID);		
		sMenuProjectionMap.put(MenuItem.MenuColumns.MENU_ITEM_NAME,
				MenuItem.MenuColumns.MENU_ITEM_NAME);		
		sMenuProjectionMap.put(MenuItem.MenuColumns.MENU_ITEM_DESCRIPTION,
				MenuItem.MenuColumns.MENU_ITEM_DESCRIPTION);
		sMenuProjectionMap.put(MenuItem.MenuColumns.MENU_ITEM_PRICE,
				MenuItem.MenuColumns.MENU_ITEM_PRICE);
		sMenuProjectionMap.put(MenuItem.MenuColumns.MENU_ITEM_CATEGORY_ID,
				MenuItem.MenuColumns.MENU_ITEM_CATEGORY_ID);
	}

	@Override
	public boolean onCreate() {
		dbHelper = new DatabaseHelper(getContext());
		return true;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String finalSelection;

		int match = sUriMatcher.match(uri);
		int affected;

		switch (match) {
		case MENU:
			affected = db.delete(MENU_TABLE_NAME, selection, selectionArgs);
			break;
		case MENU_ID:
			// Starts a final WHERE clause by restricting it to the
			// desired journey ID.
			finalSelection = MenuItem.MenuColumns.MENU_ITEM_ID + " = "
					+ uri.getPathSegments().get(MenuItem.MENU_ID_PATH_POSITION);

			// If there were additional selection criteria, append them to
			// the final WHERE clause.
			if (selection != null) {
				finalSelection = finalSelection + " AND " + selection;
			}

			affected = db.delete(MENU_TABLE_NAME, finalSelection, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		getContext().getContentResolver().notifyChange(uri, null);

		return affected;
	}

	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri)) {
		case MENU:
			return MenuItem.MenuColumns.CONTENT_TYPE;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {
		if (sUriMatcher.match(uri) != MENU) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		ContentValues values;
		if (initialValues != null) {
			values = new ContentValues(initialValues);
		} else {
			values = new ContentValues();
		}

		SQLiteDatabase db = dbHelper.getWritableDatabase();

		long rowId = db.insert(MENU_TABLE_NAME, null, values);

		if (rowId > 0) {
			Uri menuItemUri = ContentUris.withAppendedId(
					MenuItem.MenuColumns.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(menuItemUri, null);
			return menuItemUri;
		}

		throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(MENU_TABLE_NAME);

		switch (sUriMatcher.match(uri)) {
		case MENU:
			qb.setProjectionMap(sMenuProjectionMap);
			break;
		case MENU_ID:
			qb.setProjectionMap(sMenuProjectionMap);
			qb.appendWhere(MenuItem.MenuColumns.MENU_ITEM_ID + "="
					+ uri.getPathSegments().get(MenuItem.MENU_ID_PATH_POSITION));
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor c = qb.query(db, projection, selection, selectionArgs, null,
				null, sortOrder);

		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		String finalSelection;

		int count;

		switch (sUriMatcher.match(uri)) {
		case MENU:
			count = db
					.update(MENU_TABLE_NAME, values, selection, selectionArgs);
			break;
		case MENU_ID:
			// Starts creating the final WHERE clause by restricting it to
			// the incoming ID.
			finalSelection = MenuItem.MenuColumns.MENU_ITEM_ID + " = "
					+ uri.getPathSegments().get(MenuItem.MENU_ID_PATH_POSITION);

			// If there were additional selection criteria, append them to
			// the final WHERE clause.
			if (selection != null) {
				finalSelection = finalSelection + " AND " + selection;
			}

			count = db.update(MENU_TABLE_NAME, values, finalSelection,
					selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

}
