package home.project.bookingapp.controller;

import home.project.bookingapp.dto.UserHomePageInfoDTO;
import home.project.bookingapp.facade.UserAndOrdersFacade;
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

    private UserAndOrdersFacade userAndOrdersFacade;

    @Autowired
    public void setUserAndOrdersFacade(UserAndOrdersFacade userAndOrdersFacade) {
        this.userAndOrdersFacade = userAndOrdersFacade;
    }

    @RequestMapping(path = "/user/{login}", method = RequestMethod.GET)
    public ResponseEntity<UserHomePageInfoDTO> getUser(
            @PathVariable(name = "login") String login,
            Authentication authentication
    ) {
        UserHomePageInfoDTO userHomePageInfoDTO = userAndOrdersFacade.login(login, authentication);
        return new ResponseEntity(userHomePageInfoDTO, HttpStatus.OK);
    }
}
