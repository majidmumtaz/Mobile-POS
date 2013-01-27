package com.setecs.android.mpos.provider;


import com.setecs.android.mpos.R;
import com.android.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;

public class MPosDatabase extends SQLiteOpenHelper {

	/** The name of the database file on the file system */
	private static final String DATABASE_NAME = "MPos";
	/** The version of the database that this class understands. */
	private static final int DATABASE_VERSION = 1;
	/** Keep track of context so that we can load SQL from string resources */
	private final Context mContext;

	@Override
	public void onCreate(SQLiteDatabase db) {
		String[] sql = mContext.getString(R.string.MPosDatabase_onCreate).split("\n");
		
		db.beginTransaction();
		try {
			// Create tables & test data
			execMultipleSQL(db, sql);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("Error creating tables and debug data", e.toString());
		} finally {
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("MPOS_DB", "Upgrading database from version " + oldVersion
				+ " to " + newVersion + ", which will destroy all old data");

		String[] sql = mContext.getString(R.string.MPosDatabase_onUpgrade)
				.split("\n");
		db.beginTransaction();
		try {
			// Create tables & test data
			execMultipleSQL(db, sql);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("Error creating tables and debug data", e.toString());
		} finally {
			db.endTransaction();
		}

		// This is cheating. In the real world, you'll need to add columns,
		// not rebuild from scratch
		onCreate(db);
	}

	/** Constructor */
	public MPosDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.mContext = context;
	}

	/**
	 * Execute all of the SQL statements in the String[] array
	 * 
	 * @param db
	 *            The database on which to execute the statements
	 * @param sql
	 *            An array of SQL statements to execute
	 */
	private void execMultipleSQL(SQLiteDatabase db, String[] sql) {
		for (String s : sql) {
			if (s.trim().length() > 0) {
				db.execSQL(s);
			}
		}
	}

	/**
	 * Return a sorted JobsCursor
	 * 
	 * @param sortBy
	 *            the sort criteria
	 */
	public CategoriesCursor getCategories() {
		String sql = CategoriesCursor.QUERY;
		SQLiteDatabase d = getReadableDatabase();
		CategoriesCursor c = (CategoriesCursor) d.rawQueryWithFactory(
				new CategoriesCursor.Factory(), sql, null, null);
		c.moveToFirst();
		return c;
	}

	public void addCategory(String name) {
		ContentValues map = new ContentValues();
		map.put("name", name);
		try {
			getWritableDatabase().insert("categories", null, map);
		} catch (SQLException e) {
			Log.e("Error writing new category", e.toString());
		}
	}

	public void editCategory(long _id, String name) {
		ContentValues map = new ContentValues();
		map.put("name", name);
		String[] whereArgs = new String[] { Long.toString(_id) };
		try {
			getWritableDatabase().update("categories", map, "_id=?", whereArgs);
		} catch (SQLException e) {
			Log.e("Error writing new category", e.toString());
		}
	}

	public void deleteCategory(long _id) {
		String[] whereArgs = new String[] { Long.toString(_id) };
		try {
			getWritableDatabase().delete("categories", "_id=?", whereArgs);
		} catch (SQLException e) {
			Log.e("Error deleteing category", e.toString());
		}
	}

	//-------------------------------CategoriesCursor Class----------------------------------
	public static class CategoriesCursor extends SQLiteCursor {
		private static final String QUERY = "SELECT _id, name FROM categories ORDER BY name";

		private CategoriesCursor(SQLiteDatabase db, SQLiteCursorDriver driver,
				String editTable, SQLiteQuery query) {
			super(db, driver, editTable, query);
		}

		private static class Factory implements SQLiteDatabase.CursorFactory {
			@Override
			public Cursor newCursor(SQLiteDatabase db,
					SQLiteCursorDriver driver, String editTable,
					SQLiteQuery query) {
				return new CategoriesCursor(db, driver, editTable, query);
			}
		}

		public long getColCategoriesId() {
			return getLong(getColumnIndexOrThrow("_id"));
		}

		public String getColName() {
			return getString(getColumnIndexOrThrow("name"));
		}
	} //End CategoriesCursor Class
	//--------------------------------------------------------------------

}
