package com.example.diary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

public interface Retrofit_like_del {
    @HTTP(method = "DELETE", path = "like/", hasBody = true)
    Call<ReceiveModel_like> getLikeInfo(
            @Body JoinData_like joinData_like

    );
}
