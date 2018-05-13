package ro.studentportal.stportal.service;

import ro.studentportal.stportal.model.User;

public interface UserService extends GenericService<User,Long> {
    User findUserByUsername(String username);
    <S extends User> S save(S var1, String type);
}
