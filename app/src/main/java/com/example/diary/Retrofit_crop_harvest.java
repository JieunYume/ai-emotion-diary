package com.example.diary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Retrofit_crop_harvest {
    @POST("crop/harvest")
    Call<ReceiveModel_crop> getCropHarvest(
            //@Field("name") String name,
            //@Field("memberId") Long memberId
            @Query("cropId") Long cropId

    );
}
