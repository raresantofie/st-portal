package ro.studentportal.stportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import ro.studentportal.stportal.model.Book;
import ro.studentportal.stportal.model.BookStatus;

import java.util.List;

public interface BookStatusRepository extends JpaRepository<BookStatus, Long> {
    List<BookStatus> findAllByBook(Book book);
}
