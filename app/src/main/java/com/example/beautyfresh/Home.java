package com.example.beautyfresh;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btn1,btn2,btn3,btn4,btnadmin,button5;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        btn1 = findViewById(R.id.btnpearl);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "Pearl Beauty Night Face Cream";
                Intent intent = new Intent(Home.this, AddToShoppingCart.class);
                intent.putExtra("Name", name);
                startActivity(intent);
            }
        });

        btn2 = findViewById(R.id.btncollgen);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "Collgen Body Lotion";
                Intent intent = new Intent(Home.this, AddToShoppingCart.class);
                intent.putExtra("Name", name);
                startActivity(intent);
            }
        });

        btn3 = findViewById(R.id.btnday);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "Gold Beauty Day Cream";
                Intent intent = new Intent(Home.this, AddToShoppingCart.class);
                intent.putExtra("Name", name);
                startActivity(intent);
            }
        });

        btn4 = findViewById(R.id.btnhairoil);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "Gold Beauty Hair oil";
                Intent intent = new Intent(Home.this, AddToShoppingCart.class);
                intent.putExtra("Name", name);
                startActivity(intent);
            }
        });

        txt = findViewById(R.id.textViewedit);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String username = user.getText().toString();

                //String addr = null;
                //String mail = null;
                // contact = null;
                // pwd = null;
                //String gender = null;

                Intent intent = new Intent(Home.this,EditProfile.class);
                //intent.putExtra("UserName", username);
                //intent.putExtra("Address", addr);
                //intent.putExtra("Email", mail);
                //intent.putExtra("Contact No", contact);
                //intent.putExtra("Password", pwd);
                //intent.putExtra("Gender", gender);
                startActivity(intent);







            }
        });






        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
