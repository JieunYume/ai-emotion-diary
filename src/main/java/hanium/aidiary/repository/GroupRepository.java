package hanium.aidiary.repository;


import hanium.aidiary.domain.Group;
import hanium.aidiary.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findByInvitationCode(String code);
}
