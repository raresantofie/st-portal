package ro.studentportal.stportal.resources.dto;

public class FacultyDto {

    private Long id;
    private String name;
    private LibraryCreationDto libraryCreationDto;
    private SecretaryCreationDto secretaryCreationDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LibraryCreationDto getLibraryCreationDto() {
        return libraryCreationDto;
    }

    public void setLibraryCreationDto(LibraryCreationDto libraryCreationDto) {
        this.libraryCreationDto = libraryCreationDto;
    }

    public SecretaryCreationDto getSecretaryCreationDto() {
        return secretaryCreationDto;
    }

    public void setSecretaryCreationDto(SecretaryCreationDto secretaryCreationDto) {
        this.secretaryCreationDto = secretaryCreationDto;
    }
}
