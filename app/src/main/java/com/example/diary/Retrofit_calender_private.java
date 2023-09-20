package com.example.diary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Retrofit_calender_private {
    @GET("diary/calendar/{memberId}")
    Call<ReceiveModel_calender_private> getCalenderPrivateInfo(
            @Path("memberId") Long memberId,
            @Query("year") int year,
            @Query("month") int month
            //@Body JoinData_make_diary joinData_make_diary

    );
}
