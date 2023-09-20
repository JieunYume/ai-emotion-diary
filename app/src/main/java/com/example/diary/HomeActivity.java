package com.example.diary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.diary.diaryList.diary_listAdapter;
import com.example.diary.diaryList.diary_listItem;
import com.example.diary.groupList.group_listAdapter;
import com.example.diary.groupList.group_listItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//프로필 테두리
public class HomeActivity extends Fragment {

    private View view;
    private Button bt_group_private;
    private RecyclerView list_group;
    private MaterialCalendarView calendarView;
    private TextView tv_date;
    private TextView tv_my;
    private RecyclerView rv_diary;
    private CircleImageView iv_my;
    private CircleImageView iv_all;
    private RecyclerView rv_group;
    private ImageView bt_add;
    Bitmap bmImg;
    public static String imgUrl;
    public static String nickName;
    public static Long memberId;
    RequestQueue queue;
    //
    //private diary_listAdapter diary_list_adapter=null;
    private LinearLayout ll_group;
    private LinearLayout ll_notThing;
    private int group_flag=0;
    private int calender_flag_group_position;
    private Bitmap bitmap;
    private Boolean all_flag=false;

    ArrayList<group_listItem> group_list = new ArrayList<>();
    private group_listAdapter group_adapter=null;
    private diary_listAdapter diary_adapter=null;
    private static Long group_id=1l;
    Boolean diary_exits=false;
    private int calender_flag=0; //0이면 my, 1이면 all, 2이면 그룹 다이어리 리스트를 보여줌

