package home.project.bookingapp.service;

import home.project.bookingapp.dto.CategoryDTO;

import java.util.List;

public interface OrderService {
    List<CategoryDTO> getAllCategories();
    void addCategories(List<CategoryDTO> categoryDTOS);
}
