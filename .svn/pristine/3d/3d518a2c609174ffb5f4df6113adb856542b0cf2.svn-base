package com.setecs.android.mpos.provider;

import static com.setecs.android.mpos.provider.DatabaseHelper.CATEGORIES_TABLE_NAME;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class MenuCategoryProvider extends ContentProvider {

	private DatabaseHelper dbHelper;

	private static final UriMatcher sUriMatcher;
	private static final int ALL_CATEGORIES = 1;
	private static final int SPECIFIC_CATEGORY_ID = 2;

	private static HashMap<String, String> sMenuProjectionMap;

	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(MenuCategory.AUTHORITY,
				MenuCategory.Categories.CATEGORY, ALL_CATEGORIES);
		sUriMatcher.addURI(MenuCategory.AUTHORITY,
				MenuCategory.Categories.CATEGORY + "/#", SPECIFIC_CATEGORY_ID);

		sMenuProjectionMap = new HashMap<String, String>();
		sMenuProjectionMap.put(MenuCategory.Categories.CATEGORY_ID,
				MenuCategory.Categories.CATEGORY_ID);
		sMenuProjectionMap.put(MenuCategory.Categories.CATEGORY_NAME,
				MenuCategory.Categories.CATEGORY_NAME);
	}

	@Override
	public boolean onCreate() {
		dbHelper = new DatabaseHelper(getContext());
		return true;
	}

	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		int match = sUriMatcher.match(uri);
		int affected;

		switch (match) {
		case ALL_CATEGORIES:
			affected = db.delete(DatabaseHelper.CATEGORIES_TABLE_NAME,
					(!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""),
					whereArgs);
		break;
		case SPECIFIC_CATEGORY_ID:
			long catId = ContentUris.parseId(uri);
			affected = db.delete(DatabaseHelper.CATEGORIES_TABLE_NAME,
					MenuCategory.Categories.CATEGORY_ID
							+ "="
							+ catId
							+ (!TextUtils.isEmpty(where) ? " AND (" + where
									+ ')' : ""), whereArgs);
			// the call to notify the uri after deletion is explicit
			getContext().getContentResolver().notifyChange(uri, null);
			break;
		default:
			throw new IllegalArgumentException("unknown category element: "
					+ uri);
		}
		return affected;
	}

	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri)) {
		case ALL_CATEGORIES:
			return MenuCategory.Categories.CONTENT_TYPE;
		case SPECIFIC_CATEGORY_ID:
			return MenuCategory.Categories.CONTENT_CATEGORY_TYPE;
		default:
			throw new IllegalArgumentException("Unknown menu category type: "
					+ uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialValues) {

		// Validate the requested uri
		if (sUriMatcher.match(uri) != ALL_CATEGORIES) {
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		ContentValues values;
		if (initialValues != null) {
			values = new ContentValues(initialValues);
		} else {
			values = new ContentValues();
		}

		// verifyValues(values);

		// insert the initialValues into a new database row
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		long rowId = db.insert(DatabaseHelper.CATEGORIES_TABLE_NAME,
				MenuCategory.Categories.CATEGORY_NAME, values);
		if (rowId > 0) {
			Uri catURi = ContentUris.withAppendedId(
					MenuCategory.Categories.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(catURi, null);
			return catURi;
		}
		throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String where,
			String[] whereArgs, String sortOrder) {
		SQLiteDatabase mDb = dbHelper.getWritableDatabase();
		// If no sort order is specified use the default
		String orderBy;
		if (TextUtils.isEmpty(sortOrder)) {
			orderBy = MenuCategory.Categories.DEFAULT_SORT_ORDER;
		} else {
			orderBy = sortOrder;
		}

		int match = sUriMatcher.match(uri);
		Cursor c;

		switch (match) {
		case ALL_CATEGORIES:
			// query the database for all categories
			c = mDb.query(DatabaseHelper.CATEGORIES_TABLE_NAME, projection,
					where, whereArgs, null, null, sortOrder);
			c.setNotificationUri(getContext().getContentResolver(),
					MenuCategory.Categories.CONTENT_URI);
			break;
		case SPECIFIC_CATEGORY_ID:
			// query the database for a specific video
			long categoryID = ContentUris.parseId(uri);
			c = mDb.query(DatabaseHelper.CATEGORIES_TABLE_NAME, projection,
					MenuCategory.Categories.CATEGORY_ID
							+ " = "
							+ categoryID
							+ (!TextUtils.isEmpty(where) ? " AND (" + where
									+ ')' : ""), whereArgs, null, null,
					sortOrder);
			c.setNotificationUri(getContext().getContentResolver(),
					MenuCategory.Categories.CONTENT_URI);
			break;
		default:
			throw new IllegalArgumentException("Unsupported uri: " + uri);
		}

		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String where,
			String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int affected;
		switch (sUriMatcher.match(uri)) {
		case ALL_CATEGORIES:
			affected = db.update(DatabaseHelper.CATEGORIES_TABLE_NAME, values,
					where, whereArgs);
			break;
		case SPECIFIC_CATEGORY_ID:
			String catId = uri.getPathSegments().get(1);
			affected = db.update(CATEGORIES_TABLE_NAME, values,
					MenuCategory.Categories.CATEGORY_ID
							+ "="
							+ catId
							+ (!TextUtils.isEmpty(where) ? " AND (" + where
									+ ')' : ""), whereArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}

		getContext().getContentResolver().notifyChange(uri, null);
		return affected;
	}

}
