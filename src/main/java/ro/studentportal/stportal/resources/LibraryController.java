package ro.studentportal.stportal.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ro.studentportal.stportal.model.Book;
import ro.studentportal.stportal.model.BookStatus;
import ro.studentportal.stportal.model.UniversityEmployee;
import ro.studentportal.stportal.model.PaperRequest;
import ro.studentportal.stportal.resources.dto.BookDto;
import ro.studentportal.stportal.resources.dto.BookStatusDto;
import ro.studentportal.stportal.resources.dto.PaperRequestProcessDto;
import ro.studentportal.stportal.resources.dto.mappers.BookMapper;
import ro.studentportal.stportal.resources.dto.mappers.PaperRequestMapper;
import ro.studentportal.stportal.service.BookService;
import ro.studentportal.stportal.service.PaperRequestService;
import ro.studentportal.stportal.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    PaperRequestService paperRequestService;

    @PostMapping("/book")
    public BookDto createBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.toModel(bookDto);
        Book dbBook = bookService.save(book);
        return BookMapper.INSTANCE.toDto(dbBook);
    }

    @GetMapping("/book")
    public Page<BookDto> getBooks(@RequestParam int pageCount, @RequestParam int pageSize){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ro.studentportal.stportal.model.User dbUser = userService.findUserByUsername(user.getUsername());

        Page<Book> bookPage = bookService.findBooks(((UniversityEmployee)dbUser).getFaculty().getLibrary().getId(), new PageRequest(pageCount, pageSize));
        Page<BookDto> bookDto = bookPage.map(BookMapper.INSTANCE::toDto);
        return bookDto;
    }

    @GetMapping("/book/entries/{id}")
    public List<BookStatusDto> getBookStatus(@PathVariable long id){
        List<BookStatus> bookStatusList = bookService.findByBook(id);
        return bookMapper.toDto(bookStatusList);
    }

    @GetMapping("/paper")
    public Page<PaperRequestProcessDto> getRequestedPapers(@RequestParam int pageCount, @RequestParam int pageSize){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ro.studentportal.stportal.model.User dbUser = userService.findUserByUsername(user.getUsername());
        UniversityEmployee universityEmployee = (UniversityEmployee) dbUser;
        Page<PaperRequest> paperRequestPage = paperRequestService.findAllByLibraryId(((UniversityEmployee) dbUser).getFaculty().getLibrary().getId(), new PageRequest(pageCount, pageSize));
        Page<PaperRequestProcessDto> paperRequestProcessDtos = paperRequestPage.map(PaperRequestMapper.INSTANCE::toProcessDto);
        return paperRequestProcessDtos;
    }

    @PutMapping("/paper/{id}")
    public HttpStatus processRequest(@PathVariable("id") Long id){
        PaperRequest paperRequest = paperRequestService.findById(id);
        paperRequest.setFlag(true);
        try {
            paperRequestService.save(paperRequest);
            return HttpStatus.OK;
        } catch (Exception e){
            return HttpStatus.BAD_REQUEST;
        }
    }





}
