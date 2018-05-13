package ro.studentportal.stportal.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.studentportal.stportal.model.*;
import ro.studentportal.stportal.resources.dto.mappers.CourseMapper;
import ro.studentportal.stportal.resources.dto.mappers.UserMapper;
import ro.studentportal.stportal.service.CourseService;
import ro.studentportal.stportal.service.FacultyService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    FacultyService facultyService;

    @PostMapping
    public CourseCreateDto createCourse(@RequestBody CourseCreateDto c){
        Faculty faculty = facultyService.findByName(c.getFaculty());
        Course course = CourseMapper.INSTANCE.toModel(c);
        course.setFaculty(faculty);
        return CourseMapper.INSTANCE.toDto(courseService.save(course));
    }

    @GetMapping
    public List<CourseListDto> getCourses(){
        List<CourseListDto> courseListDtos = new ArrayList<>();
        List<Course> courses = courseService.findAll();
        for(Course course : courses) {
            int count = courseService.getPendingStudentsForCourse(course.getId()).size();
            CourseListDto courseListDto = new CourseListDto();
            courseListDto.setCount(Long.valueOf(count));
            courseListDto.setDescription(course.getDescription());
            courseListDto.setFaculty(course.getFaculty().getName());
            courseListDto.setId(course.getId());
            courseListDtos.add(courseListDto);
        }
        return courseListDtos;
    }

    @GetMapping("/{id}/entries")
    public List<CourseEntryDto> getStudents(@PathVariable Long id){
        List<Student> studentCourseQueues = courseService.getPendingStudentsForCourse(id);
        List<CourseEntryDto> courseEntryDtos = new ArrayList<>();
        for(Student student : studentCourseQueues){
            CourseEntryDto courseEntryDto = new CourseEntryDto();
            courseEntryDto.setStudentDto(UserMapper.INSTANCE.asDto(student));
            StudentCourseQueue studentCourseQueue = courseService.findByStdIdAndCourseId(student.getId(),id);
            courseEntryDto.setCourseEntryStatus(studentCourseQueue.getCourseEntryStatus());
            courseEntryDtos.add(courseEntryDto);
        }
        return  courseEntryDtos;
    }

    @PutMapping("/entries")
    public void updateStatus(@RequestParam("courseId") Long courseId, @RequestParam("studentId") Long studentId, @RequestParam("status") String status){
        StudentCourseQueue studentCourseQueue = courseService.findByStdIdAndCourseId(studentId,courseId);
        studentCourseQueue.setCourseEntryStatus(CourseEntryStatus.valueOf(status));
        courseService.save(studentCourseQueue);
    }


}
