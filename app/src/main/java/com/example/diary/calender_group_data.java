package com.example.diary;

import com.google.gson.annotations.SerializedName;

public class calender_group_data {

    @SerializedName("memberId")
    private Long memberId;
    //nickName, moodEmojiName, createDate, thing, likeCount
    @SerializedName("diaryId")
    private Long diaryId;

    @SerializedName("moodEmojiName")
    private String moodEmojiName;

    @SerializedName("createDate")
    private String createDate;

    @SerializedName("thing")
    private String thing;

    @SerializedName("likeCount")
    private Long likeCount;

    @SerializedName("nickName")
    private String nickName;

    public String getMood_emoji_name(){return this.moodEmojiName;}
    public Long getDiary_id(){return this.diaryId;}
    public String getCreate_date(){return this.createDate;}
    public Long getLike_count(){return this.likeCount;}
    public String getThing(){return this.thing;}
    public String getMember_id(){return this.nickName;}

    public Long getMemberId() {
        return memberId;
    }
}
