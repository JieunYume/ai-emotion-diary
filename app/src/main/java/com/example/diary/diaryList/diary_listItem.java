package com.example.diary.diaryList;


//rfid 태그 list
public class diary_listItem {
    //mood_emoji_name, diary_id, create_date, like_count, thing, impression, member_id
    private String mood_emoji_name;    //기존 rfid태그에 사용자가 따로 붙인 이름
    private Long diary_id;   //기존 rfid태그(초기)의 이름
    private String create_date;
    private Long like_count;
    private String thing;
    private String member_id;
    private Long memberId;



    public diary_listItem(Long memberId, String nickName, String mood_emoji_name, Long diary_id, String create_date, Long like_count, String thing)
    //나라 이름도 리스트아이템에 넣을지 말지. 그냥 전 페이지에서 넘겨줄지
    //int poster
    {
        this.memberId=memberId;
        this.member_id=nickName;
        this.mood_emoji_name=mood_emoji_name;
        this.diary_id=diary_id;
        this.create_date=create_date;
        this.like_count=like_count;
        this.thing=thing;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMood_emoji_name(){return this.mood_emoji_name;}
    public Long getDiary_id(){return this.diary_id;}
    public String getCreate_date(){return this.create_date;}
    public Long getLike_count(){return this.like_count;}
    public String getThing(){return this.thing;}
    public String getMember_id(){return this.member_id;}

    public void setMood_emoji_name(String mood_emoji_name) {
        this.mood_emoji_name = mood_emoji_name;
    }
    public void setDiary_id(Long diary_id) {
        this.diary_id = diary_id;
    }
    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
    public void setLike_count(Long like_count) {
        this.like_count = like_count;
    }
    public void setThing(String thing) {
        this.thing = thing;
    }
    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

}

