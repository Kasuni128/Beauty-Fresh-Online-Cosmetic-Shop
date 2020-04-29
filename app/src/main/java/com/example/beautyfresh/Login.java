package com.example.beautyfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beautyfresh.Database.DBHandler;

public class Login extends AppCompatActivity {

    EditText user, password;
    Button login,Adminlogin;




    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHandler = new DBHandler(getApplicationContext());

        user = findViewById(R.id.etuserNameLG);
        password = findViewById(R.id.etPasswordLG);

        login = findViewById(R.id.btnLoginLG);
        Adminlogin = findViewById(R.id.btnLoginAdmin);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this,Home.class);
                startActivity(intent);
            }
        });

        Adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,AdminLogin.class);
                startActivity(intent);
            }
        });



    }
}
