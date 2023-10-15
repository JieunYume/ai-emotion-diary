package hanium.aidiary.repository;

import hanium.aidiary.domain.Diary;
import hanium.aidiary.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
