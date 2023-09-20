package com.example.diary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReceiveModel_crop {
    @SerializedName("cropId")
    private Long cropId;

    @SerializedName("growthStage")
    private Long growthStage;

    @SerializedName("cropCount")
    private Long cropCount;

    @SerializedName("nonHarverstCropCount")
    private Long nonHarverstCropCount;

    public Long getCropCount() {
        return cropCount;
    }

    public Long getCropId() {
        return cropId;
    }

    public Long getGrowthStage() {
        return growthStage;
    }

    public Long getNonHarverstCropCount() {
        return nonHarverstCropCount;
    }
}
