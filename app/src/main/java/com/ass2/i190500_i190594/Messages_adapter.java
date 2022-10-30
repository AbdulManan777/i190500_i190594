package com.ass2.i190500_i190594;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Messages_adapter extends RecyclerView.Adapter<Messages_adapter.MyViewHolder> {

    final List<Messages_list> msgList;
    final Context context;

    public Messages_adapter(List<Messages_list> msgList, Context context) {
        this.msgList = msgList;
        this.context = context;
    }

    @NonNull
    @Override
    public Messages_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.message_box, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Messages_adapter.MyViewHolder holder, int position) {
        Messages_list msg = this.msgList.get(position);

        holder.Name.setText(msg.getName());
        holder.last_msg.setText(msg.lastMsg);
        if(msg.getUnseenMsg() == 0)
            holder.unseen_msg_count.setVisibility(View.GONE);
        else
            holder.unseen_msg_count.setVisibility(View.VISIBLE);

        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ChatBox.class);
                i.putExtra("mobileNo",msg.getMobile());
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return msgList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView profile_icon;
        private TextView Name, last_msg, time, unseen_msg_count;
        private LinearLayout rootLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_icon = itemView.findViewById(R.id.profile_icon);
            Name = itemView.findViewById(R.id.username);
            last_msg = itemView.findViewById(R.id.last_msg);
            time = itemView.findViewById(R.id.msg_time);
            unseen_msg_count = itemView.findViewById(R.id.unseen_msg_count);
            rootLayout = itemView.findViewById(R.id.msg_Box);
        }
    }
}
