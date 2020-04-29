package com.example.beautyfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.beautyfresh.Database.DBHandler;

public class EditProfile extends AppCompatActivity {

    EditText username,address,phone ,email,passsword;
    Button btnupdate,btndelete,btnhome;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btnhome = findViewById(R.id.btnHomeEP);
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this, Home.class);
                startActivity(intent);
            }
        });


        username =findViewById(R.id.editxtusernameEP);
        address =findViewById(R.id.editxtaddressEP);
        phone =findViewById(R.id.editxtphoneEP);
        email =findViewById(R.id.editxtemailEP);
        passsword =findViewById(R.id.editxtpasswordEP);
        btnupdate =findViewById(R.id.btnupdateEP);
        //search =findViewById(R.id.btnsearch);
        btndelete =findViewById(R.id.btnDeleteEP);
        btnhome = findViewById(R.id.btnHomeEP);
        male =findViewById(R.id.radioButton3);
        female =findViewById(R.id.radioButton4);

        Intent intent = getIntent();
        String name = intent.getStringExtra(" Name");
        String addr = intent.getStringExtra("Address");
        String contact = intent.getStringExtra("Contact");
        String mail = intent.getStringExtra("Email");
        String pwd = intent.getStringExtra("Password");

        username.setText(name);
        address.setText(addr);
        phone.setText(contact);
        email.setText(mail);
        passsword.setText(pwd);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler= new DBHandler(getApplicationContext());



                Boolean status = dbHandler.updateuserinfo(username.getText().toString(), address.getText().toString() , phone.getText().toString(),
                        email.getText().toString(), passsword.getText().toString(),gender );
                if (status){
                    Toast.makeText(EditProfile.this, "Edit Details Updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(EditProfile.this, "Edit Details Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler= new DBHandler(getApplicationContext());
                dbHandler.deleteshippinginfo(username.getText().toString());

                Toast.makeText(EditProfile.this, " Details Deleted", Toast.LENGTH_SHORT).show();

                username.setText(null);
                address.setText(null);
                phone.setText(null);
                email.setText(null);
                passsword.setText(null);


            }
        });

    }
}
