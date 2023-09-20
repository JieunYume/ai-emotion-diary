package com.example.diary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class group_search_data {

    @SerializedName("memberId")
    private Long memberId;

    @SerializedName("nickName")
    private String nickName;

    @SerializedName("imagePath")
    private String imagePath;

    public Long getMemberId() {
        return memberId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getNickName() {
        return nickName;
    }
}
