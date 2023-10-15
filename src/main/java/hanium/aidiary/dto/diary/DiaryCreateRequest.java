package hanium.aidiary.dto.diary;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiaryCreateRequest {
    private Long memberId;
    private String moodEmojiName; // 감정 이모지 이름

    private String thing;
    private String impression;
}
