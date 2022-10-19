package com.ass2.i190500_i190594;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class menu_1 extends AppCompatActivity {

    ImageView add;

    Uri AudioUri;

    ImageView play;
    ImageView pause;

    boolean flag=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        add=findViewById(R.id.add);
        pause=findViewById(R.id.pause);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu_1.this, upload_music.class);
                startActivityForResult(intent, 32);
            }




        });
        MediaPlayer player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);


        play=findViewById(R.id.play);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(AudioUri!=null && flag==true) {
                     flag=false;


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
                }

                else if(AudioUri!=null){
                    player.start();

                }
                else{
                   Toast.makeText(menu_1.this,"No Music to Play",Toast.LENGTH_LONG).show();

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

        if(requestCode==32){

            String a=data.getStringExtra("Music");
            AudioUri=Uri.parse(a);


        }
    }
}