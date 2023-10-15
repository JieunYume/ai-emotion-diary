package hanium.aidiary.dto.auth;

import lombok.*;

import java.io.File;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDto {
    private String origFilename;
    private String filename;
    private String filePath;
}
