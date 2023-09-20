package com.example.diary;

import com.google.gson.annotations.SerializedName;

public class JoinData_like {
    @SerializedName("memberId")
    private Long memberId;

    @SerializedName("diaryId")
    private Long diaryId;

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setDiaryId(Long diaryId) {
        this.diaryId = diaryId;
    }
    public JoinData_like(Long memberId, Long diaryId) {
        this.memberId = memberId;
        this.diaryId = diaryId;
    }
}
