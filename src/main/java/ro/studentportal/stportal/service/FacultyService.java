package ro.studentportal.stportal.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ro.studentportal.stportal.model.Faculty;

import java.util.List;

public interface FacultyService extends GenericService<Faculty, Long> {
    Page<Faculty> getFaculties(Pageable pageable);
    List<Faculty> getAll();
    Faculty findByName(String name);
}
