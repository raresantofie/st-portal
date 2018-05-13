package ro.studentportal.stportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.studentportal.stportal.model.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByFacultyId(Long id);
}
