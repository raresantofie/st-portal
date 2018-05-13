package ro.studentportal.stportal.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"studentId", "courseId"})
})
public class StudentCourseQueue extends BaseEntity{

    private Long studentId;
    private Long courseId;
    private CourseEntryStatus courseEntryStatus = CourseEntryStatus.PENDING;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public CourseEntryStatus getCourseEntryStatus() {
        return courseEntryStatus;
    }

    public void setCourseEntryStatus(CourseEntryStatus courseEntryStatus) {
        this.courseEntryStatus = courseEntryStatus;
    }
}
