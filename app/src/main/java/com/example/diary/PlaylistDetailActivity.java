package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diary.moodplayList.mood_playlistAdapter;
import com.example.diary.playList.playlistAdapter;
import com.example.diary.playList.playlistItem;

import java.util.ArrayList;

public class PlaylistDetailActivity extends AppCompatActivity {

    private ImageView iv_back;
    private ImageView iv_mood;
    private TextView tv_date;
    private TextView tv_mood;
    private TextView tv_count;
    private RecyclerView rv_playlist;
    static ArrayList<playlistItem> playlist;
    playlistAdapter playlist_adapter=null;

    public static void setPlaylist(ArrayList<playlistItem> playliset){

        playlist=playliset;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_playlist_detail);
        View view = findViewById(R.id.rootView);


        iv_back=findViewById(R.id.iv_back);
        iv_mood=findViewById(R.id.iv_mood);
        tv_date=findViewById(R.id.tv_date);
        tv_mood=findViewById(R.id.tv_mood);
        tv_count=findViewById(R.id.tv_count);
        rv_playlist=findViewById(R.id.rv_playlist);

        Intent intent=getIntent();

        tv_date.setText(intent.getStringExtra("date"));
        String mood=intent.getStringExtra("mood");
        tv_mood.setText(mood);

        switch(mood)
        {
            case "기쁨":
                iv_mood.setImageResource(R.drawable.happy_sample);
                break;
            case "뿌듯":
                iv_mood.setImageResource(R.drawable.proud_sample);
                break;
            case "당황":
                iv_mood.setImageResource(R.drawable.embarrassed_sample);
                break;
            case "분노":
                iv_mood.setImageResource(R.drawable.angry_sample);
                break;
            case "불안":
                iv_mood.setImageResource(R.drawable.nervous_sample);
                break;
            case "상처":
                iv_mood.setImageResource(R.drawable.hurt_sample);
                break;
            case "슬픔":
                iv_mood.setImageResource(R.drawable.sad_sample);
                break;

        }


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        //밑에꺼가 지금 우리가 해야할꺼
        playlist_adapter=new playlistAdapter(view, getApplicationContext(),playlist);


        rv_playlist.setLayoutManager(linearLayoutManager);
        //list_adapter=new HomeTimelineAdapter(listItem);
        //가로
        rv_playlist.setAdapter(playlist_adapter);
        playlist_adapter.notifyDataSetChanged();

    }
}