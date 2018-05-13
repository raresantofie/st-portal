package ro.studentportal.stportal.resources.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ro.studentportal.stportal.model.UniversityEmployee;
import ro.studentportal.stportal.model.Student;
import ro.studentportal.stportal.resources.dto.UniversityEmployeeDto;
import ro.studentportal.stportal.resources.dto.StudentDto;

@Mapper(uses = {FacultyMapper.class})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping( target= "id" , ignore = true),
            @Mapping( target = "faculty", ignore = true)
    } )
    Student asModel(StudentDto studentDto);

    @Mappings({
            @Mapping( target = "faculty", ignore = true)
    } )
    StudentDto asDto(Student student);
    @Mappings({
            @Mapping( target= "id" , ignore = true),
            @Mapping( target = "faculty", ignore = true)
    } )
    Student update(StudentDto studentDto, @MappingTarget Student student);

    @Mappings({
            @Mapping( target= "id" , ignore = true),
            @Mapping( target = "faculty", ignore = true)
    } )
    UniversityEmployee asModel(UniversityEmployeeDto universityEmployeeDto);

    @Mappings({
            @Mapping( target = "faculty", ignore = true)
    } )
    UniversityEmployeeDto asDto(UniversityEmployee universityEmployee);


    @Mappings({
            @Mapping( target= "id" , ignore = true),
            @Mapping( target = "faculty", ignore = true)
    } )
    UniversityEmployee update(UniversityEmployeeDto universityEmployeeDto, @MappingTarget UniversityEmployee universityEmployee);



}
