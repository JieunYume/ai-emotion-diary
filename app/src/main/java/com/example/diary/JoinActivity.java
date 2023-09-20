package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

/*public class JoinActivity extends AppCompatActivity {

    ImageView img;
    EditText et_id;
    EditText et_pw;
    EditText et_pw_check;
    Button bt_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        et_id=findViewById(R.id.et_id);
        et_pw=findViewById(R.id.et_pw);
        img=findViewById(R.id.img);
        et_pw_check=findViewById(R.id.et_pw_check);
        bt_join=findViewById(R.id.bt_join);

        bt_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                join();
            }
        });



    }
    void join() {
        if(!(et_id.getText().toString().equals(null))&&et_pw.getText().toString().equals(et_pw_check.getText().toString()))
        {

            //이메일 인증부터 이메일 인증화면으로 넘어가기, 이메일 인증화면으로 parameter값 넘겨주기
            //나머지 값들은 parameter로 넘겨주기
            String url = "http://43.202.124.240:8080/api/v1/auth/confirm-email";

            String value="email="+et_id.getText().toString();
            Log.d("email", value);
            NetworkTask networkTask = new NetworkTask(url, value);
            networkTask.execute();

        }
        else
        {
            //같지 않다면
            //그리고 프로필 사진
        }


    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private String values;

        public NetworkTask(String url, String values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.
            //values를 api에게 전달해준다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
            Log.d("email_verify", s);
            Intent intent=new Intent(getApplicationContext(), Email_verifyActivity.class);
            intent.putExtra("authCode", s);
            startActivity(intent);

        }
    }
}*/