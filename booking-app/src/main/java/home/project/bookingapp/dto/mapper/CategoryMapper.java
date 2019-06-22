package home.project.bookingapp.dto.mapper;

import home.project.bookingapp.dto.CategoryDTO;
import home.project.bookingapp.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "categoryKey", target = "key")
    CategoryDTO categoryToCategoryDTO(Category category);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "key", target = "categoryKey")
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
