package com.example.diary.playList;



public class playlistItem {
    //memberId, nickName, imagePath
    private String song;
    private String singer;
    private String url;
    private String date;
    private String mood;
    private int fake;

    public playlistItem(String song, String singer, String url, String date, String mood)
    //나라 이름도 리스트아이템에 넣을지 말지. 그냥 전 페이지에서 넘겨줄지
    //int poster
    {
        this.song=song;
        this.singer=singer;
        this.url=url;
        this.date=date;
        this.mood=mood;

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getUrl() {
        return url;
    }

    public String getSinger() {
        return singer;
    }

    public String getSong() {
        return song;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFake() {
        return fake;
    }

    public void setFake(int fake) {
        this.fake = fake;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
