package hanium.aidiary.service;
import hanium.aidiary.domain.Diary;
import hanium.aidiary.domain.Like;
import hanium.aidiary.domain.Member;
import hanium.aidiary.dto.like.LikeRequestDTO;
import hanium.aidiary.exception.CustomException;
import hanium.aidiary.repository.DiaryRepository;
import hanium.aidiary.repository.LikeRepository;
import hanium.aidiary.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static hanium.aidiary.handler.ErrorCode.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final DiaryRepository diaryRepository;


    @Transactional
    public void add(LikeRequestDTO likeRequestDTO) throws NullPointerException {

        Member member = memberRepository.findById(likeRequestDTO.getMemberId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        Diary diary = diaryRepository.findById(likeRequestDTO.getDiaryId())
                .orElseThrow(() -> new CustomException(DIARY_NOT_FOUND));

        // 이미 좋아요되어있으면 에러 반환
        if (likeRepository.findByMemberAndDiary(member, diary).isPresent()){
            throw new CustomException(ALREADY_SAVED_LIKE);
        }

        Like like = Like.builder()
                .member(member)
                .diary(diary)
                .build();

        likeRepository.save(like);
        diary.addLike();
    }

    @Transactional
    public void delete(LikeRequestDTO likeRequestDTO) {

        Member member = memberRepository.findById(likeRequestDTO.getMemberId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        Diary diary = diaryRepository.findById(likeRequestDTO.getDiaryId())
                .orElseThrow(() -> new CustomException(DIARY_NOT_FOUND));

        Like like = likeRepository.findByMemberAndDiary(member, diary)
                .orElseThrow(() -> new CustomException(LIKE_NOT_FOUND));

        likeRepository.delete(like);

        diary.deleteLike();
    }

}
