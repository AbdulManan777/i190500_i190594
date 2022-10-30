package com.ass2.i190500_i190594;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatBox extends AppCompatActivity {

    TextView userName;
    ImageView back, submit;
    EditText msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_box);

        userName = findViewById(R.id.user_Name);
        msg = findViewById(R.id.msg);
        submit = findViewById(R.id.submit_msg);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String phNo = getIntent().getStringExtra("mobileNo");
        userName.setText(phNo);
    }
}