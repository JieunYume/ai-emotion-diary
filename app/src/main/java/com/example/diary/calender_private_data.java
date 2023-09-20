package com.example.diary;

import com.google.gson.annotations.SerializedName;

public class calender_private_data {
    @SerializedName("diaryId")
    private Long diaryId;

    @SerializedName("date")
    private String date;

    @SerializedName("moodEmojiName")
    private String moodEmojiName;

    public Long getDiaryId() {
        return diaryId;
    }

    public String getDate() {
        return date;
    }

    public String getMoodEmojiName() {
        return moodEmojiName;
    }
}
