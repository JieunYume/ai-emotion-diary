package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MakeDiaryActivity extends AppCompatActivity {

    private LinearLayout bt_cancel;
    private TextView tv_copy;
    private TextView tv_code;
    private Button bt_send;
    ApiInterface initMyApi;;
    private ApiClient retrofitClient;
    ApiInterface apiInterface;
    RequestQueue queue;
    private Long memberIdL;
    private String name;
    private String nickName;
    private String filePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_diary);

        bt_cancel=findViewById(R.id.bt_cancel);
        tv_copy=findViewById(R.id.tv_copy);
        tv_code=findViewById(R.id.tv_code);
        bt_send=findViewById(R.id.bt_send);
        Intent intent=getIntent();
        String memberId=intent.getStringExtra("memberId");
        name=intent.getStringExtra("diaryName");
        nickName=intent.getStringExtra("nickName");
        filePath=intent.getStringExtra("filePath");
        memberIdL=Long.parseLong(memberId);

        tv_copy.setPaintFlags(tv_copy.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        make_group();

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MakeDiaryActivity.this, MainActivity.class);
                intent.putExtra("memberId", memberId);
                intent.putExtra("nickName", nickName);
                intent.putExtra("filePath", filePath);
                startActivity(intent);
                finish();

            }
        });
        tv_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("CODE", tv_code.getText().toString().trim()); //클립보드에 ID라는 이름표로 id 값을 복사하여 저장
                clipboardManager.setPrimaryClip(clipData);

                //복사가 되었다면 토스트메시지 노출
                Toast.makeText(getApplicationContext(), "코드가 복사되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        /*try{
            jObj.put("year", "2023");
            jObj.put("month", "9");
            jObj.put("day", "9");

            Log.d("cause_jobj",jObj+"");
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, jObj, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("make_diary_volley", response+"");
                    Toast.makeText(getApplicationContext(), response+"", Toast.LENGTH_SHORT);
                }}, new com.android.volley.Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("make_diary_volley", "error");
                        Toast.makeText(getApplicationContext(), "error"+"", Toast.LENGTH_SHORT);
                    }
                });

            queue.add(jsonObjectRequest);
        }
        catch(JSONException E)
        {

        }
        //name이랑 memberId json 필요
        /*try{
            jObj.put("name", name);
            jObj.put("memberId", memberId);

            new Thread(() -> {
                SendHttpWithMsg http=new SendHttpWithMsg();
                String result=http.sendHttpWithMsg("http://43.202.124.240:8080/api/v1/group/", jObj);

                JSONObject json = null;

                try{
                    //id, name, invitationCode 받아옴
                    json = new JSONObject(result);
                    Log.d("makeDiary_json", json+"");

                    String bigDiaryId=json.getString("id");        //아마 다이어리 아이디
                    String diaryName=json.getString("name");   //다이어리 이름
                    String invitationCode=json.getString("invitationCode");  //초대코드

                    HomeActivity homeActivity=new HomeActivity();

                    //Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                    Intent intent2=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    //login 성공하면 홈화면으로 넘어가기

                }
                catch(JSONException e)
                {
                    e.printStackTrace();
                    //아이디나 비밀번호가 db에 없을때
                    Log.d("makeDiary_sucess?", "no");

                }
            }).start();
        }
        catch(JSONException e)
        {

        }*/


        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }



    void make_group() {
        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://43.202.124.240:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        Retrofit_group_make service1 = retrofit.create(Retrofit_group_make.class);
        JoinData_make_diary joinDataMakediary =new JoinData_make_diary(name, memberIdL);

        Call<ReceiveModel_group_make> call = service1.getMakeGroupInfo(joinDataMakediary);
        call.enqueue(new Callback<ReceiveModel_group_make>() {
            @Override
            public void onResponse(Call<ReceiveModel_group_make> call, retrofit2.Response<ReceiveModel_group_make> response) {
                if (response.isSuccessful()) {
                    // 정상적으로 통신이 성공된 경우
                    ReceiveModel_group_make result = response.body();
                    Log.d("그룹통신", "onResponse: 성공, 결과\n" + result.toString());
                    tv_code.setText(result.getInvitationCode());
                    //intent로 홈
                } else {
                    Log.d("그룹통신", "onResponse: 실패"+response.toString());
                }
            }

            @Override
            public void onFailure(Call<ReceiveModel_group_make> call, Throwable t) {
                Log.d("그룹통신", "onFailure" + t.getMessage());
            }


        });

    }
}