package hanium.aidiary.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UnknownErrorDto {
    private int status;
    private String message;
    private String error;
}
