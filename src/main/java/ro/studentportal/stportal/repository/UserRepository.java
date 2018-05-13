package ro.studentportal.stportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.studentportal.stportal.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
