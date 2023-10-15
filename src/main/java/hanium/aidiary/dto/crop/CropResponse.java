package hanium.aidiary.dto.crop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CropResponse {
    private Long cropId;
    private int growthStage;
    private int cropCount;
    private int nonHarverstCropCount;
}
