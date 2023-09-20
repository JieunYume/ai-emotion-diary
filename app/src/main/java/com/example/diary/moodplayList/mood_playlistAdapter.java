package com.example.diary.moodplayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diary.Diary_initialActivity;
import com.example.diary.HomeActivity;
import com.example.diary.R;
import com.example.diary.groupList.group_listAdapter;
import com.example.diary.groupList.group_listItem;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class mood_playlistAdapter extends RecyclerView.Adapter<mood_playlistAdapter.Holder> {

    ArrayList<mood_playlistItem> items;
    private Context context;
    private View view;
    private Holder holder;
    private group_listAdapter.OnItemClickListener mListener = null;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
    public void setOnItemClickListener(group_listAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }


    public mood_playlistItem getItem(int position) {
        return items.get(position);
    }

    public mood_playlistAdapter(View view, Context context, ArrayList<mood_playlistItem> items) {
        this.context = context;
        this.items = items;
        this.view = view;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_mood_item, parent, false);
        holder=new Holder(v);
        return holder;

    }
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        mood_playlistItem item = items.get(position);
        holder.iv_mood.setImageResource(item.getSrc());
        holder.iv_mood.setClipToOutline(true);
        holder.tv_mood.setText(item.getMood());



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private ImageView iv_mood;
        private ImageView iv_go;
        private TextView tv_mood;

        public Holder(@NonNull View itemView) {
            super(itemView);
            iv_mood = itemView.findViewById(R.id.iv_mood);
            iv_go = itemView.findViewById(R.id.iv_go);
            tv_mood = itemView.findViewById(R.id.tv_mood);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(view, pos);

                        }


                    }


                }
            });

        }
    }
}