package com.example.diary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Retrofit_group_make {

    @POST("group/")
    Call<ReceiveModel_group_make> getMakeGroupInfo(
            //@Field("name") String name,
            //@Field("memberId") Long memberId
            @Body JoinData_make_diary joinData_make_diary

    );
}
