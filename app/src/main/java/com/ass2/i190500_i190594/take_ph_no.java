package com.ass2.i190500_i190594;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class take_ph_no extends AppCompatActivity {
    EditText Ph_no,Ph_no_password, OTP;
    Button submit, verify;
    FirebaseAuth mAuth;
    String verification_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_ph_no);
        Ph_no = findViewById(R.id.PhNo_signup);
        Ph_no_password = findViewById(R.id.PhNo_password);
        submit = findViewById(R.id.submit_phno);
        OTP = findViewById(R.id.otp);
        verify = findViewById(R.id.verify_phno);
        mAuth=FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(Ph_no.getText().toString()) || TextUtils.isEmpty(Ph_no_password.getText().toString()))
                    Toast.makeText(take_ph_no.this, "Enter Phone Number and Password", Toast.LENGTH_SHORT).show();
                else {
                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                            .setPhoneNumber(Ph_no.getText().toString())
                            .setActivity(take_ph_no.this)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(s, forceResendingToken);
                                    verification_id = s;
                                }

                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    final String code = phoneAuthCredential.getSmsCode();
                                    if( code != null) {
                                        verifyCode(code);
                                    }
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    Toast.makeText(take_ph_no.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                                }
                            })

                            .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);
                }
            }
        });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyCode(OTP.getText().toString());
            }
        });



    }
    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification_id, code);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(take_ph_no.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(take_ph_no.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}