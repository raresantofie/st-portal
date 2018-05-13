package ro.studentportal.stportal.resources.dto;

import java.util.Date;

public class PaperRequestProcessDto {
    private Long id;
    private String date;
    private String description;
    private boolean status;
    private String studentName;
    private Long studentYear;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(Long studentYear) {
        this.studentYear = studentYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
