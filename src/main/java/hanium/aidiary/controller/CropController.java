package hanium.aidiary.controller;

import hanium.aidiary.dto.crop.CropHarvestRequest;
import hanium.aidiary.dto.crop.CropRequest;
import hanium.aidiary.dto.crop.CropResponse;
import hanium.aidiary.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // 권한에 따라 메소드의 호출을 제한
@RestController
@RequestMapping("/api/v1/crop")
public class CropController {
    private final CropService cropService;

    // 농작물 조회
    @GetMapping("/")
    public ResponseEntity<CropResponse> findCrop(@RequestParam Long memberId) {
        return ResponseEntity.ok(cropService.findCrop(memberId));
    }


    @PostMapping("/harvest")
    public ResponseEntity<CropResponse> harvestCrop(@RequestParam Long cropId) {
        return ResponseEntity.ok(cropService.harvestCrop(cropId));
    }




}
