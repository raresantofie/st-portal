package ro.studentportal.stportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.studentportal.stportal.model.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
