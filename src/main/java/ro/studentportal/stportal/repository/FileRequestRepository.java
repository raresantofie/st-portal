package ro.studentportal.stportal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.studentportal.stportal.model.PaperRequest;

public interface FileRequestRepository extends JpaRepository<PaperRequest, Long>{

    Page<PaperRequest> findAllByLibraryId(Long id, Pageable pageable);

}
