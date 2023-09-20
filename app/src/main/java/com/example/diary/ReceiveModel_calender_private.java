package com.example.diary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReceiveModel_calender_private {

    @SerializedName("diaryList")
    private List<calender_private_data> diaryList;

    @Override
    public String toString() {
        return "ReceiveModel_calender_private{" +
                "diaryList=" + diaryList +
                '\'' +
                '}';
    }

    public List<calender_private_data> getDiaryList() {
        return diaryList;
    }
}
