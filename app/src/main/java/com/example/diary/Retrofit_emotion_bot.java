package com.example.diary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Retrofit_emotion_bot {
    @POST("empathy")
    Call<ReceiveModel_emotion_bot> getBot(
            //@Field("name") String name,
            //@Field("memberId") Long memberId
            @Body JoinData_emotion_bot joinData_emotion_bot

    );
}
