package com.ass2.i190500_i190594;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class signin_withaccount extends AppCompatActivity {

    TextView sign_in_bt;
    Button signup_phno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_withaccount);

        sign_in_bt = findViewById(R.id.sign_in_Bt);
        signup_phno = findViewById(R.id.sign_in_ph_nm);
        // sign in page will open
        sign_in_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signin_withaccount.this, sign_up.class));
            }
        });
        // A page will open for sign up using phone number
        signup_phno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signin_withaccount.this, take_ph_no.class));
            }
        });

    }
}