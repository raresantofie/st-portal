package ro.studentportal.stportal.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ro.studentportal.stportal.model.Faculty;
import ro.studentportal.stportal.model.Library;
import ro.studentportal.stportal.resources.dto.FacultyDto;
import ro.studentportal.stportal.resources.dto.LibraryCreationDto;
import ro.studentportal.stportal.resources.dto.mappers.FacultyMapper;
import ro.studentportal.stportal.service.FacultyService;
import ro.studentportal.stportal.service.FacultyServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/admin/faculties")
public class FacultyController {

    @Autowired
    FacultyService facultyService;

    @PostMapping
    public Faculty create(@RequestBody FacultyDto facultyDto){
        Faculty faculty = FacultyMapper.INSTANCE.asModel(facultyDto);
        return facultyService.save(faculty);
    }

    @GetMapping
    public Page<Faculty> getFaculties(@RequestParam int pageCount, @RequestParam int pageSize){
        return facultyService.getFaculties(PageRequest.of(pageCount, pageSize));
    }

    @GetMapping("/all")
    public List<FacultyDto> getFaculties(){
        return FacultyMapper.INSTANCE.asDto(facultyService.getAll());
    }
}
