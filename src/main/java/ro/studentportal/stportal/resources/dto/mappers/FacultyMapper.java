package ro.studentportal.stportal.resources.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ro.studentportal.stportal.model.Faculty;
import ro.studentportal.stportal.model.Library;
import ro.studentportal.stportal.model.Secretary;
import ro.studentportal.stportal.model.Student;
import ro.studentportal.stportal.resources.dto.FacultyDto;
import ro.studentportal.stportal.resources.dto.StudentDto;

import java.util.List;

@Mapper
public abstract class FacultyMapper {
    public static FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);

    public Faculty asModel(FacultyDto facultyDto){
        if ( facultyDto == null ) {
            return null;
        }

        if ( facultyDto.getLibraryCreationDto() == null){
            return null;
        }
        Faculty faculty = new Faculty();
        Library library = new Library();
        Secretary secretary = new Secretary();

        faculty.setName( facultyDto.getName() );
        library.setEmail(facultyDto.getLibraryCreationDto().getEmail());
        library.setPhoneNumber(facultyDto.getLibraryCreationDto().getPhoneNumber());
        secretary.setEmail(facultyDto.getSecretaryCreationDto().getEmail());
        secretary.setPhoneNumber(facultyDto.getSecretaryCreationDto().getPhoneNumber());
        faculty.setLibrary(library);
        faculty.setSecretary(secretary);
        return faculty;
    }

    public abstract FacultyDto asDto(Faculty faculty);

    public abstract List<FacultyDto> asDto(List<Faculty> faculties);

    @Mapping(target = "id", ignore = true)
    public abstract Faculty update(FacultyDto facultyDto, @MappingTarget Faculty faculty);

}
