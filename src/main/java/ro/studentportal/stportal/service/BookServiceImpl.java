package ro.studentportal.stportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ro.studentportal.stportal.exception.ElementInexistentException;
import ro.studentportal.stportal.model.Book;
import ro.studentportal.stportal.model.BookStatus;
import ro.studentportal.stportal.repository.BookRepository;

import java.util.List;


@Service
@Transactional
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Override
    public <S extends Book> S save(S book) {
        return bookRepository.save(book);
    }

    @Override
    public <S extends Book> S update(S book) {
        return bookRepository.save(book);
    }

    @Override
    public void delete(Book entity) {
        bookRepository.delete(entity);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Book> findBooks(Long id, Pageable pageable) {
        return bookRepository.findAllByLibraryId(id, pageable);
    }

    @Override
    public List<BookStatus> findByBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if(book!=null){
            return book.getBooks();
        }
        throw new ElementInexistentException("Book does not exit");
    }
}
