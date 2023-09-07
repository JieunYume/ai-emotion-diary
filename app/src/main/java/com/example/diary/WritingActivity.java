package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WritingActivity extends AppCompatActivity {

    private LinearLayout bt_check;
    private LinearLayout bt_back;
    private TextView tv_date;
    private EditText et_happen;
    private EditText et_emotion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);
        bt_check=findViewById(R.id.bt_check);
        bt_back=findViewById(R.id.bt_back);
        tv_date=findViewById(R.id.tv_date);
        et_happen=findViewById(R.id.et_happen);
        et_emotion=findViewById(R.id.et_emotion);


        long now = System.currentTimeMillis();
        Date date=new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");

        String getTime = sdf.format(date);

        tv_date.setText(getTime);
        Intent intent=getIntent();
        //String memberId=intent.getStringExtra("memberId");  //앞전 화면에서 memberId를 받아오고
        //String moodEmojiName=intent.getStringExtra("moodEmojiName");  //앞전 화면에서 moodEmojiName을 받아옴
        String memberId="1";
        String moodEmojiName="happy";
        Log.d("memberId, MoodEmoji", memberId+", "+ moodEmojiName);


        bt_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cehck


                String user_happen=et_happen.getText().toString();
                String user_emotion=et_emotion.getText().toString();
                JSONObject jObj = new JSONObject();

                if(!(user_happen.equals("")&&user_emotion.equals("")))
                {

                    //api부르고 저장하고 home화면으로 돌아가게
                    //보내야하는 데이터 memberId, moodEmojiName, thing, impression

                    try{
                        jObj.put("memberId", memberId);
                        jObj.put("moodEmojiName", moodEmojiName);
                        jObj.put("thing", user_happen);
                        jObj.put("impression", user_emotion);

                        new Thread(() -> {
                            SendHttpWithMsg http=new SendHttpWithMsg();
                            String result=http.sendHttpWithMsg("http://43.202.124.240:8080/api/v1/diary/", jObj);

                            JSONObject json = null;

                            try{
                                //diaryId, writerNickName, moodEmoji, createDate, updateDate, thing, impression, comments, likeNum
                                json = new JSONObject(result);
                                Log.d("writing_json", json+"");

                                String diaryId=json.getString("diaryId");
                                String writerNickName=json.getString("writerNickName");
                                String moodEmoji=json.getString("moodEmoji");
                                String createDate=json.getString("createDate");
                                String updateDate=json.getString("updateDate");
                                String thing=json.getString("thing");
                                String impression=json.getString("impression");
                                String comments=json.getString("comments");
                                String likeNum=json.getString("likeNum");

                                //Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                                intent.putExtra("diaryId", diaryId);
                                intent.putExtra("writerNickName", writerNickName);
                                intent.putExtra("moodEmoji", moodEmoji);
                                intent.putExtra("createDate", createDate);
                                intent.putExtra("updateDate", updateDate);
                                intent.putExtra("thing", thing);
                                intent.putExtra("impression", impression);
                                intent.putExtra("comments", comments);
                                intent.putExtra("likeNum", likeNum);
                                startActivity(intent);
                                //login 성공하면 홈화면으로 넘어가기

                            }
                            catch(JSONException e)
                            {
                                e.printStackTrace();
                                //아이디나 비밀번호가 db에 없을때
                                Log.d("writting_sucess?", "no");

                            }
                        }).start();
                    }
                    catch(JSONException e)
                    {

                    }

                }
                else
                {
                    //둘 다 null이면 저장이 안되게
                    Toast.makeText(getApplicationContext(), "일기 내용을 써주세요!", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}