package home.project.bookingapp.service.impl;

import home.project.bookingapp.dto.UserDTO;
import home.project.bookingapp.dto.mapper.UserMapper;
import home.project.bookingapp.entity.FullName;
import home.project.bookingapp.entity.User;
import home.project.bookingapp.repository.UserRepository;
import home.project.bookingapp.service.UserService;
import home.project.crossserviceapi.auth.entity.UserDetailsPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO findByLogin(String login) {
        return UserMapper.INSTANCE.userToUserDTO(userRepository.findUserByLogin(login));
    }

    @Override
    public UserDTO createUser(String login, Authentication authentication) {
        UserDetailsPrincipal principal = (UserDetailsPrincipal) authentication.getPrincipal();
        User user = new User();
        FullName fullName = new FullName();
        fullName.setFirstName(principal.getFirstName());
        fullName.setMiddleName(principal.getMiddleName());
        fullName.setLastName(principal.getLastName());

        user.setFullName(fullName);
        user.setLogin(login);


        User savedUser = userRepository.save(user);

        UserDTO userDTO = UserMapper.INSTANCE.userToUserDTO(savedUser);

        return userDTO;
    }
}
