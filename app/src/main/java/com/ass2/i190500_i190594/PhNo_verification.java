package com.ass2.i190500_i190594;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class PhNo_verification extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText otp;
    TextView tb;
    Button verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph_no_verification);
        otp = findViewById(R.id.otp);
        tb = findViewById(R.id.tb);
        verify = findViewById(R.id.verify_phno);
        mAuth=FirebaseAuth.getInstance();

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = getIntent().getStringExtra("code");
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(code, otp.getText().toString());
                mAuth.signInWithCredential(credential)
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(PhNo_verification.this, "Failed", Toast.LENGTH_LONG).show();
                            }
                        })

                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(PhNo_verification.this, "Logged in", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}