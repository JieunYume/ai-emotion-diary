package com.example.diary;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    // 노트작성내용 // 서버로 보내는값 // 제목과 내용을 보냅니다.
    @Headers({"Content-Type:application/json", "Transfer-Encoding: chunked"})
    @POST("/api/v1/diary/calendar/my/2")
    Call<Note> saveNote(
            @Query("year") String year,
            @Query("month") String month,
            @Query("day") String day

    );

}