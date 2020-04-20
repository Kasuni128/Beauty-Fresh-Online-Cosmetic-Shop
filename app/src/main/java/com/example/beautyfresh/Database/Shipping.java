package com.example.beautyfresh.Database;

import android.provider.BaseColumns;

public class Shipping {
    private Shipping() {}

    /* Inner class that defines the table contents */
    public static class ShippingDetails implements BaseColumns {
        public static final String TABLE_NAME = "shippinginfo";
        public static final String COLUMN_1= "shfristname";
        public static final String COLUMN_2 = "shlastname";
        public static final String COLUMN_3 = "shaddress1";
        public static final String COLUMN_4 = "shaddress2";
        public static final String COLUMN_5 = "shphoneno";

    }
}
