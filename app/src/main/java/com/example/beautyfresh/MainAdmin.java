package com.example.beautyfresh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beautyfresh.Database.DBHandler;

public class MainAdmin extends AppCompatActivity {

    Button _btnAddData, _btnView, _btnUpdate, _btnDelete;
    EditText _txtID, _txtProductName,_txtPType,  _txtProductDescription, _txtPrice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_admin );
        //myDb = new DBHandler( MainAdmin.this );

        _btnAddData = (Button) findViewById( R.id.btnAddData );
        _btnView = (Button) findViewById( R.id.btnView );
        _btnUpdate = (Button) findViewById( R.id.btnUpdate );
        _btnDelete = (Button) findViewById( R.id.btnDelete );
        // _txtID = (EditText) findViewById( R.id.txtID );
        _txtProductName = (EditText) findViewById( R.id.txtProductName );
        _txtPType = (EditText) findViewById( R.id.txtPType );
        _txtProductDescription = (EditText) findViewById( R.id.txtProductDescription );
        _txtPrice = (EditText) findViewById( R.id.txtPrice );


        _btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.insertData(_txtProductName.getText().toString(), _txtPType.getText().toString(), _txtProductDescription.getText().toString(), _txtPrice.getText().toString());
                Toast.makeText(MainAdmin.this, "Product Details Add " , Toast.LENGTH_SHORT).show();

            }
        });


        _btnUpdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                Boolean status = dbHandler.updateData( _txtProductName.getText().toString(), _txtPType.getText().toString(),
                        _txtProductDescription.getText().toString(), _txtPrice.getText().toString());
                if (status) {
                    Toast.makeText(MainAdmin.this, "Products Details Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainAdmin.this, "Product Details Failed", Toast.LENGTH_SHORT).show();
                }





            }
        } );


        _btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler= new DBHandler(getApplicationContext());
                dbHandler.deleteData(_txtProductName.getText().toString());

                Toast.makeText(MainAdmin.this, "Product details Deleted", Toast.LENGTH_SHORT).show();

                _txtProductName.setText(null);
                _txtPType.setText(null);
                _txtProductDescription.setText(null);
                _txtPrice.setText(null);


            }
        });

        _btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler= new DBHandler(getApplicationContext());
                Cursor res = dbHandler.getAllData();
                if(res.getCount()==0){
                    showMessage("Error","Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer(  );
                while (res.moveToNext()){
                    buffer.append( "id:"+res.getString( 0 )+"\n" );
                    buffer.append( "PName:"+res.getString( 1 )+"\n" );
                    buffer.append( "PType:"+res.getString( 2 )+"\n" );
                    buffer.append( "PDescription:"+res.getString( 3 )+"\n" );
                    buffer.append( "Price:"+res.getString( 4 )+"\n"+"\n" );





                }
                showMessage("Data",buffer.toString());


            }
        });
    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(  this);
        builder.setCancelable( true );
        builder.setTitle(  title);
        builder.setMessage( Message );
        builder.show();

    }


}









