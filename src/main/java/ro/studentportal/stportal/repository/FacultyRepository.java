package ro.studentportal.stportal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ro.studentportal.stportal.model.Faculty;
import ro.studentportal.stportal.model.User;

public interface FacultyRepository extends PagingAndSortingRepository<Faculty, Long> {

    Faculty findByName(String name);
    Page<Faculty> findAll(Pageable pageable);
}
