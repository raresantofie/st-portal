package ro.studentportal.stportal.resources;

import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ro.studentportal.stportal.model.*;
import ro.studentportal.stportal.resources.dto.PaperRequestDto;
import ro.studentportal.stportal.resources.dto.PaperRequestType;
import ro.studentportal.stportal.resources.dto.mappers.CourseMapper;
import ro.studentportal.stportal.resources.dto.mappers.PaperRequestMapper;
import ro.studentportal.stportal.service.CourseService;
import ro.studentportal.stportal.service.PaperRequestService;
import ro.studentportal.stportal.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentActionController {

    @Autowired
    UserService userService;

    @Autowired
    PaperRequestService paperRequestService;

    @Autowired
    CourseService courseService;

    @PostMapping("/paper")
    public PaperRequestDto createPaperRequest(@RequestBody PaperRequest paperRequest){
        String username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        ro.studentportal.stportal.model.User user = userService.findUserByUsername(username);
        Student student = (Student)user;
        populatePaperRequest(student, paperRequest);
        return PaperRequestMapper.INSTANCE.toDto(paperRequestService.save(paperRequest));
    }

    @GetMapping("/course")
    public List<CourseCreateDto> getCourses() {
        String username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        ro.studentportal.stportal.model.User user = userService.findUserByUsername(username);
        Student student = (Student)user;
        List<Course> courses = courseService.findByFacultyId(student.getFaculty().getId());
        return CourseMapper.INSTANCE.toDto(courses);
    }

    @PostMapping("/course")
    public void applyCourse(@RequestParam("courseId") Long courseId){
        String username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        ro.studentportal.stportal.model.User user = userService.findUserByUsername(username);
        Student student = (Student)user;

        StudentCourseQueue courseQueue = new StudentCourseQueue();
        courseQueue.setCourseId(courseId);
        courseQueue.setStudentId(student.getId());
        courseQueue.setCourseEntryStatus(CourseEntryStatus.PENDING);
        try{
            courseService.save(courseQueue);
        } catch (Exception e){
            throw new RuntimeException("Runtime exc");
        }
    }

    private void populatePaperRequest(Student student, PaperRequest paperRequest){
        if(paperRequest.getPaperRequestType().equals(PaperRequestType.LIBRARY_PERMIT)){
            paperRequest.setLibrary(student.getFaculty().getLibrary());
        }
        paperRequest.setStudent(student);
        paperRequest.setFlag(false);
    }

}
