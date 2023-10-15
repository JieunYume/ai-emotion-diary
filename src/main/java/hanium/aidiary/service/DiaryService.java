package hanium.aidiary.service;

import hanium.aidiary.domain.Comment;
import hanium.aidiary.domain.Crop;
import hanium.aidiary.domain.Diary;
import hanium.aidiary.domain.Member;
import hanium.aidiary.dto.calendar.CalendarDetailDto;
import hanium.aidiary.dto.calendar.group.GroupCalendarDetailResponse;
import hanium.aidiary.dto.calendar.my.MyCalendarResponse;
import hanium.aidiary.dto.calendar.my.MyCalendarResponse.CalendarDiaryDto;
import hanium.aidiary.dto.comment.CommentResponse;
import hanium.aidiary.dto.diary.DiaryCreateRequest;
import hanium.aidiary.dto.diary.DiaryCreateResponse;
import hanium.aidiary.dto.diary.DiaryDeleteRequest;
import hanium.aidiary.dto.diary.DiaryResponse;
import hanium.aidiary.exception.CustomException;
import hanium.aidiary.repository.DiaryRepository;
import hanium.aidiary.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static hanium.aidiary.handler.ErrorCode.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE) // 이게뭐징
    public DiaryCreateResponse createDiary(DiaryCreateRequest diaryRequest) throws CustomException{
        Member member = memberRepository.findById(diaryRequest.getMemberId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        // 플라스크랑 연동해야겠는걸ㄴ?
        // analysisDiary(diaryRequest)

        // 농작물 성장 -> 나중에 보완
        Crop crop = member.getCrop();

        if (crop.growStageUp() == -1){
            crop.draft();
            //throw new CustomException(CROP_ALL_GROWN);
        }


        Diary saveDiary = diaryRepository.save(Diary.builder()
                .member(member)
                .moodEmojiName(diaryRequest.getMoodEmojiName())
                .createDate(LocalDateTime.now())
                .updateDate(null)
                .thing(diaryRequest.getThing())
                .impression(diaryRequest.getImpression())
                .comments(null)
                .build());

        boolean cropIsAllGrown = false;
        // 농작물이 다자랐다면 true
        if(crop.isAllGrown()){
            cropIsAllGrown = true;
        }

        return getDiaryCreateResponse(saveDiary, cropIsAllGrown);
    }

    private DiaryResponse getDiaryResponse(Diary diary) {
        DiaryResponse diaryResponse = DiaryResponse.builder()
                .diaryId(diary.getId())
                .writerNickName(diary.getMember().getNickName())
                .moodEmoji(diary.getMoodEmojiName())
                .createDate(diary.getCreateDate())
                .updateDate(diary.getUpdateDate())
                .thing(diary.getThing())
                .impression(diary.getImpression())
                .comments(getCommentResponses(diary))
                .likeNum(diary.getLikeCount())
                .build();

        return diaryResponse;
    }

    private DiaryCreateResponse getDiaryCreateResponse(Diary diary, boolean cropIsAllGrown) {
        DiaryCreateResponse diaryResponse = DiaryCreateResponse.builder()
                .diaryId(diary.getId())
                .writerNickName(diary.getMember().getNickName())
                .moodEmoji(diary.getMoodEmojiName())
                .createDate(diary.getCreateDate())
                .updateDate(diary.getUpdateDate())
                .thing(diary.getThing())
                .impression(diary.getImpression())
                .comments(getCommentResponses(diary))
                .likeNum(diary.getLikeCount())
                .cropIsAllGrown(cropIsAllGrown)
                .build();

        return diaryResponse;
    }

    private List<CommentResponse> getCommentResponses(Diary diary) {
        List<CommentResponse> commentList = new ArrayList<>();
        if(diary.haveComment()) {
            for (Comment comment : diary.getComments()) {
                commentList.add(CommentResponse.builder()
                        .commentId(comment.getId())
                        .writerNickName(comment.getMember().getNickName())
                        .content(comment.getContent())
                        .build());
            }
        } else{
            commentList = null;
        }
        return commentList;
    }


    public DiaryResponse findDiary(Long diaryId) throws CustomException{
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new CustomException(DIARY_NOT_FOUND));

        return getDiaryResponse(diary);
    }


