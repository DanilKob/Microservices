package home.project.authservice.service;

import home.project.authservice.dto.UserDTO;
import home.project.authservice.exceptions.DuplicateUserNameException;
import home.project.authservice.exceptions.UserRepositoryException;

public interface UserService {
    void addUser(UserDTO userDTO) throws UserRepositoryException, DuplicateUserNameException;
}
