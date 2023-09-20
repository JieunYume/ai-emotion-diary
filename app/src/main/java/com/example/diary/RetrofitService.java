package com.example.diary;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface RetrofitService {

    @GET("diary/calendar/detail/{memberId}")
    Call<ReceiveModel> getCalendarInfo(
            @Path("memberId") Long memberId,
            @Query("year") String year,
            @Query("month") String month,
            @Query("day") String day
    );

/*
    @GET("tag/shopping")
    Call<List<ReceiveModel>>  callshop(@Query("mapx")double mapx, @Query("mapy")double mapy);

    @GET("daily/tour")
    Call<TipModel>  calltip();


    @GET("tag/tour")
    Call<List<ReceiveModel>>  calltour(@Query("mapx")double mapx, @Query("mapy")double mapy);


    @GET("tag/enjoy")
    Call<List<ReceiveModel>>  callplay(@Query("mapx")double mapx, @Query("mapy")double mapy);


    @GET("knto/restaurant")
    Call<List<ReceiveModel>> call_res_quest(@Query("mapx")double mapx,
                                            @Query("mapy")double mapy,
                                            @Query("tag1") String tag1,
                                            @Query("tag2") String tag2,
                                            @Query("characterid")int characterid);

    @GET("knto/tour")
    Call<List<ReceiveModel>> call_tour_quest(
                                             @Query("mapx")double mapx,
                                             @Query("mapy")double mapy,
                                             @Query("characterid")int characterid);

    @GET("knto/cultural")
    Call<List<ReceiveModel>> call_cultural_quest(
                                                 @Query("mapx")double mapx,
                                                 @Query("mapy")double mapy,
                                                 @Query("characterid")int characterid);

    @GET("knto/shopping")
    Call<List<ReceiveModel>> call_shopping_quest(
                                                 @Query("mapx")double mapx,
                                                 @Query("mapy")double mapy,
                                                 @Query("characterid")int characterid);



    @GET("knto/enjoy")
    Call<List<ReceiveModel>> call_play_quest(@Body ReceiveModel resModel ,
                                            @Query("mapx")double mapx,
                                            @Query("mapy")double mapy,
                                            @Query("tag1") String tag1,
                                            @Query("characterid")int characterid);

//반환값 물어보기

    @GET("challenge/accept")
    Call<Integer> challenge_accept(@Query("characterid")int characterid,
                                   @Query("title") String title );


    @GET("challenge/drop")
    Call<Integer> challenge_drop(@Query("characterid")int characterid,
                                   @Query("title") String title );


    @GET("challenge/check")
    Call<Integer> challenge_check(@Query("title") String title);


    @GET("question/find")
    Call<Integer> login(@Query("answer_id") String answer_id);






    @POST("question/save")
    Call<Integer> Surveysubmit(
            @Query("android") String android,
            @Query("ct_tag1") String ctTag1,
            @Query("ct_tag2") String ctTag2,
            @Query("ct_tag3") String ctTag3,
            @Query("ct_tag4") String ctTag4,
            @Query("r_tag3") String rTag3,
            @Query("r_tag4") String rTag4,
            @Query("r_tag5") String rTag5,
            @Query("r_tag3_1") String rTag3_1,
            @Query("s_tag1") String sTag1,
            @Query("user_name")String name,
            @Query("start_date") String start_date,
            @Query("end_date") String end_date
    );


    @GET("push/{tokens}")
    Call<String> insertToken(@Path("tokens") String tokens);

 */

}
