package hanium.aidiary.dto.group;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupResponse {
    private Long groupId;
    private String name;

    @Nullable
    private String invitationCode;
}
