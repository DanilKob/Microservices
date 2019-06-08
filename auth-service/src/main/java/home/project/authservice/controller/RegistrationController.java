package home.project.authservice.controller;

import home.project.authservice.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public ResponseEntity registerUser(

            @Validated @RequestBody UserDTO userDTO
    ){
        return null;
    }
}
