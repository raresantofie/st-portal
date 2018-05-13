package ro.studentportal.stportal.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ro.studentportal.stportal.model.PaperRequest;
import ro.studentportal.stportal.model.Student;

public interface PaperRequestService {

    PaperRequest save(PaperRequest paperRequest);
    Page<PaperRequest> findAllByLibraryId(Long id, Pageable pageable);
    PaperRequest findById(Long id);


}
