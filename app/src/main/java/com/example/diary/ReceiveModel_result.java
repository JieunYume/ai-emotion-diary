package com.example.diary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReceiveModel_result {
    @SerializedName("diaryId")
    private Long diaryId;

    @SerializedName("writerNickName")
    private String writerNickName;

    @SerializedName("moodEmoji")
    private String moodEmoji;

    @SerializedName("createDate")
    private String createDate;

    @SerializedName("updateDate")
    private String updateDate;

    @SerializedName("thing")
    private String thing;

    @SerializedName("impression")
    private String impression;

    @SerializedName("comments")
    private List<result_data> comments;

    @SerializedName("likeNum")
    private Long likeNum;

    public String getThing() {
        return thing;
    }

    public Long getDiaryId() {
        return diaryId;
    }

    public List<result_data> getComments() {
        return comments;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getImpression() {
        return impression;
    }

    public String getMoodEmoji() {
        return moodEmoji;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getWriterNickName() {
        return writerNickName;
    }

}
