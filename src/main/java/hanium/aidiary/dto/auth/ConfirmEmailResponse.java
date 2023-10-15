package hanium.aidiary.dto.auth;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfirmEmailResponse {
    private String authCode;
}
