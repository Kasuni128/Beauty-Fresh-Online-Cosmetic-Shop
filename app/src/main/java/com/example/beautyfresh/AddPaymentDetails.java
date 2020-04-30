package com.example.beautyfresh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.beautyfresh.Database.DBHandler;

import java.util.List;

public class AddPaymentDetails extends AppCompatActivity {
    private static final String CHANNEL_ID = "100CH";
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


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            String description = getString(R.string.app_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(master.isChecked()){
                        method = "master";
                }
                else{
                    method = "visa";
                }
                if(TextUtils.isEmpty(cardno.getText().toString())|| TextUtils.isEmpty(expriedate.getText().toString())||TextUtils.isEmpty(cvv.getText().toString())||
                        TextUtils.isEmpty(holdername.getText().toString())){
                    Toast.makeText(AddPaymentDetails.this, "fill the empty filed.", Toast.LENGTH_SHORT).show();
                }
                else {

                    DBHandler dbHandler = new DBHandler(getApplicationContext());
                    long newID = dbHandler.addpaymentcardinfo(method, cardno.getText().toString(), expriedate.getText().toString(),
                            cvv.getText().toString(), holdername.getText().toString());
                    Toast.makeText(AddPaymentDetails.this, "Payment Details Add payment ID:" + newID, Toast.LENGTH_SHORT).show();
                    sendNotification();
                    Intent intent = new Intent(AddPaymentDetails.this, SucessPayment.class);
                    startActivity(intent); }
            }
        });
    }

    private void sendNotification() {
        Intent intent = new Intent(AddPaymentDetails.this, SucessPayment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Beauty Fresh")
                .setContentText("Thank You for your purchase.\n Your order wil be delivered in 2-5 business days.\n Kepping Touch With Beauty Fresh")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent).setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0,builder.build());
    }
    }



