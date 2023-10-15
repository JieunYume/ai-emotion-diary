package hanium.aidiary.dto.calendar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalendarDetailDto {
    private Long memberId;
    private Long diaryId;
    private String nickName;
    private String moodEmojiName;
    private LocalDateTime createDate;
    private String thing;
    private int likeCount;
}
