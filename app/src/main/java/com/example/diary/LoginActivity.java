

package com.example.diary;

import static com.google.android.material.color.utilities.MaterialDynamicColors.error;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    private Button bt_login;
    private TextView tv_outPut;
    private EditText et_id;
    private EditText et_pw;
    private TextView tv_join;
    private TextView tv_find;
    //RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pw = findViewById(R.id.et_pw);
        tv_join = findViewById(R.id.tv_join);
        tv_find = findViewById(R.id.tv_find);
        bt_login = findViewById(R.id.bt_login);
        tv_outPut = findViewById(R.id.tv_join);

        //여기서부터터
        /*if(queue==null)
        {

            queue=Volley.newRequestQueue(this);
        }
        String url="http://43.202.124.240:8080/api/v1/auth/login";*/


        tv_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*JSONObject jObj = new JSONObject();
                try{
                    jObj.put("email", et_id.getText().toString());
                    jObj.put("password", et_pw.getText().toString());
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jObj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("test_volley", response+"");
                        }}, new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                            Log.d("test_volley", "error");
                        }

                    });

                    queue.add(jsonObjectRequest);
                }
                catch(JSONException E)
                {

                }*/


                //login();
                JSONObject jObj = new JSONObject();

                try {

                    jObj.put("email", et_id.getText().toString());
                    jObj.put("password", et_pw.getText().toString());

                    new Thread(() -> {
                        SendHttpWithMsg http = new SendHttpWithMsg();
                        String result = http.sendHttpWithMsg("http://43.202.124.240:8080/api/v1/auth/login", jObj);

                        JSONObject json = null;

//data_string은 Json형식에 준거한 Sting타입 입니다.
                        try {
                            json = new JSONObject(result);
                            String nickName = json.getString("nickName");
                            String memberId = json.getString("memberId");
                            String filePath = json.getString("fileUrl");
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            //HomeActivity homeActivity=new HomeActivity();
                            //homeActivity.setUser_info(memberId, filePath);

                            intent.putExtra("nickName", nickName);
                            intent.putExtra("filePath", filePath);
                            intent.putExtra("memberId", memberId);
                            startActivity(intent);
                            finish();
                            //login 성공하면 홈화면으로 넘어가기

                        } catch (JSONException e) {
                            e.printStackTrace();
                            //아이디나 비밀번호가 db에 없을때
                            Log.d("sucess?", "no");
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 확인해주세요!", Toast.LENGTH_SHORT).show();
                                }
                            }, 0);
                        }
                    }).start();

                } catch (JSONException e1) {

                    e1.printStackTrace();

                }


            }
        });

    }


}