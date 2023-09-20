package com.example.diary;

import com.google.gson.annotations.SerializedName;

public class emotion_analyses_data {
    @SerializedName("기쁨")
    private Long 기쁨;

    @SerializedName("당황")
    private Long 당황;

    @SerializedName("분노")
    private Long 분노;

    @SerializedName("불안")
    private Long 불안;

    @SerializedName("상처")
    private Long 상처;

    @SerializedName("슬픔")
    private Long 슬픔;

    public Long get기쁨() {
        return 기쁨;
    }

    public Long get당황() {
        return 당황;
    }

    public Long get분노() {
        return 분노;
    }

    public Long get불안() {
        return 불안;
    }

    public Long get상처() {
        return 상처;
    }

    public Long get슬픔() {
        return 슬픔;
    }
}
