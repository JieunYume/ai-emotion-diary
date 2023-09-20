package com.example.diary;

import com.google.gson.annotations.SerializedName;

public class ReceiveModel_emotion_analyses {
    @SerializedName("emotion_percentages")
    private emotion_analyses_data emotion_percentages;

    @SerializedName("max_emotion")
    private String max_emotion;

    public emotion_analyses_data getEmotion_percentages() {
        return emotion_percentages;
    }

    public String getMax_emotion() {
        return max_emotion;
    }
}
