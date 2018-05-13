package ro.studentportal.stportal.resources.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import ro.studentportal.stportal.model.Book;
import ro.studentportal.stportal.model.BookStatus;
import ro.studentportal.stportal.model.UniversityEmployee;
import ro.studentportal.stportal.model.User;
import ro.studentportal.stportal.resources.dto.BookDto;
import ro.studentportal.stportal.resources.dto.BookStatusDto;
import ro.studentportal.stportal.service.LibraryService;
import ro.studentportal.stportal.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class BookMapper {

    public static BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    @Autowired
    LibraryService libraryService;
    @Autowired
    UserService userService;

    public Book toModel(BookDto bookDto) {

        if (bookDto == null) {
            return null;
        }

        Book book = new Book();

        book.setAuthor(bookDto.getAuthor());
        book.setDomain(bookDto.getDomain());
        book.setPublisher(bookDto.getPublisher());
        book.setTitle(bookDto.getTitle());

        String name = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName();
        User user = userService.findUserByUsername(name);
        UniversityEmployee universityEmployee = (UniversityEmployee) user;
//        Library library = libraryService.findById(Long.valueOf(bookDto.getLibraryId()));
//        if (library == null) {
//            throw new ElementInexistentException("Library with id=" + bookDto.getLibraryId() + "does not exist");
//        }

        book.setLibrary(universityEmployee.getFaculty().getLibrary());

        if (bookDto.getBookStatusDtoList() == null || bookDto.getBookStatusDtoList().isEmpty()) {
            throw new IllegalArgumentException("Books don't have any entries with ISBN");
        }

        List<BookStatus> bookStatusList = new ArrayList<>();
        List<BookStatusDto> bookStatusDtos = bookDto.getBookStatusDtoList();

        for (BookStatusDto bookStatusDto : bookStatusDtos) {
            BookStatus bookStatus = new BookStatus();

            bookStatus.setISBN(bookStatusDto.getISBN());
            bookStatus.setStatus(bookStatusDto.isStatus());
            bookStatus.setBook(book);

            bookStatusList.add(bookStatus);
        }

        book.setBooks(bookStatusList);

        return book;
    }

    public BookDto toDto(Book book) {

        if (book == null) {
            return null;
        }

        BookDto bookDto = new BookDto();
        bookDto.setAuthor(book.getAuthor());
        bookDto.setBookId(book.getId());
        bookDto.setDomain(book.getDomain());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setTitle(book.getTitle());
        bookDto.setLibraryId(book.getLibrary().getId());

        List<BookStatusDto> bookStatusDtos = new ArrayList<>();

        for (BookStatus bookStatus : book.getBooks()) {
            BookStatusDto bookStatusDto = new BookStatusDto();
            bookStatusDto.setId(bookStatus.getId());
            bookStatusDto.setStatus(bookStatus.isStatus());
            bookStatusDto.setISBN(bookStatus.getISBN());
            bookStatusDtos.add(bookStatusDto);
        }

        bookDto.setBookStatusDtoList(bookStatusDtos);

        return bookDto;
    }

    public BookStatusDto toDto(BookStatus bookStatus) {
        BookStatusDto bookStatusDto = new BookStatusDto();
        bookStatusDto.setId(bookStatus.getId());
        bookStatusDto.setISBN(bookStatus.getISBN());
        bookStatusDto.setStatus(bookStatus.isStatus());

        if (bookStatus.getStudent() != null) {
            String studentName = bookStatus.getStudent().getName().getFirstName() + bookStatus.getStudent().getName().getLastName();
            bookStatusDto.setRentedBy(studentName);
        }
        return bookStatusDto;
    }

    public List<BookStatusDto> toDto(List<BookStatus> bookStatuses) {
        List<BookStatusDto> bookStatusDtos = new ArrayList<>();
        for (BookStatus bookStatus : bookStatuses) {
            bookStatusDtos.add(toDto(bookStatus));
        }
        return bookStatusDtos;
    }
}