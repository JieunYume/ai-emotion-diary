package com.example.diary.groupList;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diary.Diary_initialActivity;
import com.example.diary.HomeActivity;
import com.example.diary.R;
import com.example.diary.diaryList.diary_listAdapter;
import com.example.diary.diaryList.diary_listItem;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class group_listAdapter extends RecyclerView.Adapter<group_listAdapter.Holder> {

    ArrayList<group_listItem> items;
    private Context context;
    private View view;
    private OnItemClickListener mListener = null;
    private Holder holder;
    private int selected=0;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    public group_listItem getItem(int position) {
        return items.get(position);
    }

    public group_listAdapter(View view, Context context, ArrayList<group_listItem> items) {
        this.context = context;
        this.items = items;
        this.view = view;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        holder=new Holder(v);
        return holder;

    }
    public Holder geth(){
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        group_listItem item = items.get(position);


        if(position==0)
        {
            holder.iv_profile.setImageResource(R.drawable.profile_sample2);

        }
        if(position==1)
            holder.iv_profile.setImageResource(R.drawable.profile_sample3);

        if(TextUtils.isEmpty(item.getNickName()))      //plus
        {

            holder.iv_profile.setImageResource(R.drawable.add);
        }
        else        //다른 그룹 리스트
        {
            //homeActivity.setImageUrl(item.getImagePath(), holder.iv_profile);
        }

        //new HomeActivity.DrawUrlImageTask((CircleImageView) holder.iv_profile).execute(item.getImagePath());

        try {
            holder.tv_id.setText(item.getNickName());
        } catch (NullPointerException E) {

            holder.tv_id.setText(null);
        }




    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private CircleImageView iv_profile;
        private TextView tv_id;
        private LinearLayout ll_item;

        public Holder(@NonNull View itemView) {
            super(itemView);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            tv_id = itemView.findViewById(R.id.tv_id);
            ll_item = itemView.findViewById(R.id.ll_item);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();


                    HomeActivity homeActivity=new HomeActivity();

                    if(items.size()==pos+1)
                    {   //plus 눌렀을때 초대하기 intent
                        Intent intent=new Intent(context, Diary_initialActivity.class);
                        intent.putExtra("memberId", homeActivity.memberId.toString());
                        intent.putExtra("nickName", homeActivity.nickName);
                        intent.putExtra("filePath", homeActivity.imgUrl);
                        Log.d("clickmember", homeActivity.memberId.toString());
                        context.startActivity(intent);

                    }
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {

                            mListener.onItemClick(view, pos);
                            iv_profile.setBorderWidth(6);
                            iv_profile.setBorderColor(Color.BLACK);
                        }


                    }


                }
            });

        }
    }
}