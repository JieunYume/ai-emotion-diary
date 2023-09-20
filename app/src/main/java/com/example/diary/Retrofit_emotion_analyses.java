package com.example.diary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Retrofit_emotion_analyses {
    @GET("predict_emotions")
    Call<ReceiveModel_emotion_analyses> getEmotionAnalyses(
            @Query("sentence") String sentence
            //@Body JoinData_make_diary joinData_make_diary

    );
}
