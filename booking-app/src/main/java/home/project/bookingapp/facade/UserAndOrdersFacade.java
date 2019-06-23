package home.project.bookingapp.facade;

import home.project.bookingapp.dto.CategoryDTO;
import home.project.bookingapp.dto.UserHomePageInfoDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserAndOrdersFacade {
    UserHomePageInfoDTO login(String login, Authentication authentication);
    void addCategories(List<CategoryDTO> categoryDTOS);
}
