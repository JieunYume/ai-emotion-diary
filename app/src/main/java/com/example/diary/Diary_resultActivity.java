package com.example.diary;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Diary_resultActivity extends AppCompatActivity {

    private TextView tv_id;
    private TextView tv_date;
    private TextView tv_create_date;
    private TextView tv_diary;
    private LinearLayout bt_cancel;
    private LinearLayout bt_crop;
    private ImageView iv_emotion;
    private ImageView iv_like;
    private TextView tv_like;
    private BarChart chart;
    private TextView tv_slogon;
    private TextView tv_emotion_bot;
    private ImageView iv_emotion_bot;
    private ImageView iv_song;
    private ImageView iv_sun;
    private ImageView iv_crop;
    private TextView tv_songName;
    private TextView tv_singer;
    private TextView tv_crop;
    private RelativeLayout ll_play;
    private View fake_line;
    private LinearLayout fake_profile;
    private Button bt_comment;
    RequestQueue queue;
    private static Boolean like_flag=false;
    private Long memberId=1L;
    private Long diaryId=1L;
    BarData chartData;
    private NestedScrollView scrollview;
    private String url=null;
    private LinearLayout ll_song;
    private String flag;
    private ProgressDialog progressDialog;
    private LinearLayout ll_comment;

    //일기 쓴 다음에 넘어가야하고, 홈에서 클릭하면 넘어가야 한다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_result2);

        tv_crop=findViewById(R.id.tv_crop);
        ll_song=findViewById(R.id.ll_song);
        tv_id=findViewById(R.id.tv_id);
        tv_date=findViewById(R.id.tv_date);
        tv_create_date=findViewById(R.id.tv_create_date);
        tv_diary=findViewById(R.id.tv_diary);
        bt_cancel=findViewById(R.id.bt_cancel);
        bt_crop=findViewById(R.id.bt_crop);
        iv_emotion=findViewById(R.id.iv_emotion);
        iv_like=findViewById(R.id.iv_like);
        tv_like=findViewById(R.id.tv_like);
        chart=findViewById(R.id.chart);
        tv_slogon=findViewById(R.id.tv_slogon);
        tv_emotion_bot=findViewById(R.id.tv_emotion_bot);
        iv_emotion_bot=findViewById(R.id.iv_emotion_bot);
        iv_song=findViewById(R.id.iv_song);
        tv_songName=findViewById(R.id.tv_songName);
        tv_singer=findViewById(R.id.tv_singer);
        ll_play=findViewById(R.id.ll_play);
        fake_line=findViewById(R.id.fake_line);
        fake_profile=findViewById(R.id.fake_profile);
        bt_comment=findViewById(R.id.bt_comment);
        iv_sun=findViewById(R.id.iv_sun);
        iv_crop=findViewById(R.id.iv_crop);
        scrollview=findViewById(R.id.scrollview);
        ll_comment=findViewById(R.id.ll_comment);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        Animation animation1=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        progressDialog = new ProgressDialog(this);

        iv_sun.startAnimation(animation1);
        iv_crop.startAnimation(animation);

        fake_line.setVisibility(View.GONE);
        fake_profile.setVisibility(View.GONE);

        Intent intent=getIntent();
        String member=intent.getStringExtra("memberId");
        String diary=intent.getStringExtra("diaryId");
        flag=intent.getStringExtra("flag");

        memberId=Long.parseLong(member);
        diaryId=Long.parseLong(diary);
        if(queue==null)
        {

            queue= Volley.newRequestQueue(getApplicationContext());
        }

        if(flag.equals("0"))
        {
            bt_crop.setVisibility(View.INVISIBLE);
            tv_crop.setVisibility(View.INVISIBLE);
        }
        else
        {
            bt_crop.setVisibility(View.VISIBLE);
            tv_crop.setVisibility(View.VISIBLE);

            ll_comment.setVisibility(View.INVISIBLE);
        }

        //setData(chart);

        /*
        String url="http://43.202.124.240:8080/api/v1/diary/2";

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("test_volley", response+"");
                Toast.makeText(getApplicationContext(), response+"", Toast.LENGTH_SHORT);
            }}, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("test_volley", "error");
                Toast.makeText(getApplicationContext(), "error"+"", Toast.LENGTH_SHORT);
            }

        });

        queue.add(jsonObjectRequest);

    }*/



        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://43.202.124.240:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        Retrofit_result service1 = retrofit.create(Retrofit_result.class);

        Call<ReceiveModel_result> call = service1.getResultInfo(diaryId);
        call.enqueue(new Callback<ReceiveModel_result>() {
            @Override
            public void onResponse(Call<ReceiveModel_result> call, retrofit2.Response<ReceiveModel_result> response) {
                if (response.isSuccessful()) {
                    // 정상적으로 통신이 성공된 경우
                    ReceiveModel_result result = response.body();
                    Log.d("결과 통신", "onResponse: 성공, 결과\n" + result.toString());

                    tv_id.setText(result.getWriterNickName());
                    String[] a=(result.getCreateDate().split("T")[0]).split("-");
                    tv_date.setText(a[0]+"년 "+a[1]+"월 "+a[2]+"일");
                    tv_create_date.setText(result.getCreateDate());
                    String mood=result.getMoodEmoji();

                    switch(mood)
                    {
                        case "happy":
                            iv_emotion.setImageResource(R.drawable.happy);
                            iv_emotion_bot.setImageResource(R.drawable.happy);
                            break;
                        case "proud":
                            iv_emotion.setImageResource(R.drawable.proud);
                            iv_emotion_bot.setImageResource(R.drawable.proud);
                        case "anxiety":
                            iv_emotion.setImageResource(R.drawable.nervous);
                            iv_emotion_bot.setImageResource(R.drawable.nervous);
                            break;
                        case "sad":
                            iv_emotion.setImageResource(R.drawable.sad);
                            iv_emotion_bot.setImageResource(R.drawable.sad);
                            break;
                        case "angry":
                            iv_emotion.setImageResource(R.drawable.angry);
                            iv_emotion_bot.setImageResource(R.drawable.angry);
                            break;
                        case "hurt":
                            iv_emotion.setImageResource(R.drawable.wound);
                            iv_emotion_bot.setImageResource(R.drawable.wound);
                            break;
                        case "flustered":
                            iv_emotion.setImageResource(R.drawable.embarrassed);
                            iv_emotion_bot.setImageResource(R.drawable.embarrassed);
                            break;
                    }

                    tv_diary.setText(result.getThing()+"\n"+result.getImpression());
                    tv_like.setText(result.getLikeNum().toString());

                    emotion_analyses(result.getThing()+"\n"+result.getImpression());
                    emotion_bot(result.getThing()+"\n"+result.getImpression());
                    wise_bot(result.getThing()+"\n"+result.getImpression());






                } else {
                    Log.d("결과 통신", "onResponse: 실패"+response.toString());
                }
            }

            @Override
            public void onFailure(Call<ReceiveModel_result> call, Throwable t) {
                Log.d("통신", "onFailure" + t.getMessage());
            }


        });
        //통계함수


        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ll_song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApplicationContext().startActivity(new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse(url)) // edit this url
                        .setPackage("com.google.android.youtube").addFlags(FLAG_ACTIVITY_NEW_TASK));
            }
        });



        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fake_line.setVisibility(View.VISIBLE);
                fake_profile.setVisibility(View.VISIBLE);

                InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
        bt_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropDialog cropDialog=(CropDialog) CropDialog.cropDialog;
                cropDialog=new CropDialog(view.getContext());
                WindowManager.LayoutParams params=cropDialog.getWindow().getAttributes();
                params.width=WindowManager.LayoutParams.WRAP_CONTENT;
                params.height=WindowManager.LayoutParams.WRAP_CONTENT;
                cropDialog.getWindow().setAttributes((WindowManager.LayoutParams)params);
                cropDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cropDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                cropDialog.setId(memberId);
                cropDialog.show();
            }
        });

        iv_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //이미지도 바꾸면 좋겠다
                    //좋아요
                    Gson gson = new GsonBuilder().setLenient().create();
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
                    Retrofit retrofit= new Retrofit.Builder()
                            .baseUrl("http://43.202.124.240:8080/api/v1/")
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(client)
                            .build();
                    Retrofit_like service1 = retrofit.create(Retrofit_like.class);

                    JoinData_like joinData_like =new JoinData_like(memberId, diaryId);
                    Call<ReceiveModel_like> call = service1.getLikeInfo(joinData_like);
                    call.enqueue(new Callback<ReceiveModel_like>() {
                        @Override
                        public void onResponse(Call<ReceiveModel_like> call, retrofit2.Response<ReceiveModel_like> response) {
                            if (response.isSuccessful()) {
                                // 정상적으로 통신이 성공된 경우
                                ReceiveModel_like result = response.body();
                                Log.d("결과 통신", "onResponse: 성공, 결과\n" + result.toString());



                                //like_flag=true;


                            } else {
                                Log.d("결과 통신", "onResponse: 실패"+response.toString());
                                Gson gson = new GsonBuilder().setLenient().create();
                                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
                                Retrofit retrofit= new Retrofit.Builder()
                                        .baseUrl("http://43.202.124.240:8080/api/v1/")
                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                        .client(client)
                                        .build();
                                Retrofit_like_del service1 = retrofit.create(Retrofit_like_del.class);

                                JoinData_like joinData_like =new JoinData_like(memberId, diaryId);
                                Call<ReceiveModel_like> calll = service1.getLikeInfo(joinData_like);
                                calll.enqueue(new Callback<ReceiveModel_like>() {
                                    @Override
                                    public void onResponse(Call<ReceiveModel_like> calll, retrofit2.Response<ReceiveModel_like> response) {
                                        if (response.isSuccessful()) {
                                            // 정상적으로 통신이 성공된 경우
                                            ReceiveModel_like result = response.body();
                                            Log.d("결과 통신", "onResponse: 성공, 결과\n" + result.toString());





                                        } else {
                                            Log.d("결과 통신", "onResponse: 실패"+response.toString());

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ReceiveModel_like> calll, Throwable t) {
                                        Log.d("통신", "onFailure" + t.getMessage());
                                        Toast.makeText(Diary_resultActivity.this, "당근을 취소했어요", Toast.LENGTH_SHORT).show();

                                        //tv_like.setText(Integer.parseInt(tv_like.getText().toString())-1);
                                        tv_like.setTextColor(Color.rgb(118, 82, 4));
                                        tv_like.setTypeface(null, Typeface.NORMAL);
                                        tv_like.setText((Integer.parseInt(tv_like.getText().toString())-1)+"");

                                    }


                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<ReceiveModel_like> call, Throwable t) {
                            Log.d("통신", "onFailure" + t.getMessage());
                            Toast.makeText(Diary_resultActivity.this, "해당 다이어리에 당근을 줬어요!", Toast.LENGTH_SHORT).show();
                            //tv_like.setText(Integer.parseInt(tv_like.getText().toString())+1);
                            tv_like.setTextColor(Color.rgb(255, 136, 29));
                            tv_like.setTypeface(null, Typeface.BOLD);
                            tv_like.setText((Integer.parseInt(tv_like.getText().toString())+1)+"");
                        }


                    });




            }

        });


    }
    private void emotion_analyses(String sentence){
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.show();

        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://43.200.236.149:5000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        Retrofit_emotion_analyses service1 = retrofit.create(Retrofit_emotion_analyses.class);

        Call<ReceiveModel_emotion_analyses> call = service1.getEmotionAnalyses(sentence);
        call.enqueue(new Callback<ReceiveModel_emotion_analyses>() {
            @Override
            public void onResponse(Call<ReceiveModel_emotion_analyses> call, retrofit2.Response<ReceiveModel_emotion_analyses> response) {
                if (response.isSuccessful()) {
                    // 정상적으로 통신이 성공된 경우
                    ReceiveModel_emotion_analyses result = response.body();
                    Log.d("감정분석 통신", "onResponse: 성공, 결과\n" + result.getMax_emotion());

                    String max_emotion=result.getMax_emotion();
                    emotion_analyses_data emotion_data=result.getEmotion_percentages();

                    setData(chart, emotion_data, max_emotion);
                    getSong(max_emotion);


                } else {
                    Log.d("감정분석 통신", "onResponse: 실패"+response.toString());
                }
            }

            @Override
            public void onFailure(Call<ReceiveModel_emotion_analyses> call, Throwable t) {
                Log.d("감정분석 통신", "onFailure" + t.getMessage());
            }


        });


    }
    private void wise_bot(String comment){

        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.show();

        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://43.202.124.240:5000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        Retrofit_wise service1 = retrofit.create(Retrofit_wise.class);

        JoinData_emotion_bot joinData_emotion_bot = new JoinData_emotion_bot(comment);
        Call<ReceiveModel_wise> call = service1.getWiseInfo(joinData_emotion_bot);
        call.enqueue(new Callback<ReceiveModel_wise>() {
            @Override
            public void onResponse(Call<ReceiveModel_wise> call, retrofit2.Response<ReceiveModel_wise> response) {
                if (response.isSuccessful()) {
                    // 정상적으로 통신이 성공된 경우
                    ReceiveModel_wise result = response.body();
                    Log.d("명언봇 통신", "onResponse: 성공, 결과\n" + result.toString());

                    List<wise_data> temp=result.getWiseSayingBest3();


                    for(int i=0; i<temp.size(); i++)
                    {
                        if(temp.get(i).getRank()==1)
                            tv_slogon.setText(temp.get(i).getContent()+"    -"+temp.get(i).getName()+"-");
                    }



                } else {
                    Log.d("명언봇 통신", "onResponse: 실패"+response.toString());
                }
            }

            @Override
            public void onFailure(Call<ReceiveModel_wise> call, Throwable t) {
                Log.d("명언봇 통신", "onFailure" + t.getMessage());
            }


        });

    }

    private void emotion_bot(String comment){
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.show();

        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS).build();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://43.202.124.240:5000/")
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
                    Log.d("감정봇 통신", "onResponse: 성공, 결과\n" + result.getEmpathyBot());

                    tv_emotion_bot.setText(result.getEmpathyBot());
                    progressDialog.dismiss();



                } else {
                    Log.d("감정봇 통신", "onResponse: 실패"+response.toString());
                }
            }

            @Override
            public void onFailure(Call<ReceiveModel_emotion_bot> call, Throwable t) {
                Log.d("감정봇 통신", "onFailure" + t.getMessage());
            }


        });
    }
    private void getSong(String emotion){
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.show();

        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://43.200.236.149:5000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        Retrofit_getSong service1 = retrofit.create(Retrofit_getSong.class);

        Call<ReceiveModel_getMusic> call = service1.getMusic(emotion);
        call.enqueue(new Callback<ReceiveModel_getMusic>() {
            @Override
            public void onResponse(Call<ReceiveModel_getMusic> call, retrofit2.Response<ReceiveModel_getMusic> response) {
                if (response.isSuccessful()) {
                    // 정상적으로 통신이 성공된 경우
                    ReceiveModel_getMusic result = response.body();
                    Log.d("노래추천 통신", "onResponse: 성공, 결과\n" + result.getTitle());

                    String song=result.getTitle();
                    String singer=result.getArtist();
                    url=result.getPlay_url();

                    tv_songName.setText(song);
                    tv_singer.setText(singer);

                    //"https://www.youtube.com/watch?v=btrzs54s1Rc"
                    String id = url.substring(url.lastIndexOf("=")+1);  //맨마지막 '/'뒤에 id가있으므로 그것만 파싱해줌
                    Glide.with(getApplicationContext()).load("https://img.youtube.com/vi/"+ id+ "/" + "default.jpg").into(iv_song);


                    ll_song.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getApplicationContext().startActivity(new Intent(Intent.ACTION_VIEW)
                             .setData(Uri.parse(url)) // edit this url
                             .setPackage("com.google.android.youtube").addFlags(FLAG_ACTIVITY_NEW_TASK));
                        }
                    });



        /*



        //유튜브브
        startActivity(new Intent(Intent.ACTION_VIEW)
                .setData(Uri.parse(url)) // edit this url
                .setPackage("com.google.android.youtube"));*/


                } else {
                    Log.d("노래추천 통신", "onResponse: 실패"+response.toString());
                }
            }

            @Override
            public void onFailure(Call<ReceiveModel_getMusic> call, Throwable t) {
                Log.d("노래추천 통신", "onFailure" + t.getMessage());
            }


        });


        //api
        /*String url=;
        String song=;
        String singer=;

        tv_songName.setText(song);
        tv_singer.setText(song);

        //유튜브브
        startActivity(new Intent(Intent.ACTION_VIEW)
                .setData(Uri.parse(url)) // edit this url
                .setPackage("com.google.android.youtube"));*/
    }
    private void initBarChart(BarChart chart) {
        // 차트 회색 배경 설정 (default = false)
        chart.setDrawGridBackground(false);
        // 막대 그림자 설정 (default = false)
        chart.setDrawBarShadow(false);
        // 차트 테두리 설정 (default = false)
        chart.setDrawBorders(false);
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);  // 차트 범례 설정(legend object chart)


        // X, Y 바의 애니메이션 효과
        chart.animateY(1000);
        chart.animateX(1000);

        // 바텀 좌표 값
        XAxis xAxis = chart.getXAxis();
        // x축 위치 설정
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 그리드 선 수평 거리 설정
        xAxis.setTextSize(10);

