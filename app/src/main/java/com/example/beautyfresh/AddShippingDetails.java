package com.example.beautyfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beautyfresh.Database.DBHandler;

import java.util.List;

public class AddShippingDetails extends AppCompatActivity {
    EditText firstname, lastname , address1 , address2 , phoneno ;
    Button btnshippingAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shipping_details);


        firstname = findViewById(R.id.editTextShippingfnameud);
        lastname = findViewById(R.id.editTextShiiinglnameud);
        address1 = findViewById(R.id.editTextShiiingaddress1ud);
        address2 = findViewById(R.id.editTextShiiingaddress2ud);
        phoneno = findViewById(R.id.editTextShippingphoneud);
        btnshippingAdd = findViewById(R.id.ButtonShippingAdd);

        if (firstname.length() == 0) {
            firstname.setError("Enter the first name");
        } else if (lastname.length() == 0) {
            lastname.setError("Enter the last name");
        } else if (address1.length() == 0) {
            address1.setError("Enter the Address line 1");
        } else if (address2.length() == 0) {
            address2.setError("Enter the Address line 2");
        } else if (phoneno.length() == 0) {
            phoneno.setError("Enter the phone no");
        }

        btnshippingAdd.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(firstname.getText().toString())){
                    Toast.makeText(AddShippingDetails.this, "fill the empty filed.", Toast.LENGTH_SHORT).show();
                }
                else {
                    DBHandler dbHandler = new DBHandler(getApplicationContext());
                    long newID = dbHandler.addshippinginfo(firstname.getText().toString(), lastname.getText().toString(), address1.getText().toString(), address2.getText().toString(), phoneno.getText().toString());
                    Toast.makeText(AddShippingDetails.this, "Added shipping Details." + newID, Toast.LENGTH_SHORT).show();


                    List user = dbHandler.readAllinfo(firstname.getText().toString());
                    String fname = user.get(0).toString();
                    String lname = user.get(1).toString();
                    String address1 = user.get(2).toString();
                    String address2 = user.get(3).toString();
                    String phone = user.get(4).toString();


                    Intent intent = new Intent(AddShippingDetails.this, UpdateDeleteShipping.class);
                    intent.putExtra("First Name", fname);
                    intent.putExtra("Last Name", lname);
                    intent.putExtra("Address Line 1", address1);
                    intent.putExtra("Address Line 2", address2);
                    intent.putExtra("Phone No", phone);


                    startActivity(intent);
                }
            }
        });

    }
}
