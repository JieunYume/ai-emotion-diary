package com.example.diary;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CropDialog  extends Dialog {
    private ImageView bt_cancel;
    private ImageView iv_harvest;
    private TextView tv_harvest;
    private ImageView iv_crop;
    private TextView tv_crop_level;
    private TextView tv_crop_name;
    private TextView tv_comment;
    private Context context;
    private Long memberId;


    public static Object cropDialog;
    public CropDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }
    public void setId(Long memberId){
        this.memberId=memberId;
    }

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.crop_dialog);

        cropDialog=this;

        tv_harvest=findViewById(R.id.tv_harvest);
        iv_harvest=findViewById(R.id.iv_harvest);
        bt_cancel=findViewById(R.id.bt_cancel);
        iv_crop=findViewById(R.id.iv_crop);
        tv_crop_level=findViewById(R.id.tv_crop_level);
        tv_crop_name=findViewById(R.id.tv_crop_name);
        tv_comment=findViewById(R.id.tv_comment);
        Animation animation=AnimationUtils.loadAnimation(context, R.anim.rotate);
        Animation animation1=AnimationUtils.loadAnimation(context, R.anim.scale);


        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://43.202.124.240:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        Retrofit_crop_search service1 = retrofit.create(Retrofit_crop_search.class);

        Call<ReceiveModel_crop> call = service1.getCropSearchInfo(memberId);
        call.enqueue(new Callback<ReceiveModel_crop>() {
            @Override
            public void onResponse(Call<ReceiveModel_crop> call, retrofit2.Response<ReceiveModel_crop> response) {
                if (response.isSuccessful()) {
                    // 정상적으로 통신이 성공된 경우
                    ReceiveModel_crop result = response.body();
                    Log.d("농작물", "onResponse: 성공, 결과\n" + result.toString());
                    Long cropId=result.getCropId();
                    int cropLevel=(result.getGrowthStage()).intValue();
                    Log.d("cropLevel", cropLevel+"");

                    switch(cropLevel){
                        case 1:
                            iv_crop.setImageResource(R.drawable.crop_level1);
                            tv_crop_level.setText("1단계");
                            tv_crop_name.setText("씨앗");
                            tv_comment.setText("이제 막 씨앗을 뿌렸어요!\n" + "성장과정을 함꼐 지켜봐요~");
                            break;
                        case 2:
                            iv_crop.setImageResource(R.drawable.crop_level2);
                            tv_crop_level.setText("2단계");
                            tv_crop_name.setText("새싹");
                            tv_comment.setText("새싹이 무럭무럭 자라고 있어요!\n" + "커서 어떤 당근이 될까요?");
                            break;
                        case 3:
                            iv_crop.setImageResource(R.drawable.carrot_icon);
                            iv_crop.startAnimation(animation);
                            tv_crop_level.setText("3단계");
                            tv_crop_name.setText("당근");
                            tv_comment.setText("당근이 자랐어요\n" + "어서 수확해요!");
                            iv_harvest.setVisibility(View.VISIBLE);
                            tv_harvest.setVisibility(View.VISIBLE);
                            iv_harvest.startAnimation(animation1);

                            iv_crop.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Gson gson = new GsonBuilder().setLenient().create();
                                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                                    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
                                    Retrofit retrofit= new Retrofit.Builder()
                                            .baseUrl("http://43.202.124.240:8080/api/v1/")
                                            .addConverterFactory(GsonConverterFactory.create(gson))
                                            .client(client)
                                            .build();
                                    Retrofit_crop_harvest service1 = retrofit.create(Retrofit_crop_harvest.class);

                                    Call<ReceiveModel_crop> calll = service1.getCropHarvest(cropId);
                                    calll.enqueue(new Callback<ReceiveModel_crop>() {
                                        @Override
                                        public void onResponse(Call<ReceiveModel_crop> calll, retrofit2.Response<ReceiveModel_crop> response) {
                                            if (response.isSuccessful()) {
                                                // 정상적으로 통신이 성공된 경우
                                                ReceiveModel_crop result = response.body();
                                                Log.d("수확", "onResponse: 성공, 결과\n" + result.toString());

                                                //dialog 띄어줄까?
                                                Toast.makeText(context, "수확을 완료했어요!", Toast.LENGTH_SHORT).show();
                                                dismiss();

                                            } else {
                                                Log.d("수확", "onResponse: 실패"+response.toString());

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ReceiveModel_crop> calll, Throwable t) {
                                            Log.d("수확", "onFailure" + t.getMessage());

                                        }


                                    });

                                }
                            });

                            break;


                    }





                } else {
                    Log.d("농작물", "onResponse: 실패"+response.toString());

                }
            }

            @Override
            public void onFailure(Call<ReceiveModel_crop> call, Throwable t) {
                Log.d("농작물", "onFailure" + t.getMessage());

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
