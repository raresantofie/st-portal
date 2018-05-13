package ro.studentportal.stportal.resources.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ro.studentportal.stportal.model.Email;
import ro.studentportal.stportal.model.Gender;
import ro.studentportal.stportal.model.Name;

public class StudentDto {

    private Long id;
    private Name name;
    private Email email;
    private String faculty;
    private Integer yearOfStudy;
    private Gender gender;
    private boolean accommodationStatus;
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
