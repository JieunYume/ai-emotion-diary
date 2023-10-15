package hanium.aidiary.repository;

import hanium.aidiary.domain.Diary;
import hanium.aidiary.domain.Like;
import hanium.aidiary.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByMemberAndDiary(Member member, Diary diary);
}
