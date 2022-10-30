package com.ass2.i190500_i190594;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class SongsListFirebaseActivity extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private ArrayList<SongsListCheckBox> modelArrayList;
    private AddSongsAdapter customAdapter;
    FirebaseAuth mAuth;
    Button addsongs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list_firebase);

        mAuth=FirebaseAuth.getInstance();

        recyclerView = (RecyclerView) findViewById(R.id.rvSongs);
        addsongs=findViewById(R.id.AddSongs1);
        RecyclerView.LayoutManager lm=new LinearLayoutManager(SongsListFirebaseActivity.this);
        recyclerView.setLayoutManager(lm);
        modelArrayList=new ArrayList<>();

        customAdapter=new AddSongsAdapter(modelArrayList, SongsListFirebaseActivity.this);


        recyclerView.setAdapter(customAdapter);



        FirebaseDatabase.getInstance().getReference().child("Registered Users").child(mAuth.getCurrentUser().getUid()).child("Musics").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    SongsListCheckBox sb=new SongsListCheckBox();
                    sb.setTitle((String) ds.child("Title").getValue());

                    modelArrayList.add(sb);
                }

                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addsongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<SongsList> songs=new ArrayList<>();

                for (int i = 0; i < AddSongsAdapter.ls.size(); i++) {
                    if (AddSongsAdapter.ls.get(i).isSelected()) {
                        SongsList s=new SongsList();
                        s.setTitle(AddSongsAdapter.ls.get(i).getTitle());
                        songs.add(s);
                    }

                }

                Intent i=new Intent();

                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", songs);
                i.putExtra("BUNDLE",args);
                //  i.putExtra("Genre",genere.getText().toString());
                // i.putExtra("Description",description.getText().toString());

                setResult(36,i);
                finish();

            }
        });










    }



}