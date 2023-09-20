package com.example.diary;

import com.google.gson.annotations.SerializedName;

public class JoinData_emotion_bot {
    @SerializedName("userContent")
    private String userContent;

    public JoinData_emotion_bot(String userContent) {
        this.userContent = userContent;
    }
}
