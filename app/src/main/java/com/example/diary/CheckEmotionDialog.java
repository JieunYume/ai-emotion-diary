package com.example.diary;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
//쓰기 버튼 누르면 다이얼로그 띄어주는 것.main에서 버튼누르면 다이얼로그 띄어주는 부분
public class CheckEmotionDialog  extends Dialog {
    private LinearLayout bt_cancel;
    private ImageView iv_happy;
    private ImageView iv_proud;
    private ImageView iv_sad;
    private ImageView iv_angry;
    private ImageView iv_nervous;
    private ImageView iv_wound;
    private ImageView iv_embressed;
    private Context context;
    private String memberId;
    private String filePath;
    private String nickName;

    public static Object checkEmotionDialog;
    public CheckEmotionDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }
    public void setId(String memberId, String filePath, String nickName){
        this.memberId=memberId;
        this.filePath=filePath;
        this.nickName=nickName;
    }

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_emotion_dialog);

        checkEmotionDialog=this;

        bt_cancel=findViewById(R.id.bt_cancel);
        iv_happy=findViewById(R.id.iv_happy);
        iv_sad=findViewById(R.id.iv_sad);
        iv_angry=findViewById(R.id.iv_angry);
        iv_nervous=findViewById(R.id.iv_nervous);
        iv_wound=findViewById(R.id.iv_wound);
        iv_embressed=findViewById(R.id.iv_embressed);
        iv_proud=findViewById(R.id.iv_proud);

        iv_happy.setOnClickListener(new View.OnClickListener() {   //이름 변경
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(), WritingActivity.class);
                //rfid id 넘겨받아야함
                intent.putExtra("moodEmojiName", "happy");
                intent.putExtra("memberId", memberId);
                intent.putExtra("nickName", nickName);
                intent.putExtra("filePath", filePath);
                getContext().startActivity(intent);
                dismiss();

            }
        });
        iv_proud.setOnClickListener(new View.OnClickListener() {   //이름 변경
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(), WritingActivity.class);
                //rfid id 넘겨받아야함
                intent.putExtra("moodEmojiName", "proud");
                intent.putExtra("memberId", memberId);
                intent.putExtra("nickName", nickName);
                intent.putExtra("filePath", filePath);
                getContext().startActivity(intent);
                dismiss();

            }
        });
        iv_wound.setOnClickListener(new View.OnClickListener() {   //이름 변경
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(), WritingActivity.class);
                //rfid id 넘겨받아야함
                intent.putExtra("moodEmojiName", "hurt");
                intent.putExtra("memberId", memberId);
                intent.putExtra("nickName", nickName);
                intent.putExtra("filePath", filePath);
                getContext().startActivity(intent);
                dismiss();

            }
        });
        iv_sad.setOnClickListener(new View.OnClickListener() {   //이름 변경
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(), WritingActivity.class);
                //rfid id 넘겨받아야함
                intent.putExtra("moodEmojiName", "sad");
                intent.putExtra("memberId", memberId);
                intent.putExtra("nickName", nickName);
                intent.putExtra("filePath", filePath);
                getContext().startActivity(intent);
                dismiss();

            }
        });
        iv_angry.setOnClickListener(new View.OnClickListener() {   //이름 변경
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(), WritingActivity.class);
                //rfid id 넘겨받아야함
                intent.putExtra("moodEmojiName", "angry");
                intent.putExtra("memberId", memberId);
                intent.putExtra("nickName", nickName);
                intent.putExtra("filePath", filePath);
                getContext().startActivity(intent);
                dismiss();

            }
        });
        iv_nervous.setOnClickListener(new View.OnClickListener() {   //이름 변경
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(), WritingActivity.class);
                //rfid id 넘겨받아야함
                intent.putExtra("moodEmojiName", "anxiety");
                intent.putExtra("memberId", memberId);
                intent.putExtra("nickName", nickName);
                intent.putExtra("filePath", filePath);
                getContext().startActivity(intent);
                dismiss();

            }
        });
        iv_embressed.setOnClickListener(new View.OnClickListener() {   //이름 변경
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getContext(), WritingActivity.class);
                //rfid id 넘겨받아야함
                intent.putExtra("moodEmojiName", "flustered");
                intent.putExtra("memberId", memberId);
                intent.putExtra("nickName", nickName);
                intent.putExtra("filePath", filePath);
                getContext().startActivity(intent);
                dismiss();

            }
        });


        bt_cancel.setOnClickListener(new View.OnClickListener() {   //취소 버튼 다이얼로그 취소
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
