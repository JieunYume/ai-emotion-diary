package com.example.diary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JoinData_make_diary {

    @SerializedName("name")
    private String name;

    @SerializedName("creatorId")
    private Long creatorId;


    public String getName(){
        return name;
    }
    public Long getCreatorId(){
        return creatorId;
    }

    public void setName(String name)
    {
        this.name=name;
    }
    public void setCreatorId(Long creatorId)
    {
        this.creatorId=creatorId;
    }
    public JoinData_make_diary(){
    }
    public JoinData_make_diary(String name, Long creatorId) {
        this.name = name;
        this.creatorId = creatorId;
    }
}
