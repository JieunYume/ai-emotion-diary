package hanium.aidiary.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class LoginResponse {
    private Long memberId;
    private String nickName;
    private String fileUrl;
}