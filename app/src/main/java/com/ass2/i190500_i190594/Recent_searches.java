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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Recent_searches extends AppCompatActivity {

    EditText tvsearch;
    Button search_music;
    FirebaseAuth mAuth;
    Uri AudioURi;
    TextView SongName;
    LinearLayout ll;
    String Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_searches);

        tvsearch=findViewById(R.id.searchtv);

        search_music=findViewById(R.id.searchmusic);
        mAuth=FirebaseAuth.getInstance();
        SongName=findViewById(R.id.TitleSong);

        ll=findViewById(R.id.musicsearched);
        search_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Registered Users").child(mAuth.getCurrentUser().getUid()).child("Musics");
                final Query userQuery = rootRef.orderByChild("Title").equalTo(tvsearch.getText().toString());

                if(userQuery==null) {

                    Toast.makeText(Recent_searches.this, "No Music with this name exists", Toast.LENGTH_LONG).show();
                }

                else {

                    userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot datas : dataSnapshot.getChildren()) {
                                String keys = datas.getKey();
                                Title = datas.child("Title").getValue().toString();
                                String aud = datas.child("Music URL").getValue().toString();

                                if (aud != null && Title != null) {

                                    AudioURi = Uri.parse(aud);

                                    SongName.setText(Title);

                                    ll.setVisibility(Button.VISIBLE);
                                }


                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
            }
        });

       // if(ll.getVisibility()==LinearLayout.VISIBLE){
            Log.i("Button k under","Hello");

            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    Intent i=new Intent();
                    i.putExtra("AudioURL", AudioURi.toString());
                    i.putExtra("Title",Title);
                  //  i.putExtra("Genre",genere.getText().toString());
                   // i.putExtra("Description",description.getText().toString());

                    setResult(Activity.RESULT_OK,i);
                    finish();


                }
            });
        //}



    }
}