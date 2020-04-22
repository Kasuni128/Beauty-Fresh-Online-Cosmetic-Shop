package com.example.beautyfresh.Database;

import android.provider.BaseColumns;

public class Beauty {
    private Beauty() {}

    /* Inner class that defines the table contents */
        public static class ShippingDetails implements BaseColumns {
        public static final String TABLE_NAME = "shippinginfo";
        public static final String COLUMN_1 = "shfristname";
        public static final String COLUMN_2 = "shlastname";
        public static final String COLUMN_3 = "shaddress1";
        public static final String COLUMN_4 = "shaddress2";
        public static final String COLUMN_5 = "shphoneno";
    }
            public static class PaymentDetails implements BaseColumns {
            public static final String TABLE_NAME = "paymentdetailsinfo";
            public static final String COLUMN_1= "paymentmethod";
            public static final String COLUMN_2 = "cardno";
            public static final String COLUMN_3 = "expire";
            public static final String COLUMN_4 = "CVV";
            public static final String COLUMN_5 = "holdername";

        }


    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "UserInfo";
        public static final String COLUMN_1 = "userName";
        public static final String COLUMN_2 = "address";
        public static final String COLUMN_3 = "email";
        public static final String COLUMN_4 = "phoneNumber";
        public static final String COLUMN_5 = "password";
        public static final String COLUMN_6 = "gender";

    }

    }

