package com.ass2.i190500_i190594;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Chat_mainScreen extends AppCompatActivity {

    FirebaseAuth mAuth;
    String current_user_id;
    List<Messages_list> msgs_list = new ArrayList<>();
    RecyclerView msgs_List_rv;
    String lastMsg = "";
    int UnseenMsg = 0;
    String mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_main_screen);
        msgs_List_rv = findViewById(R.id.msgs_list_RV);
        current_user_id = mAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://i190500-i190594-default-rtdb.firebaseio.com/");

        msgs_List_rv.setHasFixedSize(true);
        msgs_List_rv.setLayoutManager(new LinearLayoutManager(this));


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.child("Registered Users").getChildren()){
                    final String User_id = dataSnapshot.getKey();

                    // getting user's phone number
                    if(User_id.equals(current_user_id))
                        mobile = dataSnapshot.child("phone").getValue(String.class);

                    if(!User_id.equals(current_user_id)){
                        final String getmobile = dataSnapshot.child("phone").getValue(String.class);


                        databaseReference.child("chat").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                int getChatCount = (int)snapshot.getChildrenCount();

                                if(getChatCount > 0){
                                    for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                                        final String getKey = dataSnapshot1.getKey();
                                        final String getUserOne =  dataSnapshot1.child("user_1").getValue(String.class);
                                        final String getUserTwo =  dataSnapshot1.child("user_2").getValue(String.class);

                                        if(getUserOne.equals(getmobile) && getUserTwo.equals(mobile) || getUserOne.equals(mobile) && getUserTwo.equals(getmobile)){
                                            for (DataSnapshot chatsnapshot: dataSnapshot1.child("messages").getChildren()){
                                                final Long getMessagekey = Long.parseLong(chatsnapshot.getKey());
                                                lastMsg = chatsnapshot.child("msg").getValue(String.class);

                                            }
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                        Messages_list msg = new Messages_list(User_id, getmobile, lastMsg, "", UnseenMsg);
                        msgs_list.add(msg);
                    }
                }
                msgs_List_rv.setAdapter(new Messages_adapter(msgs_list, Chat_mainScreen.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}