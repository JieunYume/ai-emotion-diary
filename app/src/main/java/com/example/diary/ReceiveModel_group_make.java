package com.example.diary;

import com.google.gson.annotations.SerializedName;

public class ReceiveModel_group_make {
    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("invitationCode")
    private String invitationCode;

    @Override
    public String toString() {
        return "ReceiveModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", invitationCode='" + invitationCode + '\'' +
                '}';
    }
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getInvitationCode(){
        return invitationCode;
    }
}
