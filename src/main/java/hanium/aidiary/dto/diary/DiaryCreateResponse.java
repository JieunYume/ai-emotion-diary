package hanium.aidiary.dto.diary;

import hanium.aidiary.dto.comment.CommentResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiaryCreateResponse {
    private Long diaryId;
    private String writerNickName;
    private String moodEmoji;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String thing;
    private String impression;
    private List<CommentResponse> comments = new ArrayList<>();
    private int likeNum;
    private boolean cropIsAllGrown;
}