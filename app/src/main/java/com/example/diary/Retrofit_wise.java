package com.example.diary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Retrofit_wise {
    @POST("wise-saying")
    Call<ReceiveModel_wise> getWiseInfo(
            @Body JoinData_emotion_bot joinData_emotion_bot

    );
}
