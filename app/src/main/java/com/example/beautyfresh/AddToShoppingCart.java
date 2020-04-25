package com.example.beautyfresh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyfresh.Database.Beauty;
import com.example.beautyfresh.Database.DBHandler;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddToShoppingCart extends AppCompatActivity {

    DBHandler myDB;
    TextView text1;
    private TextView mTextViewAmount,textprice,date,subtotal;
    private int mAmount = 0,total =0;
    Button buttonIncrease,buttonDescrease,buttonAdd,buttonview,btnshoppingcartdelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_shopping_cart);



        //subtotal=findViewById(R.id.textViewtotalamount);
        date = findViewById(R.id.textView8);
        text1 = findViewById(R.id.edittext_name);
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        text1.setText(name);


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE , dd-MMM-yy hh:mm:ss a");
        String date1 = simpleDateFormat.format(calendar.getTime());
        date.setText(date1);


        textprice = (TextView) findViewById(R.id.textView);
        mTextViewAmount = findViewById(R.id.textview_amount);

        buttonIncrease = findViewById(R.id.button_increase);
        buttonDescrease = findViewById(R.id.button_descrease);
        buttonAdd = findViewById(R.id.button_add);
        buttonview=findViewById(R.id.pd_add_to_cart_btm);
        myDB = new DBHandler(this);


        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase();
            }
        });

        buttonDescrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descrease();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        buttonview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddToShoppingCart.this, ViewShoppingCart.class);
                startActivity(intent);
            }
        });


    }



    private void add() {
        if ( mAmount == 0){
            return;
        }

        DBHandler dbHandler= new DBHandler(getApplicationContext());
        long newID = dbHandler.addshoppingcartinfo(text1.getText().toString(),mTextViewAmount.getText().toString(), textprice.getText().toString(),date.getText().toString());
        Toast.makeText(AddToShoppingCart.this, "added Shoppingcart."+newID, Toast.LENGTH_SHORT).show();


    }





    private void descrease() {
        if (mAmount > 0) {
            mAmount--;
            textprice.setText("Rs" +mAmount*1500);
            mTextViewAmount.setText(String.valueOf(mAmount));
        }
    }

    private void increase() {
        mAmount++;
        textprice.setText("Rs" +mAmount*1500);
        mTextViewAmount.setText(String.valueOf(mAmount));

    }



}

