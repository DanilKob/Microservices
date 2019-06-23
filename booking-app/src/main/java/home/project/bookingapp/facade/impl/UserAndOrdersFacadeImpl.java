package home.project.bookingapp.facade.impl;

import home.project.bookingapp.dto.CategoryDTO;
import home.project.bookingapp.dto.UserDTO;
import home.project.bookingapp.dto.UserHomePageInfoDTO;
import home.project.bookingapp.facade.UserAndOrdersFacade;
import home.project.bookingapp.service.OrderService;
import home.project.bookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserAndOrdersFacadeImpl implements UserAndOrdersFacade {

    private UserService userService;
    private OrderService orderService;

    @Autowired
    public UserAndOrdersFacadeImpl(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    @Transactional
    public UserHomePageInfoDTO login(String login, Authentication authentication) {
        UserDTO userDTO = userService.findByLogin(login);
        if (userDTO == null) {
            userDTO = userService.createUser(login, authentication);
        }
        List<CategoryDTO> allCategories = orderService.getAllCategories();
        UserHomePageInfoDTO userHomePageInfoDTO = new UserHomePageInfoDTO();
        userHomePageInfoDTO.setUserDTO(userDTO);
        userHomePageInfoDTO.setCategoryDTOList(allCategories);
        return userHomePageInfoDTO;
    }

    @Override
    public void addCategories(List<CategoryDTO> categoryDTOS) {
        orderService.addCategories(categoryDTOS);
    }
}
