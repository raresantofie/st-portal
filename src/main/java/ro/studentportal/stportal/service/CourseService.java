package ro.studentportal.stportal.service;

import ro.studentportal.stportal.model.Course;
import ro.studentportal.stportal.model.Student;
import ro.studentportal.stportal.model.StudentCourseQueue;

import java.util.List;

public interface CourseService {

    Course save(Course course);
    List<Course> findAll();
    List<Course> findByFacultyId(Long id);
    List<Student> getPendingStudentsForCourse(Long courseId);
    StudentCourseQueue save(StudentCourseQueue studentCourseQueue);
    StudentCourseQueue findByStdIdAndCourseId(Long studentId, Long courseId);
}
