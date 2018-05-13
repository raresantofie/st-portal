package ro.studentportal.stportal.service;

import ro.studentportal.stportal.model.Library;

public interface LibraryService extends GenericService<Library, Long> {
    Library findById(Long id);

}
