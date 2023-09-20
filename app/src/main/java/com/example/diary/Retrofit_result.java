package com.example.diary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Retrofit_result {
    @GET("diary/{diaryId}")
    Call<ReceiveModel_result> getResultInfo(
            @Path("diaryId") Long diaryId
            //@Body JoinData_make_diary joinData_make_diary

    );
}
