package com.example.diary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReceiveModel_calender_group {

    @SerializedName("groupCalendarDetailList")
    private List<calender_group_data> groupCalendarDetailList;

    @Override
    public String toString() {
        return "ReceiveModel_calender_private{" +
                "diaryList=" + groupCalendarDetailList +
                '\'' +
                '}';
    }

    public List<calender_group_data> getDiaryList() {
        return groupCalendarDetailList;
    }
}