// 레이블 텍스트 색
        xAxis.setTextColor(Color.BLACK);
// 축 색
        xAxis.setAxisLineColor(Color.BLACK);
// 그래프 뒷 배경의 그리드 표시하지 않기
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
// 레이블 간격 : 1로 하면 1단위로 레이블 생김
// xAxis.setSpaceMax(1f);
// xAxis.setSpaceMin(1f);
// 축을 숫자가 아니라 날짜로 표시 🤪 이 부분은 많이 헤맸는데... 추후 자세한 설명 글을 작성할 예정임!
// 축 레이블 표시 간격 : 2로 하면 2칸마다 레이블 표시
// xAxis.setGranularity(2f);
// xAxis.setGranularityEnabled(true);

        YAxis yAxis;
        yAxis = chart.getAxisLeft();
        chart.getAxisRight().setEnabled(false);
        xAxis.setTextSize(10);
        yAxis.setTextSize(13);
        yAxis.setTextColor(Color.BLACK);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawGridLines(false);
        yAxis.setAxisMaximum(100);
        yAxis.setAxisMinimum(0);
// yAxis.setSpaceMax(0.2f);
// yAxis.setSpaceMin(0.2f);
    }
    private void setData(BarChart chart, emotion_analyses_data emotion_data, String max_emotion)
    {
        initBarChart(chart);
        chart.setScaleEnabled(false);

        ArrayList<BarEntry> entry_chart1 = new ArrayList<>(); // 데이터를 담을 Arraylist
        ArrayList<String> xVals = new ArrayList<String>(); // 변환할 String 형태 x축 값
        List<Integer> color;

        xVals.clear(); // x축 배열 모든 값 제거
        entry_chart1.clear();



        //기, 당, 분, 불, 상, 슬
        chartData=new BarData();
        //감정 분석 api
        entry_chart1.add(new BarEntry(0, emotion_data.get기쁨())); //entry_chart1에 좌표 데이터를 담는다.
        entry_chart1.add(new BarEntry(1, emotion_data.get당황()));
        entry_chart1.add(new BarEntry(2, emotion_data.get분노()));
        entry_chart1.add(new BarEntry(3, emotion_data.get불안()));
        entry_chart1.add(new BarEntry(4, emotion_data.get상처()));
        entry_chart1.add(new BarEntry(5, emotion_data.get슬픔()));

        xVals.add("기쁨");
        xVals.add("당황");
        xVals.add("분노");
        xVals.add("불안");
        xVals.add("상처");
        xVals.add("슬픔");


        BarDataSet barDataSet = new BarDataSet(entry_chart1, "bardataset"); // 데이터가 담긴 Arraylist 를 BarDataSet 으로 변환한다.



        chartData.addDataSet(barDataSet); // 해당 BarDataSet 을 적용될 차트에 들어갈 DataSet 에 넣는다.

        barDataSet.setValueTextSize(14);


        //기, 당, 분, 불, 상, 슬

        switch(max_emotion)
        {
            case "기쁨":
                //35, 31, 32
                barDataSet.setColors(Color.rgb(254, 241, 171), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181));
                color = new ArrayList<Integer>(Arrays.asList(Color.rgb(191, 138, 2), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32)));
                barDataSet.setValueTextColors(color);
                break;
            case "당황":
                barDataSet.setColors(Color.rgb(230, 204, 181), Color.rgb(254, 241, 171), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181));
                color = new ArrayList<Integer>(Arrays.asList(Color.rgb(35, 31, 32), Color.rgb(191, 138, 2), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32)));
                barDataSet.setValueTextColors(color);
                break;
            case "분노":
                barDataSet.setColors(Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(254, 241, 171), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181));
                color = new ArrayList<Integer>(Arrays.asList(Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(191, 138, 2), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32)));
                barDataSet.setValueTextColors(color);
                break;
            case "불안":
                barDataSet.setColors(Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(254, 241, 171), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181));
                color = new ArrayList<Integer>(Arrays.asList(Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(191, 138, 2), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32)));
                barDataSet.setValueTextColors(color);
                break;
            case "상처":
                barDataSet.setColors(Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(254, 241, 171), Color.rgb(230, 204, 181));
                color = new ArrayList<Integer>(Arrays.asList(Color.rgb(35, 31, 32),  Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(191, 138, 2),Color.rgb(35, 31, 32)));
                barDataSet.setValueTextColors(color);
                break;
            case "슬픔":
                barDataSet.setColors(Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(230, 204, 181), Color.rgb(254, 241, 171));
                color = new ArrayList<Integer>(Arrays.asList(Color.rgb(35, 31, 32),  Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(35, 31, 32), Color.rgb(191, 138, 2)));
                barDataSet.setValueTextColors(color);
                break;

        }

        //102, 78, 39
        //254, 241, 171

        //barDataSet.setColor(Color.parseColor("#E6CCB5"));
        chart.setData(chartData); // 차트에 위의 DataSet 을 넣는다.


        XAxis xAxis = chart.getXAxis(); // XAxis : x축 속성 설정하기 위해서 xAxis 객체 만들어줌
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
        chart.invalidate(); // 차트 업데이트

    }


}