package home.project.authservice.service.impl;

import home.project.authservice.constants.AuthorityConstants;
import home.project.authservice.dto.UserDTO;
import home.project.authservice.entity.UserDetailsEntity;
import home.project.authservice.exceptions.UserRepositoryException;
import home.project.authservice.repository.UserDetailsRepository;
import home.project.authservice.service.UserService;
import home.project.crossserviceapi.auth.entity.FullName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private UserDetailsRepository userDetailsRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserDetailsRepository userDetailsRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(UserDTO userDTO) throws UserRepositoryException {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        String firstName = userDTO.getFirstName();
        String middleName = userDTO.getMiddleName();
        String lastName = userDTO.getLastName();
        UserDetailsEntity user = new UserDetailsEntity();
        FullName fullName = new FullName(firstName, middleName, lastName);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setFullName(fullName);
        user.setAuthorities(Collections.singletonList(AuthorityConstants.USER_AUTHORITY));
        user.setEnabled(true);
        userDetailsRepository.addUser(user);
    }
}
