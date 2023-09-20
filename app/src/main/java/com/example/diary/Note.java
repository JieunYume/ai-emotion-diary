package com.example.diary;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {
    @SerializedName("diaryId")
    @Expose
    private int diaryId;

    @SerializedName("nickName")
    @Expose
    private String nickName;

    @SerializedName("moodEmojiName")
    @Expose
    private String moodEmojiName;

    @SerializedName("createDate")
    @Expose
    private String createDate;

    @SerializedName("thing")
    @Expose
    private String thing;

    @SerializedName("likeCount")
    @Expose
    private int likeCount;


    public int getDiaryId(){
        return diaryId;
    }
    public String getNickName(){
        return nickName;
    }
    public String getMoodEmojiName(){
        return moodEmojiName;
    }
    public String getCreateDate(){
        return createDate;
    }
    public String getThing(){
        return thing;
    }
    public int getLikeCount(){
        return likeCount;
    }

    public void setDiaryId(int diaryId){
         this.diaryId=diaryId;
    }
    public void setNickName(String nickName){
        this.nickName=nickName;
    }
    public void setMoodEmojiName(String moodEmojiName){
        this.moodEmojiName=moodEmojiName;
    }
    public void setCreateDate(String createDate){
        this.createDate=createDate;
    }
    public void setThing(String thing){
        this.thing=thing;
    }


    public void setLikeCount(int likeCount){
        this.likeCount=likeCount;
    }




}