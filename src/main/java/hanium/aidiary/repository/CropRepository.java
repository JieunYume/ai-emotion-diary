package hanium.aidiary.repository;

import hanium.aidiary.domain.Crop;
import hanium.aidiary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepository extends JpaRepository<Crop, Long> {
}
