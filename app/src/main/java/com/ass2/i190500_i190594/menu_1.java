package com.ass2.i190500_i190594;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class menu_1 extends AppCompatActivity {

    ImageView add;

    Uri AudioUri;

    ImageView play;
    ImageView pause, signout;
    FirebaseAuth mAuth;

    boolean flag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        add = findViewById(R.id.add);
        pause = findViewById(R.id.pause);
        play = findViewById(R.id.play);
        signout=findViewById(R.id.singout);
        mAuth=FirebaseAuth.getInstance();

        //Toast.makeText(menu_1.this,mAuth.getCurrentUser().getUid().toString(),Toast.LENGTH_LONG).show();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu_1.this, upload_music.class);
                startActivityForResult(intent, 32);
            }


        });
        MediaPlayer player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(menu_1.this,sign_up.class));
            }
        });




        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (AudioUri != null && flag == true) {
                    flag = false;


                    pause.setVisibility(Button.VISIBLE);
                    play.setVisibility(Button.GONE);

                    try {
                        player.setDataSource(menu_1.this, AudioUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {

                            player.start();
                        }
                    });


                    player.prepareAsync();
                } else if (AudioUri != null) {
                    pause.setVisibility(Button.VISIBLE);
                    play.setVisibility(Button.GONE);
                    player.start();

                } else {
                    Toast.makeText(menu_1.this, "No Music to Play", Toast.LENGTH_LONG).show();

                }

            }
        });







        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player.isPlaying()){
                    pause.setVisibility(Button.GONE);
                    play.setVisibility(Button.VISIBLE);
                    player.pause();
                }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==32&&resultCode== Activity.RESULT_OK){

            String aud=data.getStringExtra("AudioURL");
            String title=data.getStringExtra("Title");
            String gen=data.getStringExtra("Genre");
            String desc=data.getStringExtra("Description");

            AudioUri=Uri.parse(aud);
            FirebaseDatabase  database =FirebaseDatabase.getInstance();

            HashMap<String,String> map=new HashMap<String,String>();//Creating HashMap
            map.put("Title",title);  //Put elements in Map
            map.put("Genre",gen);
            map.put("Description",desc);
            map.put("Music URL",aud);

            DatabaseReference myRef=database.getReference().child("Registered Users").child("Musics");

            myRef.setValue(map);










        }
    }
}
