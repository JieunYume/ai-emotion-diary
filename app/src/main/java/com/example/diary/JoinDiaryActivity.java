package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JoinDiaryActivity extends AppCompatActivity {

    private LinearLayout bt_cancel;
    private EditText et_code;
    private Button bt_join;
    private Long memberIdL;
    private String memberId;
    private String nickName;
    private String filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_diary);

        bt_cancel=findViewById(R.id.bt_cancel);
        et_code=findViewById(R.id.et_code);
        bt_join=findViewById(R.id.bt_join);
        Intent intent=getIntent();
        nickName=intent.getStringExtra("nickName");
        filePath=intent.getStringExtra("filePath");
        memberId=intent.getStringExtra("memberId");
        memberIdL=Long.parseLong(memberId);


        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
       bt_join.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               join_group();

               //memberId랑 invitationCode json 필요

           }
       });
    }
    void join_group() {
        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://43.202.124.240:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        Retrofit_join_diary service1 = retrofit.create(Retrofit_join_diary.class);
        JoinData_join_diary joinData_join_diary =new JoinData_join_diary(memberIdL, et_code.getText().toString());

        Call<ReceiveModel_group_make> call = service1.getJoinDiaryInfo(joinData_join_diary);
        call.enqueue(new Callback<ReceiveModel_group_make>() {
            @Override
            public void onResponse(Call<ReceiveModel_group_make> call, retrofit2.Response<ReceiveModel_group_make> response) {
                if (response.isSuccessful()) {
                    // 정상적으로 통신이 성공된 경우
                    ReceiveModel_group_make result = response.body();
                    Log.d("조인통신", "onResponse: 성공, 결과\n" + result.toString());
                    Toast.makeText(getApplicationContext(), " 그룹에 참여했어요!", Toast.LENGTH_SHORT).show();
                    //intent로 홈
                    Intent intent=new Intent(JoinDiaryActivity.this, MainActivity.class);
                    intent.putExtra("memberId", memberId);
                    intent.putExtra("nickName", nickName);
                    intent.putExtra("filePath", filePath);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d("조인통신", "onResponse: 실패"+response.toString());
                    Toast.makeText(getApplicationContext(), "초대코드를 다시 확인해주세요!", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<ReceiveModel_group_make> call, Throwable t) {
                Log.d("그룹통신", "onFailure" + t.getMessage());
            }


        });

    }
}