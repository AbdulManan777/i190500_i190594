package com.ass2.i190500_i190594;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongsListRvAdapter extends RecyclerView.Adapter<SongsListRvAdapter.MyViewHolder> {

    List<SongsList> ls;
    Context c;

    public SongsListRvAdapter(List<SongsList> ls, Context c){

        this.ls=ls;
        this.c=c;
    }
    @NonNull
    @Override
    public SongsListRvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(c).inflate(R.layout.songs_playlist_rv,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull SongsListRvAdapter.MyViewHolder holder, int position) {

        holder.songTitle.setText(ls.get(position).getTitle());




      /*  holder.Artist.setText(ls.get(position).getArtist());
        holder.songImage.setImageBitmap(ls.get(position).getArtistPic());
        holder.cross.setImageBitmap(ls.get(position).getCrossPic());
*/
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView songTitle, Artist;
        ImageView songImage, cross;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            songTitle=itemView.findViewById(R.id.TitleSong);
            Artist=itemView.findViewById(R.id.artist);
            songImage=itemView.findViewById(R.id.songImage);
            cross=itemView.findViewById(R.id.cross);

        }
    }
}
