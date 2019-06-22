package home.project.bookingapp.controller;

import home.project.bookingapp.dto.UserDTO;
import home.project.bookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // TODO: Remove testing api
    @RequestMapping(path = "/guest", method = RequestMethod.GET)
    public String getGuestInfo() {
        return "Guest info";
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public String getUserInfo() {
        return "User info";
    }

    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public String getAdminInfo() {
        return "Admin info";
    }

    @RequestMapping(path = "/user/{login}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUser(
            @PathVariable(name = "login") String login,
            Authentication authentication
    ) {
        UserDTO userDTO = userService.findByLogin(login);
        if (userDTO == null) {
            userDTO = userService.createUser(login, authentication);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
