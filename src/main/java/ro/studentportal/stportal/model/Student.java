package ro.studentportal.stportal.model;

import org.springframework.data.domain.PageRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {

    @OneToOne
    private Faculty faculty;
    private Integer yearOfStudy;
    private Gender gender;
    private boolean accommodationStatus;
    private boolean bookPermit = false;

    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
    List<PaperRequest> paperRequestList;

    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
    List<BookStatus> bookStatuses;
//
//    @ManyToMany(cascade = { CascadeType.ALL })
//    @JoinTable(
//            name = "student_courses",
//            joinColumns = { @JoinColumn(name = "student_id") },
//            inverseJoinColumns = { @JoinColumn(name = "course_id") }
//    )
//    List<Course> courses = new ArrayList<>();

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Integer getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(Integer yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isAccommodationStatus() {
        return accommodationStatus;
    }

    public void setAccommodationStatus(boolean accommodationStatus) {
        this.accommodationStatus = accommodationStatus;
    }

    public List<PaperRequest> getPaperRequestList() {
        return paperRequestList;
    }

    public void setPaperRequestList(List<PaperRequest> paperRequestList) {
        this.paperRequestList = paperRequestList;
    }

    public List<BookStatus> getBookStatuses() {
        return bookStatuses;
    }

    public void setBookStatuses(List<BookStatus> bookStatuses) {
        this.bookStatuses = bookStatuses;
    }

    public boolean isBookPermit() {
        return bookPermit;
    }

    public void setBookPermit(boolean bookPermit) {
        this.bookPermit = bookPermit;
    }

//    public List<Course> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(List<Course> courses) {
//        this.courses = courses;
//    }
}
