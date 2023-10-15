package hanium.aidiary.dto.calendar.my;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyCalendarResponse {
    ArrayList<CalendarDiaryDto> diaryList;

    @Getter
    @Setter
    public static class CalendarDiaryDto {
        private Long diaryId;
        private LocalDateTime date;
        private String moodEmojiName;

        public CalendarDiaryDto(Long diaryId, LocalDateTime date, String moodEmojiName) {
            this.diaryId = diaryId;
            this.date = date;
            this.moodEmojiName = moodEmojiName;
        }
    }
}
