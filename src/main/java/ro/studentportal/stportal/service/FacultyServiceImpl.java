package ro.studentportal.stportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.studentportal.stportal.config.ErrorCodes;
import ro.studentportal.stportal.exception.ElementExistsException;
import ro.studentportal.stportal.model.Faculty;
import ro.studentportal.stportal.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public <S extends Faculty> S save(S var1) {
        Faculty faculty = facultyRepository.findByName(var1.getName());
        if(faculty != null){
            throw new ElementExistsException(String.valueOf(ErrorCodes.ELEMENT_ALREADY_PRESENT));
        }
        return facultyRepository.save(var1);
    }

    @Override
    public <S extends Faculty> S update(S var1) {
        return null;
    }

    @Override
    public void delete(Faculty entity) {

    }

    @Override
    public Faculty findById(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }


    @Override
    public Page<Faculty> getFaculties(Pageable pageable) {
        return facultyRepository.findAll(pageable);
    }

    @Override
    public List<Faculty> getAll() {
        return (List<Faculty>) facultyRepository.findAll();
    }

    @Override
    public Faculty findByName(String name) {
        return facultyRepository.findByName(name);
    }
}
