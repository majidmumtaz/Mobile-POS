package com.setecs.android.mpos.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class MenuCategory {

	public static final int TITLE_COLUMN = 1;

	public static final String AUTHORITY = "com.setecs.android.mpos.MenuCategoryProvider";

	public static final class Categories implements BaseColumns {

		public static final String DEFAULT_SORT_ORDER = "modified DESC";

		// This class cannot be instantiated
		private Categories() {
		}

		// uri references all categories
		public static final Uri CATEGORIES_URI = Uri.parse("content://"
				+ AUTHORITY + "/" + Categories.CATEGORY);

		public static final String CATEGORY = "category";

		public static final Uri CONTENT_URI = CATEGORIES_URI;

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.mpos.menu.category";

		public static final String CONTENT_CATEGORY_TYPE = "vnd.android.cursor.item/vnd.mpos.menu.category";

		public static final String CATEGORY_ID = "_id";

		public static final String CATEGORY_NAME = "name";

	}

}
