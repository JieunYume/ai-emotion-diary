package com.example.diary;

import com.google.gson.annotations.SerializedName;

public class result_data {
    @SerializedName("commentId")
    private Long commentId;

    @SerializedName("writerNickName")
    private String writerNickName;

    @SerializedName("content")
    private String content;

    public String getWriterNickName() {
        return writerNickName;
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }
}
