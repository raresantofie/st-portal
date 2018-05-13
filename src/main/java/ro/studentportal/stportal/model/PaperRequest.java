package ro.studentportal.stportal.model;


import ro.studentportal.stportal.resources.dto.PaperRequestType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class PaperRequest extends BaseEntity{


    private String description;
    private boolean flag;

    @ManyToOne
    @JoinColumn(name="library_id")
    private Library library;

    @ManyToOne
    @JoinColumn(name="secretary_id")
    private Secretary secretary;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    private PaperRequestType paperRequestType;

    private Date date = new Date();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Date getDate() {
        return date;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PaperRequestType getPaperRequestType() {
        return paperRequestType;
    }

    public void setPaperRequestType(PaperRequestType paperRequestType) {
        this.paperRequestType = paperRequestType;
    }

    public Secretary getSecretary() {
        return secretary;
    }

    public void setSecretary(Secretary secretary) {
        this.secretary = secretary;
    }
}
