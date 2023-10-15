package hanium.aidiary.dto.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupJoinRequest {
    private Long memberId;
    private String invitationCode;
}
