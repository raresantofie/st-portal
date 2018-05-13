package ro.studentportal.stportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.studentportal.stportal.model.Library;
import ro.studentportal.stportal.repository.LibraryRepository;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;


    @Override
    public <S extends Library> S save(S var1) {
        return null;
    }

    @Override
    public <S extends Library> S update(S var1) {
        return null;
    }

    @Override
    public void delete(Library entity) {

    }

    @Override
    public Library findById(Long id) {
        return libraryRepository.findById(id).orElse(null);
    }
}
