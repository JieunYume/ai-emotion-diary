

package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    private Button bt_login;
    private TextView tv_outPut;
    private EditText et_id;
    private EditText et_pw;
    private TextView tv_join;
    private TextView tv_find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id=findViewById(R.id.et_id);
        et_pw=findViewById(R.id.et_pw);
        tv_join=findViewById(R.id.tv_join);
        tv_find=findViewById(R.id.tv_find);
        bt_login=findViewById(R.id.bt_login);
        tv_outPut = findViewById(R.id.tv_join);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //login();
                 JSONObject jObj = new JSONObject();

                try {

                    jObj.put("email", et_id.getText().toString());
                    jObj.put("password", et_pw.getText().toString());

                    new Thread(() -> {
                        SendHttpWithMsg http=new SendHttpWithMsg();
                        String result=http.sendHttpWithMsg("http://43.202.124.240:8080/api/v1/auth/login", jObj);

                        JSONObject json = null;

//data_string은 Json형식에 준거한 Sting타입 입니다.
                        try{
                            json = new JSONObject(result);
                            String nickName=json.getString("nickName");
                            String memberId=json.getString("memberId");
                            String filePath=json.getString("filePath");
                            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("nickName", nickName);
                            intent.putExtra("filePath", filePath);
                            intent.putExtra("memberId", memberId);
                            startActivity(intent);
                            //login 성공하면 홈화면으로 넘어가기

                        }
                        catch(JSONException e)
                        {
                            e.printStackTrace();
                            //아이디나 비밀번호가 db에 없을때
                            Log.d("sucess?", "no");
                            Handler handler=new Handler(Looper.getMainLooper());
                            handler.postDelayed(new Runnable(){@Override
                                    public void run(){
                                Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 확인해주세요!", Toast.LENGTH_SHORT).show();
                            }},0);
                        }
                    }).start();

                } catch (JSONException e1) {

                     e1.printStackTrace();

                }


            }
        });

    }

}
/*package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private EditText et_id;
    private EditText et_pw;
    private Button bt_login;
    private TextView tv_join;
    private TextView tv_find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id=findViewById(R.id.et_id);
        et_pw=findViewById(R.id.et_pw);
        bt_login=findViewById(R.id.bt_login);
        tv_join=findViewById(R.id.tv_join);
        tv_find=findViewById(R.id.tv_find);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        //회원가입 화면 넘어가기
        tv_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    // 서버와 연동하기
    void login() {
        Log.w("login","로그인 하는중");
        try {
            String id = et_id.getText().toString();
            String pw = et_pw.getText().toString();
            Log.w("앱에서 보낸값",id+", "+pw);

            CustomTask task = new CustomTask();
            String result = task.execute(id,pw).get();
            Log.w("받은값",result);

            Intent intent2 = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent2);
            finish();
        } catch (Exception e) {

        }
    }
    // 서버와 데이터가져오기

    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        // doInBackground의 매개변수 값이 여러개일 경우를 위해 배열로
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://43.202.124.240:8080/api/v1/auth/confirm-email");  // 어떤 서버에 요청할지(localhost 안됨.)
                // ex) http://123.456.789.10:8080/hello/android
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");                              //데이터를 POST 방식으로 전송합니다.
                conn.setDoOutput(true);

                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("Content-Type", "application/json; utf-8");


                // 서버에 보낼 값 포함해 요청함.
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&pw="+strings[1]; // GET방식으로 작성해 POST로 보냄 ex) "id=admin&pwd=1234";
                Log.d("test", strings[0]+" "+strings[1]);
                osw.write(sendMsg);                           // OutputStreamWriter에 담아 전송
                osw.flush();

                // jsp와 통신이 잘 되고, 서버에서 보낸 값 받음.
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();
                } else {    // 통신이 실패한 이유를 찍기위한 로그
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 서버에서 보낸 값을 리턴합니다.
            return receiveMsg;
        }
    }
}*/