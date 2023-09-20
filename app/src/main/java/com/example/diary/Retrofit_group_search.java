package com.example.diary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Retrofit_group_search {
    @GET("group/")
    Call<ReceiveModel_group_search> getGroupSearchInfo(
            @Query("memberId") Long memberId
            //@Body JoinData_make_diary joinData_make_diary

    );
}
