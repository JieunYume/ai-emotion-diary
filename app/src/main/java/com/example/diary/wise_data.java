package com.example.diary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class wise_data {
    @SerializedName("content")
    private String content;

    @SerializedName("name")
    private String name;

    @SerializedName("rank")
    private Long rank;

    public String getContent() {
        return content;
    }

    public Long getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }
}
