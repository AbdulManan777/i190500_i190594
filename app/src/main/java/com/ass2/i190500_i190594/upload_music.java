package com.ass2.i190500_i190594;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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
    FirebaseAuth mAuth;

    EditText title,genere,description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_music);

        upload = findViewById(R.id.upload);
        mAuth=FirebaseAuth.getInstance();
        title=findViewById(R.id.Title);
        genere=findViewById(R.id.Genre);
        description=findViewById(R.id.Description);

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

        if (title.getText().toString().isEmpty()) {
            title.setError("Please Enter the title of Song");
            title.requestFocus();
            return;


        }



        if (genere.getText().toString().isEmpty()) {
            genere.setError("Please Enter genre of music");
            title.requestFocus();
            return;


        }

        if(AudioUri==null){
            Toast.makeText(upload_music.this,"Please select and Audio File first",Toast.LENGTH_LONG).show();
            return;
        }


            Intent i=new Intent();
            i.putExtra("AudioURL", AudioUri.toString());
            i.putExtra("Title",title.getText().toString());
            i.putExtra("Genre",genere.getText().toString());
            i.putExtra("Description",description.getText().toString());

            setResult(Activity.RESULT_OK,i);
            finish();

            finish();

    }


    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_AUDIO && resultCode == Activity.RESULT_OK) {
            if ((data != null) && (data.getData() != null)) {
                // Audio is Picked in format of URI


                AudioUri = data.getData();
                Toast.makeText(upload_music.this,"Audio Selected",Toast.LENGTH_LONG).show();

                Calendar c=Calendar.getInstance();
                StorageReference ref=FirebaseStorage.getInstance().getReference().child("Music").child(mAuth.getUid()+c.getTimeInMillis()+".mp3");

                ref.putFile(AudioUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> task=taskSnapshot.getStorage().getDownloadUrl();

                        task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                AudioUri=uri;

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