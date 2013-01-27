package com.setecs.android.mpos.objects;

import android.net.Uri;
import android.provider.BaseColumns;

public class Order {

	private int orderTotal;
	private int orderID;
	// list of items
	// add menu item
	// delete menu item
	// display order
	// save order

	public static final int ORDER_ID_PATH_POSITION = 1;
	public static final String DEFAULT_SORT_ORDER = "modified DESC";
	public static final String AUTHORITY = "com.setecs.android.mpos.OrderProvider";

	public static final class OrderColumns implements BaseColumns {

		// This class cannot be instantiated
		private OrderColumns() {
		}

		// uri references all categories
		public static final Uri ORDER_URI = Uri.parse("content://" + AUTHORITY
				+ "/" + OrderColumns.ORDER);

		public static final String ORDER = "order";
		public static final Uri CONTENT_URI = ORDER_URI;

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.setecs.mpos.order";
		public static final String CONTENT_MENU_ITEM_TYPE = "vnd.android.cursor.item/vnd.setecs.mpos.menu.order";

		public static final String ORDER_ID = "_id";
		public static final String ORDER_ITEMS_ID = "order_items_id";

	}

}