// 이메일이 아닌 memeber_id 받기
    @Transactional
    public int deleteDiary(DiaryDeleteRequest diaryDeleteRequest) throws CustomException {
        Optional<Diary> optionalDiary=diaryRepository.findById(diaryDeleteRequest.getDiaryId());
        if (optionalDiary.isEmpty()) {
            throw new CustomException(DIARY_NOT_FOUND);
        }
        Diary deleteDiary = optionalDiary.get();
        System.out.println(deleteDiary.toString());

        if(deleteDiary.getMember().getId() == diaryDeleteRequest.getMemberId()) {
            diaryRepository.delete(deleteDiary);
            return 1;
        }
        return 0;
    }

    @Transactional
    public DiaryResponse updateDiary(long diaryId, DiaryCreateRequest diaryCreateRequest) throws CustomException, IllegalArgumentException{
        Diary originalDiary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new CustomException(DIARY_NOT_FOUND));
        Diary updateDiary = originalDiary;
        if(updateDiary.getMember().getId() == diaryCreateRequest.getMemberId()) {


            //patch작업을 위한 조건물 및 updateDiaryEntity초기화문
            if (diaryCreateRequest.getMoodEmojiName() != null)
                updateDiary.setMoodEmojiName(diaryCreateRequest.getMoodEmojiName());
            if (diaryCreateRequest.getThing() != null) updateDiary.setThing(diaryCreateRequest.getThing());
            if (diaryCreateRequest.getImpression() != null)
                updateDiary.setImpression(diaryCreateRequest.getImpression());
            updateDiary.setUpdateDate(LocalDateTime.now());

            Diary diary = diaryRepository.save(updateDiary);
            return getDiaryResponse(diary);
        }else
            throw new CustomException(WRITER_NOT_MATCH);
    }

    public MyCalendarResponse findMyCalendarList(Long memberId, int year, int month) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        List<Diary> diaryEntities = member.getDiaries();
        System.out.println("diaryEntities: "+diaryEntities.get(0));
        ArrayList<CalendarDiaryDto> diaryList= new ArrayList<>();
        CalendarDiaryDto calendarDiaryDto;
        System.out.println("DiaryService.findMyCalendarList:계산전");
        try {
            System.out.println("요청 년도:"+year+" , 요청 월:"+month);
            for (Diary diary : diaryEntities) { // 더 효율적인 방법 있을까?
                if (diary.getCreateDate().getYear() == year && diary.getCreateDate().getMonthValue() == month) {
                    calendarDiaryDto = new CalendarDiaryDto(diary.getId(), diary.getCreateDate(), diary.getMoodEmojiName());
                    diaryList.add(calendarDiaryDto);
                }
            }
        } catch (Exception e) {
            throw new CustomException(DIARY_CALENDAR_CAL_ERROR);
        }
        System.out.println("DiaryService.findMyCalendarList:계산완료");
        return MyCalendarResponse.builder().diaryList(diaryList).build();
    }

    public CalendarDetailDto findMyCanlendarDetail(Long memberId, int year, int month, int day) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        for(Diary diary: member.getDiaries()){ // 더 효율적인 방법 있을까?
            LocalDateTime createDate = diary.getCreateDate();
            if(createDate.getYear() == year && createDate.getMonthValue() == month && createDate.getDayOfMonth() == day){
                return CalendarDetailDto.builder()
                        .diaryId(diary.getId())
                        .nickName(diary.getMember().getNickName())
                        .moodEmojiName(diary.getMoodEmojiName())
                        .createDate(diary.getCreateDate())
                        .thing(diary.getThing())
                        .likeCount(diary.getLikeCount())
                        .build();
            }
        }
        return null;
    }

    public GroupCalendarDetailResponse findGroupCanlendarDetail(Long memberId, int year, int month, int day) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        if (member.getGroup() == null) {
            throw new CustomException(GROUP_NOT_EXIST);
        }
        List<Member> members = member.getGroup().getMembers();
        List<CalendarDetailDto> detailList = new ArrayList<>();
        for(Member groupMember : members){
            for(Diary diary: groupMember.getDiaries()) {
                LocalDateTime createDate = diary.getCreateDate();
                if (createDate.getYear() == year && createDate.getMonthValue() == month && createDate.getDayOfMonth() == day) {
                    detailList.add(CalendarDetailDto.builder()
                            .memberId(diary.getMember().getId())
                            .diaryId(diary.getId())
                            .nickName(diary.getMember().getNickName())
                            .moodEmojiName(diary.getMoodEmojiName())
                            .createDate(diary.getCreateDate())
                            .thing(diary.getThing())
                            .likeCount(diary.getLikeCount())
                            .build());
                    break;
                }
            }
        }

        return GroupCalendarDetailResponse.builder()
                .groupCalendarDetailList(detailList)
                .build();
    }
/*
    public Object findGroupCanlendarDetail2(Long groupId, int year, int month, int day) {
        Member member = groupRepository.findById(groupId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        if (member.getGroup() == null) {
            throw new CustomException(GROUP_NOT_EXIST);
        }
        List<Member> members = member.getGroup().getMembers();
        List<CalendarDetailDto> detailList = new ArrayList<>();
        for(Member groupMember : members){
            for(Diary diary: groupMember.getDiaries()) {
                LocalDateTime createDate = diary.getCreateDate();
                if (createDate.getYear() == year && createDate.getMonthValue() == month && createDate.getDayOfMonth() == day) {
                    detailList.add(CalendarDetailDto.builder()
                            .memberId(diary.getMember().getId())
                            .diaryId(diary.getId())
                            .nickName(diary.getMember().getNickName())
                            .moodEmojiName(diary.getMoodEmojiName())
                            .createDate(diary.getCreateDate())
                            .thing(diary.getThing())
                            .likeCount(diary.getLikeCount())
                            .build());
                    break;
                }
            }
        }

        return GroupCalendarDetailResponse.builder()
                .groupCalendarDetailList(detailList)
                .build();

    }

 */
}
