package hanium.aidiary.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Getter
@Table(name = "crops")
public class Crop {

    @Transient
    private final int INITIAL_GROWTH = 1;

    @Transient
    private final int GROWTH_LIMIT = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crop_id")
    private Long id;

    @OneToOne(mappedBy = "crop", fetch = FetchType.LAZY)
    private Member member;

    private int growthStage;

    @ColumnDefault("0") // 디폴트값
    private int cropCount;

    @ColumnDefault("0") // 디폴트값
    private int nonHarverstCropCount;

    public int growStageUp() {
        if (this.growthStage < GROWTH_LIMIT) {
            this.growthStage += 1;
            return growthStage;
        }
        return -1;
    }

    public void harvest() {
        if (nonHarverstCropCount >= 1) {
            cropCount += nonHarverstCropCount;
        }
        this.nonHarverstCropCount = 0;
    }

    public void draft() {
        nonHarverstCropCount++;
        this.growthStage = INITIAL_GROWTH;
    }

    public boolean isAllGrown() {
        if (this.growthStage == GROWTH_LIMIT){
            return true;
        } else {
            return false;
        }

    }

    // 생성자에 @Builder 적용
    @Builder
    public Crop() {
        this.growthStage = INITIAL_GROWTH;
    }


}
