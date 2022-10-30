package com.ass2.i190500_i190594;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AddSongsInPlaylistActivity extends AppCompatActivity {

    Button AddSongs;
    private ArrayList<SongsList> songslist;
    private RecyclerView recyclerView;
    SongsListRvAdapter customAdapter;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_songs_in_playlist);

        AddSongs=findViewById(R.id.AddSongs);
        tv=findViewById(R.id.tv1);
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        RecyclerView.LayoutManager lm=new LinearLayoutManager(AddSongsInPlaylistActivity.this);
        recyclerView.setLayoutManager(lm);
        songslist=new ArrayList<>();

        customAdapter=new SongsListRvAdapter(songslist, AddSongsInPlaylistActivity.this);


        recyclerView.setAdapter(customAdapter);



        AddSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AddSongsInPlaylistActivity.this,SongsListFirebaseActivity.class);
              startActivityForResult(i,36);



            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

      //  songslist= (ArrayList<SongsListCheckBox>) getIntent().getSerializableExtra("SongsList");

        if(requestCode==36&&resultCode== Activity.RESULT_OK) {


            //Intent intent = getIntent();
            Bundle args = data.getBundleExtra("BUNDLE");
            songslist = (ArrayList<SongsList>) args.getSerializable("ARRAYLIST");


            customAdapter.notifyDataSetChanged();

            if (songslist != null) {
                recyclerView.setVisibility(RecyclerView.VISIBLE);
                tv.setVisibility(TextView.GONE);

            } else {
                recyclerView.setVisibility(RecyclerView.GONE);
                tv.setVisibility(TextView.VISIBLE);


            }

        }



    }
}