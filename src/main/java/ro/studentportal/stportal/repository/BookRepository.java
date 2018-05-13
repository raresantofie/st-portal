package ro.studentportal.stportal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.studentportal.stportal.model.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    Page<Book> findAllByLibraryId(Long libraryId, Pageable pageable);


}
