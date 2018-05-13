package ro.studentportal.stportal.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book extends BaseEntity{

    private String author;
    private String domain;
    private String title;
    private String publisher;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    List<BookStatus> books;

    @ManyToOne
    @JoinColumn(name="library_id")
    private Library library;

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

    public List<BookStatus> getBooks() {
        return books;
    }

    public void setBooks(List<BookStatus> books) {
        this.books = books;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
