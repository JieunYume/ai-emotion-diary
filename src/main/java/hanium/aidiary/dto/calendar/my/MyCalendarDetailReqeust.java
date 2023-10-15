package hanium.aidiary.dto.calendar.my;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyCalendarDetailReqeust {
    private Long memberId;
    private int year;
    private int month;
    private int day;
}
