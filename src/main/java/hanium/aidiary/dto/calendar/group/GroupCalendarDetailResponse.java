package hanium.aidiary.dto.calendar.group;

import hanium.aidiary.dto.calendar.CalendarDetailDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupCalendarDetailResponse {
    private List<CalendarDetailDto> groupCalendarDetailList = new ArrayList<>();
}
