package home.project.bookingapp.service.impl;

import home.project.bookingapp.dto.CategoryDTO;
import home.project.bookingapp.dto.mapper.CategoryMapper;
import home.project.bookingapp.entity.Category;
import home.project.bookingapp.repository.CategoryRepository;
import home.project.bookingapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private CategoryRepository categoryRepository;

    @Autowired
    public OrderServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> allCategories = categoryRepository.findAll();
        final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
        List<CategoryDTO> categoryDTOList = allCategories.stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
        return categoryDTOList;
    }

    @Override
    public void addCategories(List<CategoryDTO> categoryDTOS) {
        CategoryMapper categoryMapper = CategoryMapper.INSTANCE;
        List<Category> categories = categoryDTOS.stream()
                .map(categoryMapper::categoryDTOToCategory)
                .collect(Collectors.toList());
        categoryRepository.saveAll(categories);
    }
}
