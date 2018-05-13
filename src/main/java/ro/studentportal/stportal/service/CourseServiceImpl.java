package ro.studentportal.stportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.studentportal.stportal.model.Course;
import ro.studentportal.stportal.model.CourseEntryStatus;
import ro.studentportal.stportal.model.Student;
import ro.studentportal.stportal.model.StudentCourseQueue;
import ro.studentportal.stportal.repository.CourseRepository;
import ro.studentportal.stportal.repository.StudentCourseQueueRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentCourseQueueRepository studentCourseQueueRepository;

    @Autowired
    UserService userService;

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findByFacultyId(Long id) {
        return courseRepository.findAllByFacultyId(id);
    }

    @Override
    public List<Student> getPendingStudentsForCourse(Long courseId) {
        List<StudentCourseQueue> list = studentCourseQueueRepository.findAllByCourseIdAndCourseEntryStatus(courseId, CourseEntryStatus.PENDING);
        List<Student> pendingStudents = new ArrayList<>();
        for(StudentCourseQueue l : list){
            Student student = userService.findById(l.getStudentId());
            pendingStudents.add(student);
        }

        return pendingStudents;
    }

    @Override
    public StudentCourseQueue save(StudentCourseQueue studentCourseQueue) {
        return studentCourseQueueRepository.save(studentCourseQueue);
    }

    @Override
    public StudentCourseQueue findByStdIdAndCourseId(Long studentId, Long courseId) {
        return studentCourseQueueRepository.findByStudentIdAndCourseIdAndCourseEntryStatus(studentId, courseId, CourseEntryStatus.PENDING);
    }


}
