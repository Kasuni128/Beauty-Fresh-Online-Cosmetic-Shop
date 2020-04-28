package com.example.beautyfresh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.beautyfresh.Database.Beauty;
import com.example.beautyfresh.Database.DBHandler;

import java.util.ArrayList;

public class ViewShoppingCart extends AppCompatActivity {


    DBHandler myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shopping_cart);

        final ListView listView = findViewById(R.id.listView);
        //final Button btn = findViewById(R.id.button);
        myDB = new DBHandler(this);

        final ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();

        while (data.moveToNext()) {

            theList.add(data.getString(3));
            theList.add(data.getString(2));
            theList.add(data.getString(1));

            final ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, theList);
            listView.setAdapter(listAdapter);

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    SparseBooleanArray positionchecker = listView.getCheckedItemPositions();

                    int count = listView.getCount();

                    for (int item = count - 1; item >= 0; item--) {
                        if (positionchecker.get(item)) {
                            DBHandler dbHandler = new DBHandler(getApplicationContext());
                            dbHandler.deleteshoppinginfo(theList.get(item));
                            Toast.makeText(ViewShoppingCart.this, "Item delete successful", Toast.LENGTH_SHORT).show();
                        }

                    }

                    positionchecker.clear();
                    ((ArrayAdapter) listAdapter).notifyDataSetChanged();

                    return false;
                }
            });
        }

        //btn.setOnClickListener(new View.OnClickListener() {
        //@Override
        // public void onClick(View v) {
        // Remove / Delete first item from List
        //theList.remove(0);
                /*
                    notifyDataSetChanged ()
                        Notifies the attached observers that the underlying
                        data has been changed and any View reflecting the
                        data set should refresh itself.
                 */
        //listAdapter.notifyDataSetChanged();
        //}
        // });




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ViewShoppingCart.this, "Click Item", Toast.LENGTH_SHORT).show();
            }
        });


       /* listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int which_item = position;

                new AlertDialog.Builder(ViewShoppingCart.this)
                        .setTitle("Are You Sure ?")
                        .setMessage("Do you want to delete this item")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                 theList.remove(which_item);

                            }
                        })
                        .setNegativeButton("No",null)
                        .show();

                return true;
            }
        });*/


    }
}
