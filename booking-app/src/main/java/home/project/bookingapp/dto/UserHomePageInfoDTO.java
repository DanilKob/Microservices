package home.project.bookingapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserHomePageInfoDTO {
    @JsonProperty("user")
    private UserDTO userDTO;
    @JsonProperty("categories")
    private List<CategoryDTO> categoryDTOList;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<CategoryDTO> getCategoryDTOList() {
        return categoryDTOList;
    }

    public void setCategoryDTOList(List<CategoryDTO> categoryDTOList) {
        this.categoryDTOList = categoryDTOList;
    }
}
