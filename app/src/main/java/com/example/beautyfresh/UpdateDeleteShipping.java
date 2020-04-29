package com.example.beautyfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyfresh.Database.DBHandler;

import java.util.List;

public class UpdateDeleteShipping extends AppCompatActivity {

    EditText firstname, lastname , address1 , address2 , phoneno ;
    Button btnshippingUpdate,btnshippingdelete , btnshippingplaceorder , btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_shipping);

        btn2 = findViewById(R.id.buttonshippingplaceOrder);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(firstname.getText().toString())|| TextUtils.isEmpty(lastname.getText().toString())||TextUtils.isEmpty(address1.getText().toString())|| TextUtils.isEmpty(phoneno.getText().toString())){
                    Toast.makeText(UpdateDeleteShipping.this, "fill the empty filed.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(UpdateDeleteShipping.this, AddPaymentDetails.class);
                    startActivity(intent);
                }
            }
        });

        firstname= findViewById(R.id.editTextshippingfname);
        lastname= findViewById(R.id.editTextshippinglname);
        address1= findViewById(R.id.editTextshppingaddressline1);
        address2= findViewById(R.id.editTextshppingaddressline2);
        phoneno= findViewById(R.id.editTextShippingphone);
        btnshippingUpdate= findViewById(R.id.buttonshippingUpdate);
        btnshippingdelete= findViewById(R.id.buttonshippingDelete);


        btnshippingplaceorder= findViewById(R.id.buttonshippingplaceOrder);

        Intent intent = getIntent();
        String fname = intent.getStringExtra("First Name");
        String lname = intent.getStringExtra("Last Name");
        String addressline1 = intent.getStringExtra("Address Line 1");
        String addressline2 = intent.getStringExtra("Address Line 2");
        String phone = intent.getStringExtra("Phone No");


        firstname.setText(fname);
        lastname.setText(lname);
        address1.setText(addressline1);
        address2.setText(addressline2);
        phoneno.setText(phone);




        btnshippingUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(firstname.getText().toString()) || TextUtils.isEmpty(lastname.getText().toString()) || TextUtils.isEmpty(address1.getText().toString()) || TextUtils.isEmpty(phoneno.getText().toString())) {
                    Toast.makeText(UpdateDeleteShipping.this, "fill the empty filed.", Toast.LENGTH_SHORT).show();
                } else {
                    DBHandler dbHandler = new DBHandler(getApplicationContext());


                    Boolean status = dbHandler.updateinfo(firstname.getText().toString(), lastname.getText().toString(), address1.getText().toString(),
                            address2.getText().toString(), phoneno.getText().toString());
                    if (status) {
                        Toast.makeText(UpdateDeleteShipping.this, "Shipping Details Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateDeleteShipping.this, "Shipping Details Update Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnshippingdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler= new DBHandler(getApplicationContext());
                dbHandler.deleteshippinginfo(firstname.getText().toString());

                Toast.makeText(UpdateDeleteShipping.this, "Shipping details Deleted", Toast.LENGTH_SHORT).show();

                firstname.setText(null);
                lastname.setText(null);
                address1.setText(null);
                address2.setText(null);
                phoneno.setText(null);


            }
        });
    }
}