package ro.studentportal.stportal.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ro.studentportal.stportal.model.Book;
import ro.studentportal.stportal.model.BookStatus;

import java.util.List;

public interface BookService extends GenericService<Book, Long> {

    Page<Book> findBooks(Long id, Pageable pageable);
    List<BookStatus> findByBook(Long bookId);

}
