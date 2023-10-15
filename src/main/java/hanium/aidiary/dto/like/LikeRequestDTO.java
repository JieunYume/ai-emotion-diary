package hanium.aidiary.dto.like;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeRequestDTO {
    private Long memberId;
    private Long diaryId;

    public LikeRequestDTO(Long memberId, Long diaryId) {
        this.memberId = memberId;
        this.diaryId = diaryId;
    }
}