package hanium.aidiary.repository;

import hanium.aidiary.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}