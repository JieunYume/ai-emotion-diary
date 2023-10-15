package hanium.aidiary.controller;

import hanium.aidiary.dto.like.LikeRequestDTO;
import hanium.aidiary.service.DiaryService;
import hanium.aidiary.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // 권한에 따라 메소드의 호출을 제한
@RestController
@RequestMapping("/api/v1/like")
public class LikeController {
    private final LikeService likeService;
    private final DiaryService diaryService;

    @PostMapping("/")
    public ResponseEntity<?> insert(@RequestBody @Valid LikeRequestDTO likeRequestDTO) {
        likeService.add(likeRequestDTO);
        return ResponseEntity.ok("좋아요 추가 성공");
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestBody @Valid LikeRequestDTO likeRequestDTO) {
        likeService.delete(likeRequestDTO);
        return ResponseEntity.ok("좋아요 삭제 성공");
    }

}
