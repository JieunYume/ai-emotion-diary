package hanium.aidiary.repository;

import hanium.aidiary.domain.Diary;
import hanium.aidiary.domain.Group;
import hanium.aidiary.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(@Param("email") String email);
    Optional<Member> findByGroupId(Long groupId);
}
