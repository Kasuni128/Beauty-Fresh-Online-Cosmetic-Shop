package com.example.beautyfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserAdmin extends AppCompatActivity {
    Button btnadmin,btnuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_admin);

        btnadmin=findViewById(R.id.main_login_admin);
        btnuser=findViewById(R.id.main_login_user);

        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserAdmin.this,AdminLogin.class);
                startActivity(i);
            }
        });

        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(UserAdmin.this,Login.class);
                startActivity(i);
            }
        });
    }
}
