package com.example.diary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReceiveModel_wise {
    @SerializedName("wiseSayingBest3")
    private List<wise_data> wiseSayingBest3;

    public List<wise_data> getWiseSayingBest3() {
        return wiseSayingBest3;
    }
}
