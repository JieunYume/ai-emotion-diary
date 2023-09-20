package com.example.diary;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ReceiveModel {

    @SerializedName("diaryId")
    private Long diaryId;

    @SerializedName("nickName")
    private String nickName;

    @SerializedName("moodEmojiName")
    private String moodEmojiName;

    @SerializedName("createDate")
    private String createDate;

    @SerializedName("thing")
    private String thing;

    @SerializedName("likeCount")
    private Long likeCount;

    @Override
    public String toString() {
        return "ReceiveModel{" +
                "diaryId=" + diaryId +
                ", nickName='" + nickName + '\'' +
                ", moodEmojiName='" + moodEmojiName + '\'' +
                ", createDate=" + createDate +
                ", thing='" + thing + '\'' +
                ", likeCount='" + likeCount + '\'' +
                '}';
    }

    public String getMoodEmojiName() {
        return moodEmojiName;
    }

    public Long getDiaryId() {
        return diaryId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public String getThing() {
        return thing;
    }
}
