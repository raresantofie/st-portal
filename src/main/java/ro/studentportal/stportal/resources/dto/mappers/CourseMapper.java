package ro.studentportal.stportal.resources.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ro.studentportal.stportal.model.Course;
import ro.studentportal.stportal.model.CourseCreateDto;
import ro.studentportal.stportal.model.Student;
import ro.studentportal.stportal.model.UniversityEmployee;
import ro.studentportal.stportal.resources.dto.StudentDto;
import ro.studentportal.stportal.resources.dto.UniversityEmployeeDto;

import java.util.List;

@Mapper(uses = {FacultyMapper.class})
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(target = "faculty", ignore = true)
    Course toModel(CourseCreateDto courseCreateDto);

    @Mapping(target = "faculty", ignore = true)
    CourseCreateDto toDto(Course course);

    List<CourseCreateDto> toDto(List<Course> courses);


}
