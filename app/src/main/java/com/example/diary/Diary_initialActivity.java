package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Diary_initialActivity extends AppCompatActivity {

    private Button bt_make;
    private Button bt_join;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_initial);

        bt_make=findViewById(R.id.bt_make);
        bt_join=findViewById(R.id.bt_join);
        Intent intent=getIntent();
        String memberId=intent.getStringExtra("memberId");
        String nickName=intent.getStringExtra("nickName");
        String filePath=intent.getStringExtra("filePath");
        String diaryName="first";  //지금은 테스트용 임의로, 나중에는 사용자가 직접 입력할 수 있도록

        //일기 생성
        bt_make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MakeDiaryActivity.class);
                intent.putExtra("memberId", memberId);
                intent.putExtra("diaryName", diaryName);
                intent.putExtra("nickName", nickName);
                intent.putExtra("filePath", filePath);
                startActivity(intent);
            }
        });
        //일기 참여
        bt_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), JoinDiaryActivity.class);
                intent.putExtra("memberId", memberId);
                intent.putExtra("nickName", nickName);
                intent.putExtra("filePath", filePath);
                startActivity(intent);
            }
        });
    }
}