package ro.studentportal.stportal.model;

import ro.studentportal.stportal.resources.dto.StudentDto;

public class CourseEntryDto {

    private StudentDto studentDto;
    private CourseEntryStatus courseEntryStatus;

    public StudentDto getStudentDto() {
        return studentDto;
    }

    public void setStudentDto(StudentDto studentDto) {
        this.studentDto = studentDto;
    }

    public CourseEntryStatus getCourseEntryStatus() {
        return courseEntryStatus;
    }

    public void setCourseEntryStatus(CourseEntryStatus courseEntryStatus) {
        this.courseEntryStatus = courseEntryStatus;
    }

    public static CourseEntryDto mapper(StudentDto studentDto, CourseEntryStatus courseEntryStatus){
        CourseEntryDto courseEntryDto = new CourseEntryDto();
        courseEntryDto.setStudentDto(studentDto);
        courseEntryDto.setCourseEntryStatus(courseEntryStatus);
        return courseEntryDto;
    }
}
