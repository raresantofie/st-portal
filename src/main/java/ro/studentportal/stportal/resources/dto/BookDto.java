package ro.studentportal.stportal.resources.dto;

import ro.studentportal.stportal.model.BookStatus;

import java.util.List;

public class BookDto {
    private Long bookId;
    private String author;
    private String domain;
    private String title;
    private String publisher;
    private Long libraryId;

    private List<BookStatusDto> bookStatusDtoList;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public List<BookStatusDto> getBookStatusDtoList() {
        return bookStatusDtoList;
    }

    public void setBookStatusDtoList(List<BookStatusDto> bookStatusDtoList) {
        this.bookStatusDtoList = bookStatusDtoList;
    }
}
