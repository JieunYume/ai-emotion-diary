package com.example.diary.playList;


import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.diary.R;
import com.example.diary.groupList.group_listAdapter;
import com.example.diary.moodplayList.mood_playlistItem;

import java.util.ArrayList;

public class playlistAdapter extends RecyclerView.Adapter<playlistAdapter.Holder> {

    ArrayList<playlistItem> items;
    private Context context;
    private View view;
    private Holder holder;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }


    public playlistItem getItem(int position) {
        return items.get(position);
    }

    public playlistAdapter(View view, Context context, ArrayList<playlistItem> items) {
        this.context = context;
        this.items = items;
        this.view = view;
    }

    @NonNull
    @Override
    public playlistAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_item_txt, parent, false);
        holder=new Holder(v);
        return holder;

    }
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        playlistItem item = items.get(position);

        String id = item.getUrl().substring(item.getUrl().lastIndexOf("/")+1);  //맨마지막 '/'뒤에 id가있으므로 그것만 파싱해줌
        Log.d("유튭 파싱한 아이디id 값", id);
        String url ="https://img.youtube.com/vi/"+ id+ "/" + "default.jpg";  //유튜브 썸네일 불러오는 방법
        Glide.with(context).load(url).into(holder.iv_song);

        Glide.with(context).load(url).into(holder.iv_song);
        //holder.iv_song.setImageResource(item.getUrl());


        holder.tv_song.setText(item.getSong());
        holder.tv_singer.setText(item.getSinger());
        holder.tv_date.setText(item.getDate());

        holder.ll_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //유튜브 링크
                context.startActivity(new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse(item.getUrl())) // edit this url
                        .setPackage("com.google.android.youtube").addFlags(FLAG_ACTIVITY_NEW_TASK));
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public class Holder extends RecyclerView.ViewHolder {

       private ImageView iv_song;
       private TextView tv_song;
       private TextView tv_singer;
       private ImageView iv_play;
       private LinearLayout ll_song;
       private TextView tv_date;


        public Holder(@NonNull View itemView) {
            super(itemView);
            iv_song = itemView.findViewById(R.id.iv_song);
            tv_song = itemView.findViewById(R.id.tv_song);
            tv_singer = itemView.findViewById(R.id.tv_singer);
            iv_play = itemView.findViewById(R.id.iv_play);
            tv_date = itemView.findViewById(R.id.tv_date);
            ll_song=itemView.findViewById(R.id.ll_song);


        }
    }
}