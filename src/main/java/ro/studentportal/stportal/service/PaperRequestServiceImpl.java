package ro.studentportal.stportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.studentportal.stportal.model.PaperRequest;
import ro.studentportal.stportal.repository.FileRequestRepository;

@Service
public class PaperRequestServiceImpl implements PaperRequestService {

    @Autowired
    FileRequestRepository fileRequestRepository;

    @Override
    public PaperRequest save(PaperRequest paperRequest) {
        return fileRequestRepository.save(paperRequest);
    }

    @Override
    public Page<PaperRequest> findAllByLibraryId(Long id, Pageable pageable) {
        return fileRequestRepository.findAllByLibraryId(id, pageable);
    }

    @Override
    public PaperRequest findById(Long id) {
        return fileRequestRepository.findById(id).orElse(null);
    }
}
