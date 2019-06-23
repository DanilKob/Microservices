package home.project.bookingapp.controller;

import home.project.bookingapp.dto.CategoryDTO;
import home.project.bookingapp.facade.UserAndOrdersFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private UserAndOrdersFacade userAndOrdersFacade;

    @Autowired
    public void setUserAndOrdersFacade(UserAndOrdersFacade userAndOrdersFacade) {
        this.userAndOrdersFacade = userAndOrdersFacade;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/categories", method = RequestMethod.POST)
    public ResponseEntity addCategories(
            @RequestBody List<CategoryDTO> categories
    ) {
        userAndOrdersFacade.addCategories(categories);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
