package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Email_verifyActivity extends AppCompatActivity {

    private EditText et_authCode;
    private Button bt_join;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verify);

        et_authCode=findViewById(R.id.et_authCode);
        bt_join=findViewById(R.id.bt_join);

        Intent intent=getIntent();
        String authCode=intent.getStringExtra("authCode");

        bt_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(authCode.equals(et_authCode.getText().toString()))
                {
                    //회원가입 api
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "인증코드를 다시 확인해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}