package com.ass2.i190500_i190594;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddSongsAdapter extends RecyclerView.Adapter<AddSongsAdapter.MyViewHolder> {

   public static List<SongsListCheckBox> ls;
    Context c;

    public AddSongsAdapter(List<SongsListCheckBox> ls, Context c) {

        this.ls = ls;
        this.c = c;
    }


    @NonNull
    @Override
    public AddSongsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(c).inflate(R.layout.songs_list_rv_row, parent, false);
        return new AddSongsAdapter.MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull AddSongsAdapter.MyViewHolder holder, int position) {
        holder.songTitle.setText(ls.get(position).getTitle());
        holder.cb.setTag(position);
        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.cb.getTag();
                Toast.makeText(c, ls.get(pos).getTitle() + " clicked!", Toast.LENGTH_SHORT).show();

                if (ls.get(pos).isSelected()) {
                    ls.get(pos).setSelected(false);
                } else {
                    ls.get(pos).setSelected(true);
                }
            }
        });
        //holder.Artist.setText(ls.get(position).getArtist());
        //holder.songImage.setImageBitmap(ls.get(position).getArtistPic());

        /*holder.cb.setText("Checkbox " + position);
        holder.cb.setTag(position);

        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.cb.getTag();
                Toast.makeText(c, ls.get(pos).getTitle() + " clicked!", Toast.LENGTH_SHORT).show();

                if (ls.get(pos).isSelected()) {
                    ls.get(pos).setSelected(false);
                } else {
                    ls.get(pos).setSelected(true);
                }
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView songTitle, Artist;
        ImageView songImage;
        CheckBox cb;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            songTitle=itemView.findViewById(R.id.TitleSong);
            Artist=itemView.findViewById(R.id.artist);
            songImage=itemView.findViewById(R.id.songImage);
            cb=itemView.findViewById(R.id.checkboxSongs);

        }
    }

}
