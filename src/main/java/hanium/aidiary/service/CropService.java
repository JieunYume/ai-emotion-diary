package hanium.aidiary.service;

import hanium.aidiary.domain.Crop;
import hanium.aidiary.dto.crop.CropHarvestRequest;
import hanium.aidiary.dto.crop.CropRequest;
import hanium.aidiary.dto.crop.CropResponse;
import hanium.aidiary.exception.CustomException;
import hanium.aidiary.repository.CropRepository;
import hanium.aidiary.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static hanium.aidiary.handler.ErrorCode.CROP_NOT_FOUND;
import static hanium.aidiary.handler.ErrorCode.USER_NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CropService {
    private final CropRepository cropRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public Crop createCrop() {
        return cropRepository.save(
                Crop.builder()
                        .build());

    }

    public CropResponse findCrop(Long memberId) {
        Crop crop = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND))
                .getCrop();
        return CropResponse.builder()
                .cropId(crop.getId())
                .growthStage(crop.getGrowthStage())
                .cropCount(crop.getCropCount())
                .nonHarverstCropCount(crop.getNonHarverstCropCount())
                .build();

    }


    @Transactional
    public CropResponse harvestCrop(Long cropId) {
        Crop crop = cropRepository.findById(cropId)
                .orElseThrow(() -> new CustomException(CROP_NOT_FOUND));
        crop.harvest();

        return CropResponse.builder()
                .cropId(crop.getId())
                .growthStage(crop.getGrowthStage())
                .cropCount(crop.getCropCount())
                .build();
    }

}
