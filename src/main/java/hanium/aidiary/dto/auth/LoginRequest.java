package hanium.aidiary.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginRequest {
    private String email;
    private String password;
}