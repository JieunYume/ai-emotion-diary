package com.example.diary;

import com.google.gson.annotations.SerializedName;

public class ReceiveModel_getMusic {
    @SerializedName("artist")
    private String artist;

    @SerializedName("play_url")
    private String play_url;

    @SerializedName("title")
    private String title;

    public String getArtist() {
        return artist;
    }

    public String getPlay_url() {
        return play_url;
    }

    public String getTitle() {
        return title;
    }
}
