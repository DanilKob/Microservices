package home.project.bookingapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    // TODO: Remove testing api
    @RequestMapping(path="/guest", method = RequestMethod.GET)
    public String getGuestInfo(){
        return "Guest info";
    }
    @RequestMapping(path="/user", method = RequestMethod.GET)
    public String getUserInfo(){
        return "User info";
    }
    @RequestMapping(path="/admin", method = RequestMethod.GET)
    public String getAdminInfo(){
        return "Admin info";
    }
}
