package ro.studentportal.stportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.studentportal.stportal.model.CourseEntryStatus;
import ro.studentportal.stportal.model.StudentCourseQueue;
import ro.studentportal.stportal.model.User;

import java.util.List;

public interface StudentCourseQueueRepository extends JpaRepository<StudentCourseQueue, Long> {
    List<StudentCourseQueue> findAllByCourseIdAndCourseEntryStatus(Long courseId, CourseEntryStatus courseEntryStatus);
    StudentCourseQueue findByStudentIdAndCourseIdAndCourseEntryStatus(Long studentId, Long courseId, CourseEntryStatus courseEntryStatus);
}
