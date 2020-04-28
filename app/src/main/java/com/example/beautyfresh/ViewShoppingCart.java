package com.example.beautyfresh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyfresh.Database.Beauty;
import com.example.beautyfresh.Database.DBHandler;

import java.util.ArrayList;

public class ViewShoppingCart extends AppCompatActivity {

    Button addNewbtn,refresh,nextprocessbtn;
    DBHandler myDB;
    TextView totalampount;

    int total = 0;


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



            theList.add(data.getString(1));
            theList.add(data.getString(2));
            theList.add(data.getString(3));

            final ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
            listView.setAdapter(listAdapter);
        }







        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int which_item = position;

                new AlertDialog.Builder(ViewShoppingCart.this)
                        .setTitle("Are You Sure ?")
                        .setMessage("Do you want to delete this item")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBHandler dbHandler = new DBHandler(getApplicationContext());
                                dbHandler.deleteshoppinginfo(theList.get(which_item));

                            }
                        })
                        .setNegativeButton("No",null)
                        .show();

                return true;
            }
        });

        addNewbtn = findViewById(R.id.add_new_in_shopping_cart_btn);
        addNewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewShoppingCart.this, Home.class);
                startActivity( intent );
            }
        });

        refresh = findViewById(R.id.refresh_btn);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewShoppingCart.this, ViewShoppingCart.class);
                startActivity( intent );
            }
        });

        nextprocessbtn = findViewById(R.id.next__btn);
        nextprocessbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


}
