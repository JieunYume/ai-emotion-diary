package com.example.diary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Retrofit_calender_group {

    @GET("diary/calendar/group/{groupId}")
    Call<ReceiveModel_calender_group> getCalendarInfo(
            @Path("groupId") Long groupId,
            @Query("year") String year,
            @Query("month") String month,
            @Query("day") String day
    );
}
