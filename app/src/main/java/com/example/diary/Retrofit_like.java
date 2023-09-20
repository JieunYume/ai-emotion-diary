package com.example.diary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Retrofit_like {
    @POST("like/")
    Call<ReceiveModel_like> getLikeInfo(
            @Body JoinData_like joinData_like

    );
}
