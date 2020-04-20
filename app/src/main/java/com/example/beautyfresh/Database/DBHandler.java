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
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Shipping.ShippingDetails.TABLE_NAME + " (" +
                    Shipping.ShippingDetails._ID + " INTEGER PRIMARY KEY," +
                    Shipping.ShippingDetails.COLUMN_1 + " TEXT," +
                    Shipping.ShippingDetails.COLUMN_2 + " TEXT," +
                    Shipping.ShippingDetails.COLUMN_3 + " TEXT," +
                    Shipping.ShippingDetails.COLUMN_4 + " TEXT," +
                    Shipping.ShippingDetails.COLUMN_5+ " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Shipping.ShippingDetails.TABLE_NAME;

    public long addshippinginfo(String fristname , String lastname , String address1 , String address2 ,String phoneno){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Shipping.ShippingDetails.COLUMN_1, fristname);
        values.put(Shipping.ShippingDetails.COLUMN_2, lastname);
        values.put(Shipping.ShippingDetails.COLUMN_3, address1);
        values.put(Shipping.ShippingDetails.COLUMN_4, address2);
        values.put(Shipping.ShippingDetails.COLUMN_5, phoneno);
// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Shipping.ShippingDetails.TABLE_NAME, null, values);

        return newRowId;
    }


    public boolean updateinfo(String fristname , String lastname , String address1 , String address2 , String phoneno){
        SQLiteDatabase db = getWritableDatabase();

// New value for one column

        ContentValues values = new ContentValues();
        values.put(Shipping.ShippingDetails.COLUMN_2, lastname);
        values.put(Shipping.ShippingDetails.COLUMN_3, address1);
        values.put(Shipping.ShippingDetails.COLUMN_4, address2);
        values.put(Shipping.ShippingDetails.COLUMN_5, phoneno);

// Which row to update, based on the title
        String selection = Shipping.ShippingDetails.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { fristname };

        int count = db.update(
                Shipping.ShippingDetails.TABLE_NAME,
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
        String selection = Shipping.ShippingDetails.COLUMN_1 + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = { firstname };
// Issue SQL statement.
        int deletedRows = db.delete(Shipping.ShippingDetails.TABLE_NAME, selection, selectionArgs);
    }

    public List readAllinfo(String fristname){

        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                Shipping.ShippingDetails.COLUMN_1,
                Shipping.ShippingDetails.COLUMN_2,
                Shipping.ShippingDetails.COLUMN_3,
                Shipping.ShippingDetails.COLUMN_4,
                Shipping.ShippingDetails.COLUMN_5
        };

// Filter results WHERE "title" = 'My Title'
        String selection = Shipping.ShippingDetails.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { fristname };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                Shipping.ShippingDetails.COLUMN_1+ " ASC";

        Cursor cursor = db.query(
                Shipping.ShippingDetails.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List shippingInfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(Shipping.ShippingDetails.COLUMN_1));
            String lastname = cursor.getString(cursor.getColumnIndexOrThrow(Shipping.ShippingDetails.COLUMN_2));
            String address1 = cursor.getString(cursor.getColumnIndexOrThrow(Shipping.ShippingDetails.COLUMN_3));
            String address2 = cursor.getString(cursor.getColumnIndexOrThrow(Shipping.ShippingDetails.COLUMN_4));
            String phoneno = cursor.getString(cursor.getColumnIndexOrThrow(Shipping.ShippingDetails.COLUMN_5));


            shippingInfo.add(user);//0
            shippingInfo.add(lastname);//1
            shippingInfo.add(address1);//2
            shippingInfo.add(address2);//3
            shippingInfo.add(phoneno);//4

        }
        cursor.close();
        return shippingInfo;
    }



}



