package com.example.beautyfresh.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    //public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database.db";

    public DBHandler(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    private static final String SQL_CREATE_ENTRIES_REGISTER =
            "CREATE TABLE " + Beauty.Users.TABLE_NAME + " (" +
                    Beauty.Users._ID + " INTEGER PRIMARY KEY," +
                    Beauty.Users.COLUMN_1 + " TEXT," +
                    Beauty.Users.COLUMN_2 + " TEXT," +
                    Beauty.Users.COLUMN_3 + " TEXT," +
                    Beauty.Users.COLUMN_4 + " TEXT," +
                    Beauty.Users.COLUMN_5 + " TEXT," +
                    Beauty.Users.COLUMN_6+ " TEXT)";


    private static final String SQL_DELETE_ENTRIES_REGISTER =
            "DROP TABLE IF EXISTS " + Beauty.Users.TABLE_NAME;

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_PAYMENTDE);

        db.execSQL(SQL_CREATE_ENTRIES_REGISTER);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

        db.execSQL(SQL_DELETE_ENTRIES_REGISTER);
        onCreate(db);
    }


    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }



    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Beauty.ShippingDetails.TABLE_NAME + " (" +
                    Beauty.ShippingDetails._ID + " INTEGER PRIMARY KEY," +
                    Beauty.ShippingDetails.COLUMN_1 + " TEXT," +
                    Beauty.ShippingDetails.COLUMN_2 + " TEXT," +
                    Beauty.ShippingDetails.COLUMN_3 + " TEXT," +
                    Beauty.ShippingDetails.COLUMN_4 + " TEXT," +
                    Beauty.ShippingDetails.COLUMN_5+ " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Beauty.ShippingDetails.TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES_PAYMENTDE =
            "CREATE TABLE " + Beauty.PaymentDetails.TABLE_NAME + " (" +
                    Beauty.PaymentDetails._ID + " INTEGER PRIMARY KEY," +
                    Beauty.PaymentDetails.COLUMN_1 + " TEXT," +
                    Beauty.PaymentDetails.COLUMN_2 + " TEXT," +
                    Beauty.PaymentDetails.COLUMN_3 + " TEXT," +
                    Beauty.PaymentDetails.COLUMN_4 + " TEXT," +
                    Beauty.PaymentDetails.COLUMN_5+ " TEXT)";


    private static final String SQL_DELETE_ENTRIES_PAYMENTDE =
            "DROP TABLE IF EXISTS " + Beauty.PaymentDetails.TABLE_NAME;

    public long addshippinginfo(String fristname , String lastname , String address1 , String address2 ,String phoneno){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Beauty.ShippingDetails.COLUMN_1, fristname);
        values.put(Beauty.ShippingDetails.COLUMN_2, lastname);
        values.put(Beauty.ShippingDetails.COLUMN_3, address1);
        values.put(Beauty.ShippingDetails.COLUMN_4, address2);
        values.put(Beauty.ShippingDetails.COLUMN_5, phoneno);
// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Beauty.ShippingDetails.TABLE_NAME, null, values);

        return newRowId;
    }


    public boolean updateinfo(String fristname , String lastname , String address1 , String address2 , String phoneno){
        SQLiteDatabase db = getWritableDatabase();

// New value for one column

        ContentValues values = new ContentValues();
        values.put(Beauty.ShippingDetails.COLUMN_2, lastname);
        values.put(Beauty.ShippingDetails.COLUMN_3, address1);
        values.put(Beauty.ShippingDetails.COLUMN_4, address2);
        values.put(Beauty.ShippingDetails.COLUMN_5, phoneno);

// Which row to update, based on the title
        String selection = Beauty.ShippingDetails.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { fristname };

        int count = db.update(
                Beauty.ShippingDetails.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (count >= 1){
            return true;
        }
        else{
            return false;
        }
    }

    public void deleteshippinginfo(String firstname){

        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = Beauty.ShippingDetails.COLUMN_1 + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = { firstname };
// Issue SQL statement.
        int deletedRows = db.delete(Beauty.ShippingDetails.TABLE_NAME, selection, selectionArgs);
    }

    public List readAllinfo(String fristname){

        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                Beauty.ShippingDetails.COLUMN_1,
                Beauty.ShippingDetails.COLUMN_2,
                Beauty.ShippingDetails.COLUMN_3,
                Beauty.ShippingDetails.COLUMN_4,
                Beauty.ShippingDetails.COLUMN_5
        };

// Filter results WHERE "title" = 'My Title'
        String selection = Beauty.ShippingDetails.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { fristname };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                Beauty.ShippingDetails.COLUMN_1+ " ASC";

        Cursor cursor = db.query(
                Beauty.ShippingDetails.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List shippingInfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(Beauty.ShippingDetails.COLUMN_1));
            String lastname = cursor.getString(cursor.getColumnIndexOrThrow(Beauty.ShippingDetails.COLUMN_2));
            String address1 = cursor.getString(cursor.getColumnIndexOrThrow(Beauty.ShippingDetails.COLUMN_3));
            String address2 = cursor.getString(cursor.getColumnIndexOrThrow(Beauty.ShippingDetails.COLUMN_4));
            String phoneno = cursor.getString(cursor.getColumnIndexOrThrow(Beauty.ShippingDetails.COLUMN_5));


            shippingInfo.add(user);//0
            shippingInfo.add(lastname);//1
            shippingInfo.add(address1);//2
            shippingInfo.add(address2);//3
            shippingInfo.add(phoneno);//4

        }
        cursor.close();
        return shippingInfo;
    }



    public long addpaymentcardinfo(String method , String Cardno , String valid , String CVV ,String name){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Beauty.PaymentDetails.COLUMN_1, method);
        values.put(Beauty.PaymentDetails.COLUMN_2, Cardno);
        values.put(Beauty.PaymentDetails.COLUMN_3, valid);
        values.put(Beauty.PaymentDetails.COLUMN_4, CVV);
        values.put(Beauty.PaymentDetails.COLUMN_5, name);
// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Beauty.PaymentDetails.TABLE_NAME, null, values);

        return newRowId;
    }


    public  long  addInfo(String username , String address ,String email ,String phoneNumber ,String password, String gender){

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Beauty.Users.COLUMN_1, username);
        values.put(Beauty.Users.COLUMN_2, address);
        values.put(Beauty.Users.COLUMN_3, email);
        values.put(Beauty.Users.COLUMN_4, phoneNumber);
        values.put(Beauty.Users.COLUMN_5, password);
        values.put(Beauty.Users.COLUMN_6, gender);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Beauty.Users.TABLE_NAME, null, values);

        return newRowId;
    }

}





