package hanium.aidiary.dto.auth;

import hanium.aidiary.domain.File;
import hanium.aidiary.domain.Member;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinResponse {
    private String email;
}
