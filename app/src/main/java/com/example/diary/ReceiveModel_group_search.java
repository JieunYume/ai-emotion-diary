package com.example.diary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReceiveModel_group_search {
    @SerializedName("memberNum")
    private Long memberNum;

    @SerializedName("groupFindMemberList")
    private List<group_search_data> groupFindMemberList;

    @Override
    public String toString() {
        return "ReceiveModel_group_search{" +
                "memberNum=" + memberNum +
                ", groupFindMemberList='" + groupFindMemberList + '\'' +
                '\'' +
                '}';
    }
    public Long getMemberNum(){
        return memberNum;
    }
    public List<group_search_data> getGroupFindMemberList(){

        return groupFindMemberList;
    }

}
