package hanium.aidiary.controller;

import hanium.aidiary.dto.comment.CommentRequest;
import hanium.aidiary.dto.comment.CommentResponse;
import hanium.aidiary.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    // 댓글 작성
    @PostMapping("/")
    public ResponseEntity<Object> createComment(@RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.createComment(request));
    }

    // 댓글 조회 -> DiaryController
    // 댓글 삭제
}
