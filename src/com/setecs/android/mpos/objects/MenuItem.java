package com.setecs.android.mpos.objects;

import android.net.Uri;
import android.provider.BaseColumns;

public class MenuItem {

	public static final int MENU_ID_PATH_POSITION = 1;
	public static final String DEFAULT_SORT_ORDER = "modified DESC";
	public static final String AUTHORITY = "com.setecs.android.mpos.MenuItemProvider";

	public static final class MenuColumns implements BaseColumns {

		// This class cannot be instantiated
		private MenuColumns() {
		}

		// uri references all categories
		public static final Uri MENU_ITEM_URI = Uri.parse("content://"
				+ AUTHORITY + "/" + MenuColumns.MENU);

		public static final String MENU = "menu";
		public static final Uri CONTENT_URI = MENU_ITEM_URI;
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.setecs.mpos.menu.item";
		public static final String CONTENT_MENU_ITEM_TYPE = "vnd.android.cursor.item/vnd.setecs.mpos.menu.item";
		public static final String MENU_ITEM_ID = "_id";	
		public static final String MENU_ITEM_NAME = "menu_item_name";
		public static final String MENU_ITEM_PRICE = "menu_item_price";
		public static final String MENU_ITEM_DESCRIPTION = "menu_item_description";
		public static final String MENU_ITEM_CATEGORY_ID = "menu_id_cat";

	}

}
