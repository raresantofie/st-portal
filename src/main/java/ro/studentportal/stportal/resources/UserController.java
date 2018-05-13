package ro.studentportal.stportal.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.studentportal.stportal.model.*;
import ro.studentportal.stportal.resources.dto.UniversityEmployeeDto;
import ro.studentportal.stportal.resources.dto.StudentDto;
import ro.studentportal.stportal.resources.dto.mappers.UserMapper;
import ro.studentportal.stportal.service.UserService;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    UserService userService;

    private static final String ROLE_TYPE_STUDENT = "STUDENT";
    private static final String ROLE_TYPE_LIBRARIAN = "LIBRARIAN";

    @PostMapping("/student")
    public StudentDto createStudent(@RequestBody StudentDto studentDto){
        Student student = UserMapper.INSTANCE.asModel(studentDto);
        setFaculty(student, studentDto.getFaculty());
        return UserMapper.INSTANCE.asDto(userService.save(student,ROLE_TYPE_STUDENT));
    }

    @PutMapping("/student")
    public StudentDto updateStudent(@RequestBody StudentDto studentDto){
        Student dbStudent = userService.findById(studentDto.getId());
        Student student = UserMapper.INSTANCE.update(studentDto, dbStudent);
        setFaculty(student, studentDto.getFaculty());
        return UserMapper.INSTANCE.asDto(userService.update(student));
    }

    @PostMapping("/employee")
    public UniversityEmployeeDto createLibrarian(@RequestBody UniversityEmployeeDto universityEmployeeDto, @RequestParam("type") String type){
        UniversityEmployee universityEmployee = UserMapper.INSTANCE.asModel(universityEmployeeDto);
        setFaculty(universityEmployee, universityEmployeeDto.getFaculty());
        return UserMapper.INSTANCE.asDto(userService.save(universityEmployee,type));
    }

    @PutMapping("/employee")
    public UniversityEmployeeDto updateLibrarian(@RequestBody UniversityEmployeeDto universityEmployeeDto){
        UniversityEmployee dbUniversityEmployee = userService.findById(universityEmployeeDto.getId());
        UniversityEmployee universityEmployee = UserMapper.INSTANCE.update(universityEmployeeDto, dbUniversityEmployee);
        setFaculty(universityEmployee, universityEmployeeDto.getFaculty());
        return UserMapper.INSTANCE.asDto(userService.update(universityEmployee));
    }

    private void setFaculty(User user, String facultyName){
        Faculty faculty = new Faculty();
        faculty.setName(facultyName);
        if(user instanceof Student){
            ((Student)user).setFaculty(faculty);
        } else if (user instanceof UniversityEmployee){
            ((UniversityEmployee)user).setFaculty(faculty);
        }
    }

}
