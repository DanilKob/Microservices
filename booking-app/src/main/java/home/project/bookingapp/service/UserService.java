package home.project.bookingapp.service;

import home.project.bookingapp.dto.UserDTO;
import org.springframework.security.core.Authentication;

public interface UserService {
    UserDTO findByLogin(String login);
    UserDTO createUser(String login, Authentication authentication);
}
