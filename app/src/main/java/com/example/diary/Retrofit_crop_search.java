package com.example.diary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Retrofit_crop_search {
    @GET("crop/")
    Call<ReceiveModel_crop> getCropSearchInfo(
            //@Field("name") String name,
            //@Field("memberId") Long memberId
            @Query("memberId") Long memberId

    );
}
