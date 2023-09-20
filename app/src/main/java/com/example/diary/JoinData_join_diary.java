package com.example.diary;

import com.google.gson.annotations.SerializedName;

public class JoinData_join_diary {

    @SerializedName("memberId")
    private Long memberId;

    @SerializedName("invitationCode")
    private String invitationCode;


    public Long getMemberId(){
        return memberId;
    }
    public String getInvitationCode(){
        return invitationCode;
    }

    public void setMemberId(Long memberId)
    {
        this.memberId=memberId;
    }
    public void setInvitationCode(String invitationCode)
    {
        this.invitationCode=invitationCode;
    }

    public JoinData_join_diary(Long memberId, String invitationCode) {
        this.memberId = memberId;
        this.invitationCode = invitationCode;
    }
}
