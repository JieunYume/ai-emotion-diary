package com.example.diary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Retrofit_join_diary {
    @POST("group/join")
    Call<ReceiveModel_group_make> getJoinDiaryInfo(
            //@Field("name") String name,
            //@Field("memberId") Long memberId
            @Body JoinData_join_diary joinData_join_diary

    );
}
