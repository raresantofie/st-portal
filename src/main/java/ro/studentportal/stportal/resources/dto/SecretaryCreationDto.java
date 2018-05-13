package ro.studentportal.stportal.resources.dto;

import ro.studentportal.stportal.model.Email;

public class SecretaryCreationDto {

    private String phoneNumber;
    private Email email;
    private String secretaryUsername;

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

    public String getSecretaryUsername() {
        return secretaryUsername;
    }

    public void setSecretaryUsername(String secretaryUsername) {
        this.secretaryUsername = secretaryUsername;
    }
}
