package hanium.aidiary.service;

import hanium.aidiary.domain.Comment;
import hanium.aidiary.domain.Crop;
import hanium.aidiary.domain.Diary;
import hanium.aidiary.domain.Member;
import hanium.aidiary.dto.comment.CommentRequest;
import hanium.aidiary.dto.comment.CommentResponse;
import hanium.aidiary.exception.CustomException;
import hanium.aidiary.handler.ErrorCode;
import hanium.aidiary.repository.CommentRepository;
import hanium.aidiary.repository.DiaryRepository;
import hanium.aidiary.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static hanium.aidiary.handler.ErrorCode.DIARY_NOT_FOUND;
import static hanium.aidiary.handler.ErrorCode.USER_NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final DiaryRepository diaryRepository;

    @Transactional
    public CommentResponse createComment(CommentRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        Diary diary = diaryRepository.findById(request.getDiaryId())
                .orElseThrow(() -> new CustomException(DIARY_NOT_FOUND));

        Comment saveComment = commentRepository.save(Comment.builder()
                .member(member)
                .diary(diary)
                .content(request.getContent())
                .createDate(LocalDateTime.now())
                .build());

        return getCommentResponse(saveComment);
    }

    private CommentResponse getCommentResponse(Comment saveComment) {
        return CommentResponse.builder()
                .commentId(saveComment.getId())
                .writerNickName(saveComment.getMember().getNickName())
                .content(saveComment.getContent())
                .build();
    }
}
