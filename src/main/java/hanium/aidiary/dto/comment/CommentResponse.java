package hanium.aidiary.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentResponse {
    private Long commentId;
    private String writerNickName;
    private String content;

}
