package home.project.authservice.controller;

import home.project.authservice.dto.UserDTO;
import home.project.authservice.exceptions.UserRepositoryException;
import home.project.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public ResponseEntity registerUser(

            /*@Validated */ @RequestBody UserDTO userDTO
    ) {
        try {
            userService.addUser(userDTO);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (UserRepositoryException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/info", method = RequestMethod.GET)
    public String info() {
        return "info";
    }
}
