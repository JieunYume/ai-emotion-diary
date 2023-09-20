package com.example.diary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Retrofit_getSong {
    @GET("get_music")
    Call<ReceiveModel_getMusic> getMusic(
            @Query("emotion") String emotion
    );
}
