package com.example.diary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.diary.groupList.group_listAdapter;
import com.example.diary.moodplayList.mood_playlistAdapter;
import com.example.diary.moodplayList.mood_playlistItem;
import com.example.diary.playList.playlistAdapter;
import com.example.diary.playList.playlistItem;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class PlayListActivity extends Fragment {

    private View view;
    private TextView tv_date;
    private ImageView iv_category;
    private RecyclerView rv_playlist_mood;
    private RecyclerView rv_playlist;
    private mood_playlistAdapter mood_adapter=null;
    private playlistAdapter playlist_adapter=null;
    ArrayList<playlistItem> play_list;
    Boolean flag=false;

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };
    private void updateLabel() {
        String myFormat = "yyyy년 MM월";    // 출력형식   2021/07/26
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);




        tv_date.setText(sdf.format(myCalendar.getTime()));

        if(flag)
        {


            Log.d("8월", "8월");

            play_list=new ArrayList<>();
            //누르면 유튜브
            play_list.add(new playlistItem("Mr. Blue Sky", "ELO", "https://youtu.be/TysmhKj8iQE", "08/31", "행복"));
            play_list.add(new playlistItem(" I Want You Back", "Jackson 5", "https://youtu.be/2iVwn_sa2Ew", "08/30", "행복"));
            play_list.add(new playlistItem("Soda City Funk", "Soda City Funk", "https://youtu.be/zhIScvlFn2w", "09/18", "뿌듯"));
            play_list.add(new playlistItem("Baby Powder", "Jenevieve", "https://youtu.be/O1Qh7j1yD8Y", "09/19", "행복"));

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            for(int i=0; i<play_list.size(); i++)
            {
                Log.d("play_list", play_list.get(i).getSong());
            }


            //밑에꺼가 지금 우리가 해야할꺼
            playlistAdapter e=new playlistAdapter(view, getContext(),play_list);


            rv_playlist.setLayoutManager(linearLayoutManager);
            //list_adapter=new HomeTimelineAdapter(listItem);
            //가로
            rv_playlist.setAdapter(e);
            e.notifyDataSetChanged();
            //setData(chart, Arrays.asList(10, 50, 10, 10, 10, 10));
        }
        if(!flag)
        {

            Log.d("9월", "9월");
            fake();
            //setData(chart, Arrays.asList(30, 20, 20, 10, 10, 10));
        }




    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = (ViewGroup) inflater.inflate(R.layout.activity_play_list, container, false);

        tv_date=view.findViewById(R.id.tv_date);
        iv_category=view.findViewById(R.id.iv_category);
        rv_playlist_mood=view.findViewById(R.id.rv_playlist_mood);
        rv_playlist=view.findViewById(R.id.rv_date);


        CalendarDay today = CalendarDay.today();
        String date=today.getYear() + "년 " + (today.getMonth() + 1) + "월 " + today.getDay() + "일";
        tv_date.setText(date);
        //기쁨, 뿌듯, 슬픔, 화남, 당황, 불안, 상처

        ArrayList<mood_playlistItem> mood_list=new ArrayList<>();

        mood_list.add(new mood_playlistItem("행복", R.drawable.happy_sample));
        mood_list.add(new mood_playlistItem("뿌듯", R.drawable.proud_sample));
        mood_list.add(new mood_playlistItem("슬픔", R.drawable.sad_sample));
        mood_list.add(new mood_playlistItem("분노", R.drawable.angry_sample));
        mood_list.add(new mood_playlistItem("당황", R.drawable.embarrassed_sample));
        mood_list.add(new mood_playlistItem("불안", R.drawable.nervous_sample));
        mood_list.add(new mood_playlistItem("상처", R.drawable.hurt_sample));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        //밑에꺼가 지금 우리가 해야할꺼
        mood_adapter=new mood_playlistAdapter(view, getContext(),mood_list);


        rv_playlist_mood.setLayoutManager(linearLayoutManager);
        //list_adapter=new HomeTimelineAdapter(listItem);
        //가로
        rv_playlist_mood.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rv_playlist_mood.setAdapter(mood_adapter);
        mood_adapter.notifyDataSetChanged();

        fake();

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(getContext(), myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                flag=!flag;
                Log.d("flag", flag+"");



            }
        });

        mood_adapter.setOnItemClickListener(new group_listAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent=new Intent(getActivity(), PlaylistDetailActivity.class);
                //date, mood, setPlaylist(arraylist)
                intent.putExtra("date", date);
                intent.putExtra("mood", mood_adapter.getItem(position).getMood());
                Log.d("mood", mood_adapter.getItem(position).getMood());
                ArrayList<playlistItem> temp=new ArrayList<>();

                for(int i=0; i<play_list.size();i++)
                {
                    if(play_list.get(i).getMood().equals(mood_adapter.getItem(position).getMood()))
                    {
                        temp.add(play_list.get(i));
                    }
                }


                for(int i=0; i<temp.size(); i++)
                {
                    Log.d("temp", temp.get(i).getSong()+"");
                }

                PlaylistDetailActivity playlistDetailActivity=new PlaylistDetailActivity();
                playlistDetailActivity.setPlaylist(temp);

                startActivity(intent);
            }
        });


        /*

        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:5000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        Retrofit_emotion_bot service1 = retrofit.create(Retrofit_emotion_bot.class);

        JoinData_emotion_bot joinData_emotion_bot = new JoinData_emotion_bot(comment);
        Call<ReceiveModel_emotion_bot> call = service1.getBot(joinData_emotion_bot);
        call.enqueue(new Callback<ReceiveModel_emotion_bot>() {
            @Override
            public void onResponse(Call<ReceiveModel_emotion_bot> call, retrofit2.Response<ReceiveModel_emotion_bot> response) {
                if (response.isSuccessful()) {
                    // 정상적으로 통신이 성공된 경우
                    ReceiveModel_emotion_bot result = response.body();
                    Log.d("감정봇 통신", "onResponse: 성공, 결과\n" + result.toString());

                    List<> a= result.;
                    ArrayList<playlistItem> playlist = new ArrayList<playlistItem>();

                    for(int i=0; i<a.size();i++)
                    {
                        playlist.add(new playlistItem(a.getSong(), a.getSinger(), a.getUrl(), a.getMood()));
                    }

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


                    //밑에꺼가 지금 우리가 해야할꺼
                    playlist_adapter=new playlistAdapter(view, getContext(),playlist);


                    rv_playlist.setLayoutManager(linearLayoutManager);
                    //list_adapter=new HomeTimelineAdapter(listItem);
                    //가로
                    rv_playlist.setAdapter(playlist_adapter);
                    playlist_adapter.notifyDataSetChanged();

                    mood_adapter.setOnItemClickListener(new group_listAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {


                            ArrayList<playlistItem> list=new ArrayList<playlistItem>();
                            for(int i=0; i<playlist.size(); i++)
                            {
                                if((playlist.get(i).getMood()).equals(mood_adapter.getItem(position).getMood()))
                                {
                                    list.add(playlist.get(i).getSong(), playlist.get(i).getSinger(), playlist.get(i).getUrl());
                                }
                            }

                            Intent intent=new Intent(getContext(), );
                            intent.putExtra()   //감정이름, 리스트
                        }
                    });



                } else {
                    Log.d("감정봇 통신", "onResponse: 실패"+response.toString());
                }
            }

            @Override
            public void onFailure(Call<ReceiveModel_emotion_bot> call, Throwable t) {
                Log.d("감정봇 통신", "onFailure" + t.getMessage());
            }


        });*/



        return view;



    }
    void fake(){

        play_list=new ArrayList<>();
        //누르면 유튜브
        play_list.add(new playlistItem("DON'T TOUCH ME", "REFUND SISTERS(환불원정대)", "https://youtu.be/IQ0zkMpIkuE", "09/20", "분노"));
        play_list.add(new playlistItem("No Blueberries", "DPR IAN", "https://youtu.be/zKUWWlxemJQ", "09/19", "행복"));
        play_list.add(new playlistItem("Calvin_s_Joint", "Calvin_s_Joint", "https://youtu.be/BphXxG0BA9A", "09/18", "행복"));
        play_list.add(new playlistItem("너답기기안", "MEENOI(미노이)", "https://youtu.be/L-iKO4jKRvI", "09/17", "슬픔"));
        play_list.add(new playlistItem("은하수", "쿠잉(COOING)", "https://youtu.be/aJHl-ROGqoM", "09/15", "슬픔"));
        play_list.add(new playlistItem("Soda City Funk", "Soda City Funk", "https://youtu.be/zhIScvlFn2w", "09/14", "뿌듯"));
        play_list.add(new playlistItem("Baby Powder", "Jenevieve", "https://youtu.be/O1Qh7j1yD8Y", "09/10", "행복"));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        //밑에꺼가 지금 우리가 해야할꺼
        playlist_adapter=new playlistAdapter(view, getContext(),play_list);


        rv_playlist.setLayoutManager(linearLayoutManager);
        //list_adapter=new HomeTimelineAdapter(listItem);
        //가로
        rv_playlist.setAdapter(playlist_adapter);
        playlist_adapter.notifyDataSetChanged();


    }
    void fake1(){

        play_list=new ArrayList<>();
        //누르면 유튜브
        play_list.add(new playlistItem("Mr. Blue Sky", "ELO", "https://youtu.be/TysmhKj8iQE", "08/31", "행복"));
        play_list.add(new playlistItem(" I Want You Back", "Jackson 5", "https://youtu.be/2iVwn_sa2Ew", "08/30", "행복"));
        play_list.add(new playlistItem("Soda City Funk", "Soda City Funk", "https://youtu.be/zhIScvlFn2w", "09/18", "뿌듯"));
        play_list.add(new playlistItem("Baby Powder", "Jenevieve", "https://youtu.be/O1Qh7j1yD8Y", "09/19", "행복"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        //밑에꺼가 지금 우리가 해야할꺼
        playlist_adapter=new playlistAdapter(view, getContext(),play_list);


        rv_playlist.setLayoutManager(linearLayoutManager);
        //list_adapter=new HomeTimelineAdapter(listItem);
        //가로
        rv_playlist.setAdapter(playlist_adapter);
        playlist_adapter.notifyDataSetChanged();

    }
}