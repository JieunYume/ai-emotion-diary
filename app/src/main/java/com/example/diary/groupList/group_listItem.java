package com.example.diary.groupList;

public class group_listItem {
    //memberId, nickName, imagePath
    private Long memberId;    //기존 rfid태그에 사용자가 따로 붙인 이름
    private String nickName;   //기존 rfid태그(초기)의 이름
    private String imagePath;   //기존 rfid태그(초기)의 이름


    public group_listItem(Long memberId, String nickName, String imagePath)
    //나라 이름도 리스트아이템에 넣을지 말지. 그냥 전 페이지에서 넘겨줄지
    //int poster
    {
        this.memberId=memberId;
        this.nickName=nickName;
        this.imagePath=imagePath;
    }

    public String getNickName() {
        return nickName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
