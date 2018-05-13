package ro.studentportal.stportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.studentportal.stportal.model.Role;
import ro.studentportal.stportal.model.User;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
