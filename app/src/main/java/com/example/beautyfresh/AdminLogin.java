package com.example.beautyfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    EditText ed1,ed2;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        b1 = (Button)findViewById(R.id.button1);
        ed1 = (EditText)findViewById(R.id.editText2);
        ed2 = (EditText)findViewById(R.id.editText1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed2.getText().toString().equals("admin") &&
                        ed1.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Sucess login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminLogin.this, MainAdmin.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong login",Toast.LENGTH_SHORT).show();
            }
            }
        });


    }
}