    public static void setUser_info(String member, String img, String nick)
    {
        memberId=Long.parseLong(member);
        imgUrl=img;
        nickName=nick;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = (ViewGroup) inflater.inflate(R.layout.acitvity_home, container, false);




        calendarView = view.findViewById(R.id.cv_calendar);
        tv_date = view.findViewById(R.id.tv_date);
        iv_my = view.findViewById(R.id.iv_my);
        iv_all = view.findViewById(R.id.iv_all);
        rv_group = view.findViewById(R.id.rv_group);
        ll_notThing=view.findViewById(R.id.ll_notThing);
        tv_my = view.findViewById(R.id.tv_my);
        ll_group = view.findViewById(R.id.ll_group);
        rv_diary = view.findViewById(R.id.rv_diary);

        MaterialCalendarView materialCalendarView = view.findViewById(R.id.cv_calendar);
        materialCalendarView.setSelectedDate(CalendarDay.today());
        tv_date = view.findViewById(R.id.tv_date);
        group_list.add(new group_listItem(2L, "3", "ee"));
        group_adapter=new group_listAdapter(view, getContext(),group_list);


        Glide.with(getContext()).load(imgUrl).into(iv_my);

        /*try {
            URL url = new URL("https://mysite/images/profile.png");


            try{
                URLConnection conn = url.openConnection();
                conn.connect();

                InputStreaem stream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);

                imageView.setImageBitmap(bitmap);
            }

        }catch(MalformedURLException e)
        {

        }*/

        if(queue==null)
        {

            queue= Volley.newRequestQueue(view.getContext());
        }

        //task = new back();
        //task.execute(imgUrl);
       //프로필 사진 처리

        iv_my.setBorderColor(Color.rgb(255, 136, 29));      //처음에는 내 것만 나오게, 그룹 리스트에서 내 프로필 사진이 선택된 것처럼 테두리효과
        iv_my.setBorderWidth(6);
        tv_my.setText(nickName);


        //캘린더 뷰에 아이콘 표시하기, 날짜 전달해서, 아이콘 표시하도록
        materialCalendarView.addDecorator(new Calender_icon(Color.RED, Collections.singleton(CalendarDay.today())));
        CalendarDay today = CalendarDay.today();
        Log.d("testt", today.getYear() + "년 " + (today.getMonth() + 1) + "월 " + today.getDay() + "일");
        tv_date.setText(today.getYear() + "년 " + (today.getMonth() + 1) + "월 " + today.getDay() + "일");

        //calendar_private();



        //그룹 리스트 생성
        group_search();
        calendar_my();



        ArrayList<group_listItem> group_list = new ArrayList<>();

        //group_id=




        //new DrawUrlImageTask((CircleImageView) iv_all)
                      //.execute("http://localhost:8080/image/sample.png");





        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {

            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                tv_date.setText(date.getYear() + "년 " + (date.getMonth() + 1) + "월 " + date.getDay() + "일");

                //ArrayList<diary_listItem> diary_list=calendar_group();
                //Log.d("diary_list_size()", diary_list.size()+"");


                switch(calender_flag){
                    case 0:     //나
                        calendar_my();
                        break;
                    case 1:     //모두
                        calendar_group();
                        break;
                    case 3:
                        calender_group_each(calender_flag_group_position);
                        break;




                }

                //그룹 눌렸을 때
                group_adapter.setOnItemClickListener(new group_listAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        calender_flag=3;
                        calender_flag_group_position=position;

                       iv_my.setBorderWidth(0);
                       iv_all.setBorderWidth(0);

                       //group_search();
                       calender_group_each(position);



                    }
                });
            }
        });

        iv_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //캘린더에 맞는 내 데이터가 나오도록
                //memberId, year, month


                calender_flag=0;
                calendar_my();

                iv_my.setBorderWidth(6);
                iv_my.setBorderColor(Color.rgb(255, 136, 29));


                iv_all.setBorderWidth(0);
                group_search();



                /*try{
                    jObj.put("memberId", mem);
                    jObj.put("year", "2023");
                    jObj.put("month", "9");

                    Log.d("cause_jobj",jObj+"");
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("test_volley", response+"");
                            Toast.makeText(getContext(), response+"", Toast.LENGTH_SHORT);
                        }}, new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                            Log.d("test_volley", "error");
                            Toast.makeText(getContext(), "error"+"", Toast.LENGTH_SHORT);
                        }

                    });

                    queue.add(jsonObjectRequest);
                }
                catch(JSONException E)
                {

                }*/

                /*try{
                    jObj.put("memberId", "2");
                    jObj.put("year", "9");
                    jObj.put("month", "2023");


                    new Thread(() -> {
                        SendHttpWithMsg_get http=new SendHttpWithMsg_get();
                        String result=http.sendHttpWithMsg_get("http://43.202.124.240:8080/api/v1/diary/calendar", jObj);

                        JSONObject json = null;

                        try{
                            //id, name, invitationCode 받아옴
                            json = new JSONObject(result);
                            Log.d("calender_diary", json+"");

                            //String bigDiaryId=json.getString("id");        //아마 다이어리 아이디
                            //String diaryName=json.getString("name");   //다이어리 이름
                            //String invitationCode=json.getString("invitationCode");  //초대코드

                            //login 성공하면 홈화면으로 넘어가기

                        }
                        catch(JSONException e)
                        {
                            e.printStackTrace();
                            //아이디나 비밀번호가 db에 없을때
                            Log.d("calender_dairy_sucess?", "no");

                        }
                    }).start();
                }
                catch(JSONException e)
                {

                }*/
            }
        });
        iv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                all_flag=!all_flag;

                if(all_flag)
                {
                    ll_group.setVisibility(View.VISIBLE);
                }
                else
                    ll_group.setVisibility(View.INVISIBLE);
                calender_flag=1;
                if(group_flag==1)
                    ll_group.setVisibility(View.VISIBLE);
                else
                {
                    ll_group.setVisibility(View.VISIBLE);
                    group_list.add(new group_listItem(null, null,null)); //플러스 이미지

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


                    //밑에꺼가 지금 우리가 해야할꺼
                    group_adapter=new group_listAdapter(view, getContext(),group_list);


                    rv_group.setLayoutManager(linearLayoutManager);
                    //list_adapter=new HomeTimelineAdapter(listItem);
                    //가로
                    rv_group.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
                    rv_group.setAdapter(group_adapter);
                    group_adapter.notifyDataSetChanged();
                }

                calendar_group();

                iv_all.setBorderWidth(6);
                iv_all.setBorderColor(Color.rgb(255, 136, 29));

                iv_my.setBorderWidth(0);

                group_adapter.setOnItemClickListener(new group_listAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        calender_flag=3;
                        calender_flag_group_position=position;

                        iv_my.setBorderWidth(0);
                        iv_all.setBorderWidth(0);

                        calender_group_each(position);



                    }
                });


            }
        });

        //날짜에 맞는 데이터를 가지고 와서 밑에 일정 표시
        return view;
    }

    void calender_group_each(int position){

        diary_exits=false;
        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://43.202.124.240:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        Retrofit_calender_group service1 = retrofit.create(Retrofit_calender_group.class);

        String txt_date=tv_date.getText().toString().replace("년", "-");
        txt_date=txt_date.replace(" ", "");
        txt_date=txt_date.replace("월", "-");
        txt_date=txt_date.replace("일", "");
        ArrayList<diary_listItem> diary_list = new ArrayList<>();
        Call<ReceiveModel_calender_group> call = service1.getCalendarInfo(group_id, txt_date.split("-")[0], txt_date.split("-")[1], txt_date.split("-")[2]);
        call.enqueue(new Callback<ReceiveModel_calender_group>() {
            @Override
            public void onResponse(Call<ReceiveModel_calender_group> call, retrofit2.Response<ReceiveModel_calender_group> response) {
                if (response.isSuccessful()) {
                    // 정상적으로 통신이 성공된 경우
                    ReceiveModel_calender_group result = response.body();
                    Log.d("그룹캘린더통신_캘린더안", "onResponse: 성공, 결과\n" + result.toString());


                    List<calender_group_data> calender_group_list=result.getDiaryList();


                    //String create_date, String like_count, String thing, String impression

                    if(result.getDiaryList().size()>0)
                    {
                        for(int i=0; i<calender_group_list.size(); i++) {
                            diary_list.add(new diary_listItem(calender_group_list.get(i).getMemberId(), calender_group_list.get(i).getMember_id(), calender_group_list.get(i).getMood_emoji_name(), calender_group_list.get(i).getDiary_id(), calender_group_list.get(i).getCreate_date(), calender_group_list.get(i).getLike_count(), calender_group_list.get(i).getThing())); //플러스 이미지

                        }
                        ArrayList<diary_listItem> diary_list_each = new ArrayList<diary_listItem>();

                        for(int j=0; j<diary_list.size(); j++)
                        {
                            if(diary_list.get(j).getMember_id().equals(group_adapter.getItem(position).getNickName()))
                            {
                                diary_list_each.add(new diary_listItem(calender_group_list.get(j).getMemberId(), diary_list.get(j).getMember_id(), diary_list.get(j).getMood_emoji_name(), diary_list.get(j).getDiary_id(), diary_list.get(j).getCreate_date(), diary_list.get(j).getLike_count(), diary_list.get(j).getThing()));

                                //Log.d("diary_list_each", diary_list_each.get(j).getThing());
                                diary_exits=true;
                            }
                        }
                        if(diary_exits)
                        {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


                            //밑에꺼가 지금 우리가 해야할꺼
                            ll_notThing.setVisibility(View.GONE);
                            rv_diary.setVisibility(View.VISIBLE);
                            diary_adapter=new diary_listAdapter(view, getContext(),diary_list_each);
                            rv_diary.setLayoutManager(linearLayoutManager);
                            //list_adapter=new HomeTimelineAdapter(listItem);
                            //가로
                            rv_diary.setAdapter(diary_adapter);
                            diary_adapter.notifyDataSetChanged();
                        }
                        else
                        {
                            ll_notThing.setVisibility(View.VISIBLE);
                            rv_diary.setVisibility(View.GONE);
                        }


                    }
                    else
                    {
                        Log.d("hey?", "hey");
                        ll_notThing.setVisibility(View.VISIBLE);
                        rv_diary.setVisibility(View.GONE);
                    }



                } else {
                    Log.d("그룹캘린더통신", "onResponse: 실패"+response.toString());

                }
            }

            @Override
            public void onFailure(Call<ReceiveModel_calender_group> call, Throwable t) {
                //일기가 없을 때
                Log.d("그룹캘린더통신", "onFailure" + t.getMessage());

            }


        });





    }
    public void calendar_group() {
        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://43.202.124.240:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        Retrofit_calender_group service1 = retrofit.create(Retrofit_calender_group.class);

        String txt_date=tv_date.getText().toString().replace("년", "-");
        txt_date=txt_date.replace(" ", "");
        txt_date=txt_date.replace("월", "-");
        txt_date=txt_date.replace("일", "");
        ArrayList<diary_listItem> diary_list = new ArrayList<>();
        Call<ReceiveModel_calender_group> call = service1.getCalendarInfo(group_id, txt_date.split("-")[0], txt_date.split("-")[1], txt_date.split("-")[2]);
        call.enqueue(new Callback<ReceiveModel_calender_group>() {
            @Override
            public void onResponse(Call<ReceiveModel_calender_group> call, retrofit2.Response<ReceiveModel_calender_group> response) {
                if (response.isSuccessful()) {
                    // 정상적으로 통신이 성공된 경우
                    ReceiveModel_calender_group result = response.body();
                    Log.d("그룹캘린더통신", "onResponse: 성공, 결과\n" + result.toString());


                    List<calender_group_data> calender_group_list=result.getDiaryList();


                    //String create_date, String like_count, String thing, String impression

                    if(result.getDiaryList().size()>0)
                    {
                        for(int i=0; i<calender_group_list.size(); i++)
                        {
                            diary_list.add(new diary_listItem(calender_group_list.get(i).getMemberId(),calender_group_list.get(i).getMember_id(), calender_group_list.get(i).getMood_emoji_name(),calender_group_list.get(i).getDiary_id(), calender_group_list.get(i).getCreate_date(), calender_group_list.get(i).getLike_count(), calender_group_list.get(i).getThing())); //플러스 이미지
                        }

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


                        //밑에꺼가 지금 우리가 해야할꺼
                        ll_notThing.setVisibility(View.GONE);
                        rv_diary.setVisibility(View.VISIBLE);
                        diary_adapter=new diary_listAdapter(view, getContext(),diary_list);


                        rv_diary.setLayoutManager(linearLayoutManager);
                        //list_adapter=new HomeTimelineAdapter(listItem);
                        //가로
                        rv_diary.setAdapter(diary_adapter);
                        diary_adapter.notifyDataSetChanged();
                    }
                    else
                    {
                        Log.d("hey?", "hey");
                        ll_notThing.setVisibility(View.VISIBLE);
                        rv_diary.setVisibility(View.GONE);
                    }



                } else {
                    Log.d("그룹캘린더통신", "onResponse: 실패"+response.toString());

                }
            }

            @Override
            public void onFailure(Call<ReceiveModel_calender_group> call, Throwable t) {
                //일기가 없을 때
                Log.d("그룹캘린더통신", "onFailure" + t.getMessage());

            }


        });

    }
    void calendar_my() {
        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://43.202.124.240:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        RetrofitService service1 = retrofit.create(RetrofitService.class);

        String txt_date=tv_date.getText().toString().replace("년", "-");
        txt_date=txt_date.replace(" ", "");
        txt_date=txt_date.replace("월", "-");
        txt_date=txt_date.replace("일", "");
        Call<ReceiveModel> call = service1.getCalendarInfo(memberId, txt_date.split("-")[0], txt_date.split("-")[1], txt_date.split("-")[2]);
        call.enqueue(new Callback<ReceiveModel>() {
            @Override
            public void onResponse(Call<ReceiveModel> call, retrofit2.Response<ReceiveModel> response) {
                if (response.isSuccessful()) {
                    // 정상적으로 통신이 성공된 경우
                    ReceiveModel result = response.body();
                    Log.d("통신", "onResponse: 성공, 결과\n" + result.toString());


                    ArrayList<diary_listItem> diary_list = new ArrayList<>();
                    //String create_date, String like_count, String thing, String impression
                    diary_list.add(new diary_listItem(memberId, result.getNickName(), result.getMoodEmojiName(),result.getDiaryId(), result.getCreateDate(), result.getLikeCount(), result.getThing())); //플러스 이미지

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


                    //밑에꺼가 지금 우리가 해야할꺼
                    ll_notThing.setVisibility(View.GONE);
                    rv_diary.setVisibility(View.VISIBLE);
                    diary_adapter=new diary_listAdapter(view, getContext(),diary_list);


                    rv_diary.setLayoutManager(linearLayoutManager);
                    //list_adapter=new HomeTimelineAdapter(listItem);
                    //가로
                    rv_diary.setAdapter(diary_adapter);
                    diary_adapter.notifyDataSetChanged();

                } else {
                    Log.d("통신", "onResponse: 실패"+response.toString());
                }
            }

            @Override
            public void onFailure(Call<ReceiveModel> call, Throwable t) {
                //일기가 없을 때
                Log.d("통신", "onFailure" + t.getMessage());
                ll_notThing.setVisibility(View.VISIBLE);
                rv_diary.setVisibility(View.GONE);
            }


        });

    }
    void calendar_private() {
        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://43.202.124.240:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        Retrofit_calender_private service1 = retrofit.create(Retrofit_calender_private.class);

        Call<ReceiveModel_calender_private> call = service1.getCalenderPrivateInfo(memberId, 2023, 9);
        call.enqueue(new Callback<ReceiveModel_calender_private>() {
            @Override
            public void onResponse(Call<ReceiveModel_calender_private> call, retrofit2.Response<ReceiveModel_calender_private> response) {
                if (response.isSuccessful()) {
                    // 정상적으로 통신이 성공된 경우
                    ReceiveModel_calender_private result = response.body();
                    Log.d("캘린더 프라이벳통신", "onResponse: 성공, 결과\n" + result.toString());

                    List<calender_private_data> diaryList=result.getDiaryList();




                } else {
                    Log.d("캘린더 프라이벳통신", "onResponse: 실패"+response.toString());
                }
            }

            @Override
            public void onFailure(Call<ReceiveModel_calender_private> call, Throwable t) {
                Log.d("통신", "onFailure" + t.getMessage());
            }


        });

    }
    void group_search() {
        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://43.202.124.240:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        Retrofit_group_search service1 = retrofit.create(Retrofit_group_search.class);

        Call<ReceiveModel_group_search> call = service1.getGroupSearchInfo(memberId);
        call.enqueue(new Callback<ReceiveModel_group_search>() {
            @Override
            public void onResponse(Call<ReceiveModel_group_search> call, retrofit2.Response<ReceiveModel_group_search> response) {
                if (response.isSuccessful()) {
                    // 정상적으로 통신이 성공된 경우
                    ReceiveModel_group_search result = response.body();
                    Log.d("그룹서치통신", "onResponse: 성공, 결과\n" + result.toString());
                    Long groupCount=result.getMemberNum();
                    List<group_search_data> group_search=result.getGroupFindMemberList();
                    ArrayList<group_listItem> group_list = new ArrayList<>();

                    //group_id=

                    if(groupCount>1)
                        group_flag=1;

                    for(int i=0; i<group_search.size(); i++)
                    {
                        Log.d("imagepath", group_search.get(i).getImagePath());
                        group_list.add(new group_listItem(group_search.get(i).getMemberId(),group_search.get(i).getNickName(), group_search.get(i).getImagePath()));

                    }
                    group_list.add(new group_listItem(null, null,null)); //플러스 이미지

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


                    //밑에꺼가 지금 우리가 해야할꺼
                    group_adapter=new group_listAdapter(view, getContext(),group_list);


                    rv_group.setLayoutManager(linearLayoutManager);
                    //list_adapter=new HomeTimelineAdapter(listItem);
                    //가로
                    rv_group.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
                    rv_group.setAdapter(group_adapter);
                    group_adapter.notifyDataSetChanged();





                } else {
                    Log.d("그룹서치통신", "onResponse: 실패"+response.toString());
                }
            }

            @Override
            public void onFailure(Call<ReceiveModel_group_search> call, Throwable t) {
                Log.d("통신", "onFailure" + t.getMessage());
            }


        });

    }
    //이미지 함수 두개 중에 하나 선택
    public void setImageUrl(String url1, CircleImageView a){
        Thread mThread=new Thread(){
            @Override
            public void run(){
                try{
                    URL url=new URL(url1);


                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    Log.d("image", "연결성곡");
                    InputStream is=conn.getInputStream();
                    bitmap=BitmapFactory.decodeStream(is);


                }catch(MalformedURLException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        };

        mThread.start();

        try{
            mThread.join();

            a.setImageBitmap(bitmap);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public class DrawUrlImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView ivSample;

        public DrawUrlImageTask(CircleImageView ivSample) {
            this.ivSample = ivSample;
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap bitmap = null;
            InputStream in = null;

            try {
                in = new java.net.URL(url).openStream();
                Log.d("inin", in+"");
                bitmap = BitmapFactory.decodeStream(in);
            }
            catch (Exception e) {
                e.printStackTrace();
                Log.d("inerror", e+"");
            }
            finally {
                /*try {
                    //in.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }*/
            }

            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            ivSample.setImageBitmap(bitmap);
        }
    }



}