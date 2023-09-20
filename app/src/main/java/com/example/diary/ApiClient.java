package com.example.diary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 공통으로 사용하는 APICLIENT // 레트로핏
/*public class ApiClient {

    private static ApiClient instance=null;
    private static ApiInterface apiInterface;
    // BaseUrl등록
    private static final String BASE_URL ="http://43.202.124.240:8080/";


    private ApiClient()
    {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

            //로그를 보기 위한 Interceptor
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client) //로그 기능 추가
                    .build();

            apiInterface=retrofit.create(ApiInterface.class);




    }
    public static ApiClient getInstance(){
        if(instance==null)
        {
            instance=new ApiClient();
        }
        return instance;
    }
    public static ApiInterface getRetrofitInterface()
    {
        return apiInterface;
    }
}*/
public class ApiClient {
    private static Retrofit retrofit;
    // BaseUrl등록
    private static final String BASE_URL = "http://43.202.124.240:8080/";

    public static Retrofit getApiClient()
    {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client) //로그 기능 추가
                    .build();
        }

        return retrofit;
    }
}