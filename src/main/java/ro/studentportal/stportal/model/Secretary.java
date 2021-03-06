package ro.studentportal.stportal.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Secretary extends BaseEntity{

    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Email email;
    @OneToOne(cascade = CascadeType.ALL)
    UniversityEmployee universityEmployee;
    @OneToMany(mappedBy = "secretary", cascade = CascadeType.ALL)
    List<PaperRequest> paperRequests;

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

    public UniversityEmployee getUniversityEmployee() {
        return universityEmployee;
    }

    public void setUniversityEmployee(UniversityEmployee universityEmployee) {
        this.universityEmployee = universityEmployee;
    }

    public List<PaperRequest> getPaperRequests() {
        return paperRequests;
    }

    public void setPaperRequests(List<PaperRequest> paperRequests) {
        this.paperRequests = paperRequests;
    }
}
