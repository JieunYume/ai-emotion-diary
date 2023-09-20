package com.example.diary.diaryList;

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
import com.example.diary.Diary_resultActivity;
import com.example.diary.HomeActivity;
import com.example.diary.R;
import com.example.diary.groupList.group_listAdapter;
import com.example.diary.groupList.group_listItem;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class diary_listAdapter extends RecyclerView.Adapter<diary_listAdapter.Holder> {

    ArrayList<diary_listItem> items;
    private Context context;
    private View view;
    private OnItemClickListener mListener = null;


    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    public diary_listItem getItem(int position) {
        return items.get(position);
    }

    public diary_listAdapter(View view, Context context, ArrayList<diary_listItem> items) {
        this.context = context;
        this.items = items;
        this.view = view;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_diary_item, parent, false);
        return new Holder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        diary_listItem item = items.get(position);

        String emotion=item.getMood_emoji_name();
        //happy, proud, sad, angry, nervous, wound, embressed
        switch(emotion)
        {
            case "happy":
                holder.iv_emotion.setImageResource(R.drawable.happy);
                break;
            case "angry":
                holder.iv_emotion.setImageResource(R.drawable.angry);
                break;
            case "sad":
                holder.iv_emotion.setImageResource(R.drawable.sad);
                break;
            case "anxiety":
                holder.iv_emotion.setImageResource(R.drawable.nervous);
                break;
            case "hurt":
                holder.iv_emotion.setImageResource(R.drawable.wound);
                break;
            case "flustered":
                holder.iv_emotion.setImageResource(R.drawable.embarrassed);
                break;
            case "proud":
                holder.iv_emotion.setImageResource(R.drawable.proud);
                break;
        }
        holder.tv_id.setText(item.getMember_id());
        holder.tv_date.setText(item.getCreate_date());
        holder.tv_contents.setText(item.getThing());
        holder.tv_like.setText(item.getLike_count().toString());

        holder.ll_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Diary_resultActivity.class);
                intent.putExtra("memberId", (item.getMemberId()).toString());
                intent.putExtra("diaryId", (item.getDiary_id()).toString());
                intent.putExtra("flag", "0");

                context.startActivity(intent);
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

        private ImageView iv_emotion;
        private TextView tv_id;
        private TextView tv_date;
        private TextView tv_contents;
        private TextView tv_like;
        private LinearLayout ll_diary;

        public Holder(@NonNull View itemView) {
            super(itemView);
            iv_emotion = itemView.findViewById(R.id.iv_emotion);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_date=itemView.findViewById(R.id.tv_date);
            tv_contents=itemView.findViewById(R.id.tv_contents);
            tv_like=itemView.findViewById(R.id.tv_like);
            ll_diary=itemView.findViewById(R.id.ll_diary);


            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos=getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION)
                    {
                        String diary_id = items.get(pos).getDiary_id();

                        Log.d("diary_id", diary_id);
                    }


                }
            });

        }*/

        }
    }
}