package com.ass2.i190500_i190594;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class upload_music extends AppCompatActivity {

    ImageView upload;
    private final int PICK_AUDIO = 1;
    Uri AudioUri;
    Button up;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_music);

        upload = findViewById(R.id.upload);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent audio = new Intent();
                audio.setType("audio/*");
                audio.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(audio, "Select"), PICK_AUDIO);
             //   Toast.makeText(upload_music.this,AudioUri.toString(),Toast.LENGTH_LONG).show();



            }
        });
        up=findViewById(R.id.button1);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMusic();
            }
        });




    }

    public void sendMusic() {

        if(AudioUri!=null) {
           /* Intent i = new Intent();
            i.putExtra("Music", AudioUri.toString());
            setResult(32, i);*/

            finish();
        }
    }


    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_AUDIO && resultCode == Activity.RESULT_OK) {
            if ((data != null) && (data.getData() != null)) {
                // Audio is Picked in format of URI

                AudioUri = data.getData();
                Toast.makeText(upload_music.this,"Audio Selected",Toast.LENGTH_LONG).show();
                FirebaseStorage storage=FirebaseStorage.getInstance();
                Calendar c=Calendar.getInstance();
                StorageReference ref=storage.getReference().child("Music/"+c.getTimeInMillis()+AudioUri+".mp3");
                ref.putFile(AudioUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> task=taskSnapshot.getStorage().getDownloadUrl();

                        task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {


                                Log.d("AudioURl",uri.toString());
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(upload_music.this,"Failed to Upload",Toast.LENGTH_LONG).show();

                    }
                });
                //select_Audio.setText("Audio Selected");
            }
        }
    }
}