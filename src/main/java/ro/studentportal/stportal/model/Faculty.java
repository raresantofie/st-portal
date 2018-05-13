package ro.studentportal.stportal.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Faculty extends BaseEntity {

    @Column(unique = true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Library library;

    @OneToOne(cascade = CascadeType.ALL)
    private Secretary secretary;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "faculty")
    private List<Course> courses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Secretary getSecretary() {
        return secretary;
    }

    public void setSecretary(Secretary secretary) {
        this.secretary = secretary;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
