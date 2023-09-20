package com.example.diary.moodplayList;


public class mood_playlistItem {
    //memberId, nickName, imagePath
    private String mood;
    private int src;

    public mood_playlistItem(String mood, int src)
    //나라 이름도 리스트아이템에 넣을지 말지. 그냥 전 페이지에서 넘겨줄지
    //int poster
    {
        this.mood=mood;
        this.src=src;

    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public String getMood() {
        return mood;
    }

    public int getSrc() {
        return src;
    }
}
