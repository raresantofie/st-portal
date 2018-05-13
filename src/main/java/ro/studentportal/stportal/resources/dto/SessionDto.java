package ro.studentportal.stportal.resources.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SessionDto {

    private String name;
    private Collection<GrantedAuthority> role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<GrantedAuthority> getRole() {
        return role;
    }

    public void setRole(Collection<GrantedAuthority> role) {
        this.role = role;
    }

    public static SessionDto mapObject(User user) {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setName(user.getName());
        sessionDto.setRole(user.getAuthorities());
        return sessionDto;
    }
}
