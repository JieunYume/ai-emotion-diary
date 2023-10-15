package hanium.aidiary.dto.group;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GroupCreateRequest {

    private String name;

    @NotBlank
    private Long creatorId;
}
