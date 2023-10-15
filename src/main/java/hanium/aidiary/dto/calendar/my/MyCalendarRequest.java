package hanium.aidiary.dto.calendar.my;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyCalendarRequest {

    private Long memberId;

    private String year;
    private String month;
}
