package ro.studentportal.stportal.resources.dto;

import ro.studentportal.stportal.model.Email;

public class LibraryCreationDto {

    private String phoneNumber;
    private Email email;
    private String librarianUsername;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getLibrarianUsername() {
        return librarianUsername;
    }

    public void setLibrarianUsername(String librarianUsername) {
        this.librarianUsername = librarianUsername;
    }
}
