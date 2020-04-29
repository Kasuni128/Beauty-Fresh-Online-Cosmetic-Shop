package com.example.beautyfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyfresh.Database.DBHandler;

import java.util.List;

public class Register extends AppCompatActivity {

    EditText username,address,email,phoneNumber,password;
    Button register;
    RadioButton male,female;
    String gender;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHandler = new DBHandler(getApplicationContext());

        username =findViewById(R.id.etusernameRE);
        address =findViewById(R.id.txtaddressRE);
        email =findViewById(R.id.txtemailRE);
        phoneNumber =findViewById(R.id.txtphoneRE);
        password =findViewById(R.id.txtpassRE);
        male =findViewById(R.id.radioButton3);
        female =findViewById(R.id.radioButton4);
        register =findViewById(R.id.btnRegisterRE);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(username.getText().toString())|| TextUtils.isEmpty(address.getText().toString())||TextUtils.isEmpty(email.getText().toString())|| TextUtils.isEmpty(phoneNumber.getText().toString())|| TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(Register.this, "fill the empty filed.", Toast.LENGTH_SHORT).show();

                }
                else{DBHandler dbHandler = new DBHandler(getApplicationContext());
                    long newID = dbHandler.addInfo(username.getText().toString(), address.getText().toString(), email.getText().toString(), phoneNumber.getText().toString(), password.getText().toString(),gender);
                    Toast.makeText(Register.this, "Added shipping Details." + newID, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this,Home.class);
                    startActivity(intent);

                }

                //List user = dbHandler.readAllinfo(username.getText().toString());
                //String fname = user.get(0).toString();
                //String  Add= user.get(1).toString();
                //String mail = user.get(2).toString();
                //String contact = user.get(3).toString();
                //String pwd = user.get(4).toString();

                //Intent intent = new Intent(Register.this, EditProfile.class);
                //intent.putExtra("First Name", fname);
                //intent.putExtra("Last Name", Add);
                //intent.putExtra("Address Line 1", mail);
                //intent.putExtra("Address Line 2", contact);
                //intent.putExtra("Phone No", pwd
                //);


                /*long newID = dbHandler.addInfo(fname, Add, mail, contact, pwd, gender);

                if (newID > 0){
                    Toast.makeText(Register.this, "User Added. User ID: "+newID, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Register.this,MainActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(Register.this, "User Not Added.", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

    }
}
