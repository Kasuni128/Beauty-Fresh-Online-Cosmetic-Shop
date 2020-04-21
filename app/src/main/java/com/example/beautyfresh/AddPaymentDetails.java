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

import java.util.List;

public class AddPaymentDetails extends AppCompatActivity {

    EditText cardno,expriedate,cvv,holdername;
    Button proceed ;
    RadioButton master,visa;
    String method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_details);

        cardno= findViewById(R.id.editTextCardNo);
        expriedate= findViewById(R.id.EditTextValid);
        cvv= findViewById(R.id.editTextCVV);
        holdername= findViewById(R.id.editTextCardHolder);
        proceed= findViewById(R.id.btnProceed);
        master= findViewById(R.id.radioButtonMasterCard);
        visa= findViewById(R.id.radioButtonVisaCard);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(master.isChecked()){
                        method = "master";
                }
                else{
                    method = "visa";
                }

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                    long newID = dbHandler.addpaymentcardinfo(method,cardno.getText().toString(),expriedate.getText().toString(),cvv.getText().toString(),holdername.getText().toString());
                    Toast.makeText(AddPaymentDetails.this,"Payment Details Add payment ID:" + newID, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddPaymentDetails.this,SucessPayment.class);
                startActivity(intent);



            }
        });
    }
}
