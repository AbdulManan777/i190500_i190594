package com.ass2.i190500_i190594;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class sign_up extends AppCompatActivity {


    EditText pass, phone;

    FirebaseAuth mAuth;
    TextView newuser;
    Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        pass=findViewById(R.id.password_signup);
        phone=findViewById(R.id.phone_email);
        mAuth=FirebaseAuth.getInstance();
        signin=findViewById(R.id.sign_in);
        newuser=findViewById(R.id.newUser);

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sign_up.this,signin_withaccount.class));
            }
        });



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Toast.makeText(sign_up.this,mAuth.getCurrentUser().getUid().toString(),Toast.LENGTH_LONG).show();

                AuthCredential credential = EmailAuthProvider.getCredential(phone.getText().toString(), pass.getText().toString());

                mAuth.signInWithCredential(credential)

                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser currentUser = task.getResult().getUser();

                                    startActivity(new Intent(sign_up.this, menu_1.class));
                                    // Merge prevUser and currentUser accounts and data
                                    // ...
                                } else
                                    Toast.makeText(sign_up.this, "Add Valid User's Email and Password", Toast.LENGTH_SHORT).show();

                            }
                        });



               /* FirebaseDatabase database =FirebaseDatabase.getInstance();
                DatabaseReference ref=database.getReference("Registered Users");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot i : snapshot.getChildren())
                        {
                            if(i.child("phone").getValue().equals(phone.getText().toString())&& i.child("pass").getValue().equals(pass.getText().toString())){

                                   startActivity(new Intent(sign_up.this,menu_1.class));

                            }
                            else{
                                Toast.makeText(sign_up.this,"Not a Valid User",Toast.LENGTH_LONG).show();
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/



            }
        });




    }


}