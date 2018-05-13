package ro.studentportal.stportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.studentportal.stportal.exception.ElementInexistentException;
import ro.studentportal.stportal.model.*;
import ro.studentportal.stportal.repository.FacultyRepository;
import ro.studentportal.stportal.repository.RoleRepository;
import ro.studentportal.stportal.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public User findUserByUsername(String email) {
        return userRepository.findUserByUsername(email);
    }

    @Override
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    @Override
    public User save(User user, String type) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole(type);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        checkFaculty(user);
        return userRepository.save(user);
    }

    @Override
    public <S extends User> S update(S var1) {
        return userRepository.save(var1);
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    private void checkFaculty(User user){
        if(user instanceof Student) {
            String facultyName = ((Student)user).getFaculty().getName();
            Faculty faculty = facultyRepository.findByName(facultyName);
            if(faculty == null) {
                throw new ElementInexistentException(String.format("Faculty with name %s does not exist", facultyName));
            } else {
                ((Student) user).setFaculty(faculty);
            }
        } else if (user instanceof UniversityEmployee){
            String facultyName = ((UniversityEmployee)user).getFaculty().getName();
            Faculty faculty = facultyRepository.findByName(facultyName);
            if(faculty == null) {
                throw new ElementInexistentException(String.format("Faculty with name %s does not exist", facultyName));
            } else {
                ((UniversityEmployee) user).setFaculty(faculty);
            }
        }
    }
}
